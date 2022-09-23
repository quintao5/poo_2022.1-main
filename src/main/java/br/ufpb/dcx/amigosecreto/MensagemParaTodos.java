package br.ufpb.dcx.amigosecreto;

public class MensagemParaTodos extends Mensagem {
    public MensagemParaTodos(String texto,String emailRemetente, boolean anonima) {
        super(texto, emailRemetente, anonima);
    }

    public String getTextoCompletoAExibir() {
        if (super.ehAnonima()) {
            return "Mensagem para todos:\n" + super.getTexto();
        }
        return "Mensagem de " + super.getEmailRemetente() + " Para todos:\n"+super.getTexto();

    }
}

