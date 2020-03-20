/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto1.controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock; 
import static projeto1.controllers.work.MAX_T;

/**
 *
 * @author TiagoRodrigues
 */
public class monitor {
    // We are using this
    int nFarmers;
    int maxSteps;
    int timeOut;
    String teste = "teste";

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public String getTeste() {
        return teste;
    }
    
    
    // locks
    static final int MAX_T = 2; 
    ReentrantLock rel = new ReentrantLock(); 
    ExecutorService pool = Executors.newFixedThreadPool(MAX_T);    
    
    public monitor(int nFarmers, int maxSteps, int timeOut){
        this.nFarmers = nFarmers;
        this.maxSteps = maxSteps;
        this.timeOut = timeOut;
        
        int[][] matrix = new int[nFarmers][nFarmers];
        
        System.out.println("number of farmers: " + nFarmers);
        
        Runnable w1 = new work(rel, "1", maxSteps, this);
        Runnable w2 = new work(rel, "2", maxSteps, this);
        Runnable w3 = new work(rel, "3", maxSteps, this);
        Runnable w4 = new work(rel, "4", maxSteps, this);
        pool.execute(w1);
        pool.execute(w2);
        pool.execute(w3);
        pool.execute(w4);
        pool.shutdown();
        
    }
    public static void print2D(int mat[][]){ 
        System.out.println("\nmatrix");
        // Loop through all rows 
        for (int[] row : mat){
            System.out.println(Arrays.toString(row));
        } 
    }
}
