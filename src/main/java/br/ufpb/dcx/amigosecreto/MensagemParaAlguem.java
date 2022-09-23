package br.ufpb.dcx.amigosecreto;

public class MensagemParaAlguem extends Mensagem{
    private String emailDestinatario;

    public MensagemParaAlguem (String texto, String emailRemetente, String emailDestinatario, boolean anonima){
        super(texto, emailRemetente,anonima);
        this.emailDestinatario = emailDestinatario;
    }

    public String getTextoCompletoAExibir() {
        if(super.ehAnonima()){
            return "Mensagem para: "+ this.emailDestinatario + ". Texto: "+super.getTexto();
        }
        return "Mensagem de: "+super.getEmailRemetente()+" para "+ this.emailDestinatario+"\n Texto:\n"+super.getTexto();
    }
}
