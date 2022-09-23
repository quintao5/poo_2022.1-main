package br.ufpb.dcx.amigosecreto;

import java.util.*;

public class SistemaAmigoMap {
    private Map<Integer, Mensagem> mensagens;
    private Map<String, Amigo> amigos;

    public SistemaAmigoMap(){
        this.mensagens = new HashMap<>();
        this.amigos = new HashMap<>();
    }
    public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
        for(Amigo a: this.amigos.values()){
            if(a.getEmail().equals(emailAmigo)){
                return a;
            }
        }
        throw new AmigoInexistenteException("Não foi encontrado no sistema nenhum usuário com o email "+emailAmigo);
    }
    public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
        for(Amigo a: this.amigos.values()){
            if(a.getEmail().equals(emailAmigo)){
                throw new AmigoJaExisteException("já existe pessoa no sisteema com o email "+emailAmigo);
            }
        }
        Amigo novoAmigo = new Amigo(nomeAmigo, emailAmigo);
        this.amigos.put(novoAmigo.getEmail(), novoAmigo);
    }
    public Collection<Mensagem> pesquisaTodasAsMensagens() {

        return this.mensagens.values();
    }
    public void enviarMensagemParaTodos(String texto, String remetente, boolean ehAnonima) {
        MensagemParaTodos m= new MensagemParaTodos(texto,remetente,ehAnonima);
        this.mensagens.put(mensagens.size()+1, m);
    }
    public void enviarMensagemParaAlguem(String texto, String remetente, String destinatario, boolean ehAnonima) {
        MensagemParaAlguem m = new MensagemParaAlguem(texto, remetente,destinatario, ehAnonima);
        this.mensagens.put(mensagens.size()+1, m);
    }
    public Collection<Mensagem> pesquisaMensagensAnonimas() {
        Collection<Mensagem>anonimas = new ArrayList<>();
        for(Mensagem m : this.mensagens.values()){
            if(m.ehAnonima()){
                anonimas.add(m);
            }
        }
        return anonimas;
    }
    public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException {

        for(Amigo a : this.amigos.values()){
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
        for (Amigo a: this.amigos.values()){
            if (a.getEmail().equals(emailDaPessoa)){
                a.setEmailAmigoSorteado(emailAmigoSorteado);
                return;
            }
        }
        throw new AmigoInexistenteException("não existe pessoa no sistema com o email "+emailDaPessoa);
    }
}


