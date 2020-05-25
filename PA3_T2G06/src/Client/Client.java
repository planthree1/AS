/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.IOException;
import Constants.Constants;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author TiagoRodrigues
 */
public class Client {
    
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(Constants.serverIP, Constants.serverPort);
        
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        while (true){
            System.out.println(">");
            String command = keyboard.readLine();
            if  (command.equals("quit")) break;
            
            // here the info is sent to the server gotta change it to the format    | client id | request id | 01 | number of iterations |
            out.println(command);
            
            String serverResponse = input.readLine();
            System.out.println("Server says: " + serverResponse);
        }
        
        
        socket.close();
        System.exit(0);
    }
}
