package br.ufpb.dcx.amigosecreto;


import java.util.List;

public class TestaSistemaAmigo {
    public static void main (String [] args){
        SistemaAmigo sistema = new SistemaAmigo();
        //Letra A
        try {
            sistema.cadastraAmigo("José", "jose@gmail.com");
            sistema.cadastraAmigo("Maria", "maria@gmail.com");
        }catch(AmigoJaExisteException e){
            System.out.println(e.getMessage());
        }
        //Letra B
        try{
        sistema.configuraAmigoSecretoDe("jose@gmail.com", "maria@gmail.com");
        sistema.configuraAmigoSecretoDe("maria@gmail.com", "jose@gmail.com");
        }catch (AmigoInexistenteException e){
            System.out.println(e.getMessage());
        }
        //Letra C
        sistema.enviarMensagemParaAlguem("fala josé", "maria@gmail.com", "jose@gmail.com", true);
        //Letra D
        sistema.enviarMensagemParaTodos("quero presente bom", "maria@gmail.com", true);
        //Letra E
        List<Mensagem> mensagensAnonimas =sistema.pesquisaMensagensAnonimas();
        for(Mensagem m : mensagensAnonimas){
            System.out.println(m.getTexto());
        }
        //Letra F
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
    }
}
