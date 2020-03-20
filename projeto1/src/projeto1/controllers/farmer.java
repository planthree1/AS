/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto1.controllers;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
import java.util.concurrent.locks.ReentrantLock; 
/**
 *
 * @author TiagoRodrigues
 */
public class farmer implements  Runnable  {
    int max_steps;
    int timeout;
    int position;
    ReentrantLock re; 
        
    public farmer(int max_steps, int timeout, ReentrantLock re){
        this.max_steps = max_steps;
        this.timeout = timeout;
    }

    
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
