/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientHandler;

import static Server.Server.serverFrame;
import Server.ServerFrame;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static Server.Server.writeMessageToServerFrame;

/**
 *
 * @author TiagoRodrigues
 */
public class ClientHandlerThread implements Runnable{
    
    // Lida com a mensagem recebida no servidor
    
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    // public static ServerFrame serverFrame = new ServerFrame();

    public ClientHandlerThread(Socket loadBalancer) throws IOException{
        this.client = loadBalancer;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
        
    }
    
    @Override
    public void run() {
        try{
            while(true){
                String request = in.readLine();
                System.out.println("ClientHandlerThread :: ");
                System.out.println(request);
                writeMessageToServerFrame("Precessing request: " + request);
                
                // mudar este if para aceitar o formato | client id | request id | 01 | number of iterations |
                if (request.contains("pi")){
                    double response = getPiValue(2);
                    writeMessageToServerFrame("Sending response: " + response);
                    out.println(response);
                    out.println("asdas");
                } else {
                    out.println("type pi");
                }
            }
            
        } catch(IOException|InterruptedException e){
            
            System.err.println("IOException Load balancer");
            System.err.println(e);
        }   finally {
            
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static double getPiValue(int i) throws InterruptedException{
        Thread.sleep(i * 1000);
        return 3.1416;
    }
    
}
