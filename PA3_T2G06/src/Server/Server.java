/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Constants.Constants;
import ClientHandler.ClientHandlerThread;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author TiagoRodrigues
 */
public class Server {
    
    private static ArrayList<ClientHandlerThread> clients = new ArrayList<ClientHandlerThread>();
    private static ExecutorService pool = Executors.newFixedThreadPool(4);
    
    public static void main(String[] args) throws IOException {
        
        ServerSocket listener = new ServerSocket(Constants.serverPort);
        System.out.println("[SERVER] waiting for client connection...");
        Socket client = listener.accept();
        System.out.println("[SERVER] connected to client!.");
        
        ClientHandlerThread clientThread = new ClientHandlerThread(client);
        clients.add(clientThread);
        
        // Cria uma thread para cada mensagem recebida por um cliente 
        pool.execute(clientThread);
    }
    
}
