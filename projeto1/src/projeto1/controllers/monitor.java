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
    int row = 4;
    int column = 10;
    int nFarmers;
    int maxSteps;
    int timeOut;
    boolean stop = false;
    int[][] matrix = new int[row][column];

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public boolean isStop() {
        return stop;
    }
    
    
    
    // locks
    static final int MAX_T = 2; 
    ReentrantLock rel = new ReentrantLock(); 
    ExecutorService pool = Executors.newFixedThreadPool(MAX_T);    
    
    public monitor(int nFarmers, int maxSteps, int timeOut) throws InterruptedException{
        this.nFarmers = nFarmers;
        this.maxSteps = maxSteps;
        this.timeOut = timeOut;
        
        Runnable w1 = new work(rel, 1, maxSteps, this);
        Runnable w2 = new work(rel, 2, maxSteps, this);
        Runnable w3 = new work(rel, 3, maxSteps, this);
        Runnable w4 = new work(rel, 4, maxSteps, this);
        
        for (int i = 0; i < 9; i++){
            pool.execute(w1);
            pool.execute(w2);
            pool.execute(w3);
            pool.execute(w4);
        }
        
        pool.shutdown();
        
    }
    public static void print2D(int mat[][]){ 
        System.out.println("\nmatrix");
        // Loop through all rows 
        for (int[] row : mat){
            System.out.println(Arrays.toString(row));
        } 
    }
    
    public static int[][] ClearLastStep(int x,int[][] matrix, int id){
        for (int j = 0; j < 4; j++) {
            if (matrix[j][x-1] == id){
                matrix[j][x-1] = 0;
                
            }
        }
        
        return matrix;
    }
}
