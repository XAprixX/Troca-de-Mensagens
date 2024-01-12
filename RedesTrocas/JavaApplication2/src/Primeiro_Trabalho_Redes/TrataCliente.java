package Primeiro_Trabalho_Redes;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class TrataCliente {
		
	private Socket soquete_cliente;
	private ObjectOutputStream saida;
	private ObjectInputStream entrada;

	public TrataCliente(Socket soquete_cliente) throws Exception {
		super();
		this.soquete_cliente = soquete_cliente;
		this.saida = new ObjectOutputStream(this.soquete_cliente.getOutputStream()); 
		this.entrada = new ObjectInputStream(this.soquete_cliente.getInputStream());
	}
	
	public void enviar_mensagem(Object mensagem) throws Exception {
		this.saida.writeObject(mensagem);
	}
	
	public Object receber_mensagem() throws Exception {
		return this.entrada.readObject();
	}
	
	public void finalizar() throws IOException {
		this.soquete_cliente.close();
	}
	
	public int iniciar(Servidor servidor) throws Exception {
            Scanner inputUser = new Scanner(System.in);
            
		String mensagem = (String)receber_mensagem();
                if(mensagem.equalsIgnoreCase("sair")){
                    System.out.println("O cliente desconectou");
                    finalizar();
                    return 1;
                }
                
                
		System.out.println("Mensagem do Cliente: " + mensagem);
		System.out.print("->");
                String mensaem = inputUser.nextLine();
		finalizar();
            if(mensagem.equalsIgnoreCase("sair")){
                System.out.println("--encerrar servidor--");
                enviar_mensagem(mensagem);
                finalizar();
                servidor.finalizar();
                return 0;
            }
            enviar_mensagem(mensagem);
            System.out.println("erro");
            finalizar();
            return 1;
                
	}
}
