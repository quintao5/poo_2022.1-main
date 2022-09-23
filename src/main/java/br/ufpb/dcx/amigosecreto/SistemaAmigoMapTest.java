package br.ufpb.dcx.amigosecreto;

import java.util.Collection;

public class SistemaAmigoMapTest {
    public static void main (String [] args){
        SistemaAmigoMap sistema = new SistemaAmigoMap();

        try {
            sistema.cadastraAmigo("José", "jose@gmail.com");
            sistema.cadastraAmigo("Maria", "maria@gmail.com");
        }catch(AmigoJaExisteException e){
            System.out.println(e.getMessage());
        }

        try{
            sistema.configuraAmigoSecretoDe("jose@gmail.com", "maria@gmail.com");
            sistema.configuraAmigoSecretoDe("maria@gmail.com", "jose@gmail.com");
        }catch (AmigoInexistenteException e){
            System.out.println(e.getMessage());
        }
        sistema.enviarMensagemParaAlguem("fala josé", "maria@gmail.com", "jose@gmail.com", true);
        sistema.enviarMensagemParaTodos("quero presente bom", "maria@gmail.com", true);

        Collection<Mensagem> mensagensAnonimas =sistema.pesquisaMensagensAnonimas();
        for(Mensagem m : mensagensAnonimas){
            System.out.println(m.getTexto());
        }
        try{
            String amigoDeJose =sistema.pesquisaAmigoSecretoDe("jose@gmail.com");
            if(amigoDeJose.equals("maria@gmail.com")){
                System.out.println("ok");
            }else{
                System.out.println("erro no amigo de josé");
            }
        }catch(AmigoInexistenteException | AmigoNaoSorteadoException e){
            System.out.println(e.getMessage());
        }
        Collection<Mensagem> men =sistema.pesquisaTodasAsMensagens();
        for(Mensagem m: men){
            System.out.println(m.getTexto());
        }
    }
}
