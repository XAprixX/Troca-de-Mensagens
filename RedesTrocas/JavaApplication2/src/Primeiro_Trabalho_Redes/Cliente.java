package Primeiro_Trabalho_Redes;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Cliente {
	
	private Socket soquete;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	
	public Cliente(String endereco, int porta) throws Exception {
		super();
		this.soquete = new Socket(endereco, porta);
		this.output = new ObjectOutputStream(this.soquete.getOutputStream()); 
		this.input = new ObjectInputStream(this.soquete.getInputStream());
	}

	public void enviar_mensagem(Object mensagem) throws Exception {
		this.output.writeObject(mensagem);
	}
	
	public Object receber_mensagem() throws Exception {
		return this.input.readObject();
	}
	
	public void finalizar() throws IOException {
		this.soquete.close();
	}
        
        
    
	
	public static void main(String[] args) throws Exception {
            Scanner inputUser = new Scanner(System.in);
            
            while(true){
		Cliente cliente = new Cliente("127.0.0.1", 15500);
                System.out.print("-->");
                String mensagem = inputUser.nextLine(); 
                    if(mensagem.equalsIgnoreCase("sair")){
                        System.out.println("--Encenrrando Cojnexão--");
                        cliente.enviar_mensagem(mensagem);
                        break;
                        
                    }
                    cliente.enviar_mensagem(mensagem);
                    System.out.println("Erro");
                    
                    String resposta =(String)cliente.receber_mensagem();
                    
                    if(resposta.equalsIgnoreCase("sair")){
                        System.out.println("Servidor Encerrado");
                        break;
                    }
                    System.out.println("mensagem do servidor:" + resposta);
                    cliente.finalizar();
		
	}
}
}