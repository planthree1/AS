/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
import java.util.concurrent.locks.ReentrantLock; 

/**
 *
 * @author TiagoRodrigues
 */
public class monitor {
    // We are using this
    int nFarmers;
    int maxSteps;
    int timeOut;
    boolean stop = false;
    ReentrantLock re;
    
    // maybe we will use this
    boolean allFarmersIn;
    boolean prepare;
    boolean start;
    boolean collect;
    boolean returnStorehouse;
    boolean exit;
    
    int[][] matrix = new int[3][3];
    
    public monitor(int nFarmers, int maxSteps, int timeOut){
        this.nFarmers = nFarmers;
        this.maxSteps = maxSteps;
        this.timeOut = timeOut;
        
        System.out.println("number of farmers: " + nFarmers);
        
        
    }
    
    //ArrayList<Integer> farmer = new ArrayList<Integer>();
    //for (i = 0; i< nFarmers ; i++){
    //    farmer.add(1);
    //    farmer.add(2);
    //    farmer.add(3);
    //    System.out.println(farmer);
    //}
    
    // Move foward function
    
    // Show's the state of the matrix
    public static void print2D(int mat[][]){ 
        System.out.println("\nmatrix");
        // Loop through all rows 
        for (int[] row : mat){
            System.out.println(Arrays.toString(row));
        } 
    }
    
    public void Stop(boolean True){
        this.stop = True;
    }
    
    public void some_method() { 
        re.lock(); 
        try
        { 
            //Do some work 
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
        finally
        { 
            re.unlock(); 
        } 
          
} 
    
    
}
