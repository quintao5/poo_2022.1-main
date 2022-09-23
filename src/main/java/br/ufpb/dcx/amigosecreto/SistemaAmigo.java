package br.ufpb.dcx.amigosecreto;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SistemaAmigo {
    private List <Mensagem> mensagens;
    private List <Amigo> amigos;

    public SistemaAmigo(){
        this.mensagens = new LinkedList<>();
        this.amigos = new LinkedList<>();
    }
    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException{
        for(Amigo a: this.amigos){
            if(a.getEmail().equals(emailAmigo)){
                return a;
            }
        }
        throw new AmigoInexistenteException("Não foi encontrado no sistema nenhum usuário com o email "+emailAmigo);
    }

    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
        for(Amigo a: this.amigos){
            if(a.getEmail().equals(emailAmigo)){
                throw new AmigoJaExisteException("já existe pessoa no sisteema com o email "+emailAmigo);
            }
        }
        Amigo novoAmigo = new Amigo(nomeAmigo, emailAmigo);
        this.amigos.add(novoAmigo);
    }

    public List<Mensagem> pesquisaTodasAsMensagens() {

        return this.mensagens;
    }

    public void enviarMensagemParaTodos(String texto, String remetente, boolean ehAnonima) {
        MensagemParaTodos m= new MensagemParaTodos(texto,remetente,ehAnonima);
        this.mensagens.add(m);
    }

    public void enviarMensagemParaAlguem(String texto, String remetente, String destinatario, boolean ehAnonima) {
        MensagemParaAlguem m = new MensagemParaAlguem(texto, remetente,destinatario, ehAnonima);
        this.mensagens.add(m);
    }

    public List<Mensagem> pesquisaMensagensAnonimas() {
        List<Mensagem> anonimas = new LinkedList<>();
        for(int i = 0; i < this.mensagens.size(); i++){
            Mensagem m = this.mensagens.get(i);
            if(m.ehAnonima()){
                anonimas.add(m);
            }
        }
        return anonimas;
    }

    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {

        for(int i = 0; i < this.amigos.size(); i++){
            Amigo a = this.amigos.get(i);
            if(a.getEmail().equals(emailDaPessoa)){
                String emailAmigoSorteado = a.getEmailAmigoSorteado();
                if (emailAmigoSorteado == null){
                    throw new AmigoNaoSorteadoException("ainda nao foi sorteado o amigo secreto da pessoa com email "+a.getEmail());
                }else{
                    return emailAmigoSorteado;
                }
            }
        }
        throw new AmigoInexistenteException("Não foi encontrada no sistema nenhuma pessoa com o email "+ emailDaPessoa);

    }

    public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws  AmigoInexistenteException{
        for (Amigo a: this.amigos){
            if (a.getEmail().equals(emailDaPessoa)){
                a.setEmailAmigoSorteado(emailAmigoSorteado);
                return;
            }
        }
        throw new AmigoInexistenteException("não existe pessoa no sistema com o email "+emailDaPessoa);
    }
}
