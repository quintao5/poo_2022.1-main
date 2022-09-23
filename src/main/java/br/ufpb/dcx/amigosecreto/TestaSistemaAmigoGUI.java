package br.ufpb.dcx.amigosecreto;

import javax.swing.*;

public class TestaSistemaAmigoGUI {
    public static void main (String [] args){
        int maxMensagens = Integer.parseInt(JOptionPane.showInputDialog("digite o maximo de mensagens suportada pelo sistema"));

        SistemaAmigo sistema = new SistemaAmigo();
        int maxAmigos = Integer.parseInt(JOptionPane.showInputDialog("digite o maximo de amigos suportada pelo sistema"));

        for (int k=0; k<maxAmigos; k++){
            String nome = JOptionPane.showInputDialog("Digite o nome da pessoa");
            String email = JOptionPane.showInputDialog("Digite o email da pessoa");
            try{
                sistema.cadastraAmigo(nome, email);
                JOptionPane.showMessageDialog(null, "pessoa cadastrada");
            } catch(AmigoJaExisteException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }

        for(int k=0; k<maxAmigos; k++){
            String emailDaPessoa = JOptionPane.showInputDialog("Digite o email da pessoa");
            String emailDoAmigoSecreto = JOptionPane.showInputDialog("Digite o email do amigo secreto da pessoa");
            try{
                sistema.configuraAmigoSecretoDe(emailDaPessoa,emailDoAmigoSecreto );
             }catch(AmigoInexistenteException e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        String textoMensagem = JOptionPane.showInputDialog("Digite o texto da mensagem a enviar");
        String remetente = JOptionPane.showInputDialog("digite o email do remetente");
        String ehAnonima = JOptionPane.showInputDialog("é anonima : S/N");
        boolean anonima = false;
        if(ehAnonima.equalsIgnoreCase("s")){
            anonima = true;
        }
        MensagemParaTodos m = new MensagemParaTodos(textoMensagem,remetente,anonima);
    }
}
