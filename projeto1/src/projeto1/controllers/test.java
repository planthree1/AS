/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto1.controllers;

/**
 *
 * @author TiagoRodrigues
 */
// Java code to illustrate Reentrant Locks 
import java.text.SimpleDateFormat; 
import java.util.Date; 
import java.util.concurrent.ExecutorService; 
import java.util.concurrent.Executors; 
import java.util.concurrent.locks.ReentrantLock; 
  
class worker implements Runnable 
{ 
  String name; 
  ReentrantLock re; 
  int[][] matrix = new int[3][3];
  
  public worker(ReentrantLock rl, String n) 
  { 
    re = rl; 
    name = n; 
  } 
  public void run() 
  { 
    boolean done = false; 
    while (!done) 
    { 
      //Getting Outer Lock 
      boolean ans = re.tryLock(); 
  
      // Returns True if lock is free 
      if(ans) 
      { 
        try
        { 
          Thread.sleep(1500); 
          // Getting Inner Lock 
          re.lock(); 
          try
          { 
            d = new Date(); 
            ft = new SimpleDateFormat("hh:mm:ss"); 
            System.out.println("task name - "+ name 
                       + " inner lock acquired at "
                       + ft.format(d) 
                       + " Doing inner work"); 
            System.out.println("Lock Hold Count - "+ re.getHoldCount()); 
            Thread.sleep(1500); 
          } 
          catch(InterruptedException e) 
          { 
            e.printStackTrace(); 
          } 
          finally
          { 
            //Inner lock release 
            System.out.println("task name - " + name + 
                       " releasing inner lock"); 
  
            re.unlock(); 
          } 
          System.out.println("Lock Hold Count - " + re.getHoldCount()); 
          System.out.println("task name - " + name + " work done"); 
  
          done = true; 
        } 
        catch(InterruptedException e) 
        { 
          e.printStackTrace(); 
        } 
        finally
        { 
          //Outer lock release 
          System.out.println("task name - " + name + 
                     " releasing outer lock"); 
  
          re.unlock(); 
          System.out.println("Lock Hold Count - " + 
                       re.getHoldCount()); 
        } 
      } 
      else
      { 
        System.out.println("task name - " + name + 
                      " waiting for lock"); 
        try
        { 
          Thread.sleep(1000); 
        } 
        catch(InterruptedException e) 
        { 
          e.printStackTrace(); 
        } 
      } 
    } 
  } 
} 
  
public class test 
{ 
  static final int MAX_T = 2; 
  public static void main(String[] args) 
  { 
    ReentrantLock rel = new ReentrantLock(); 
    ExecutorService pool = Executors.newFixedThreadPool(MAX_T); 
    Runnable w1 = new worker(rel, "Job1"); 
    Runnable w2 = new worker(rel, "Job2"); 
    Runnable w3 = new worker(rel, "Job3"); 
    Runnable w4 = new worker(rel, "Job4"); 
    pool.execute(w1); 
    pool.execute(w2); 
    pool.execute(w3); 
    pool.execute(w4); 
    pool.shutdown(); 
  } 
} 
