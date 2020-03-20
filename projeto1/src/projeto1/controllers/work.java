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



public class work implements Runnable 
{ 
    static final int MAX_T = 5;
  String name; 
  ReentrantLock re;
  int maxSteps;
  monitor m;
  
  public work(ReentrantLock rl, String n, int maxSteps, monitor m) 
  { 
    re = rl; 
    name = n;
    maxSteps = maxSteps;
    this.m = m;
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
            m.setTeste(name);
            
            System.out.println(m.getTeste());
            // Trying to get inner lock
            Thread.sleep(1500); 
          } 
          catch(InterruptedException e) 
          { 
            e.printStackTrace(); 
          } 
          finally
          { 
            //Inner lock release
            System.out.println("farmer id: " + name + " releasing inner lock"); 
  
            re.unlock(); 
          } 
          System.out.println("Lock Hold Count - " + re.getHoldCount()); 
          System.out.println("farmer id: " + name + "moved to the desired place"); 
  
          done = true; 
        } 
        catch(InterruptedException e) 
        { 
          e.printStackTrace(); 
        } 
        finally
        { 
          //Outer lock release 
          System.out.println("farmer id: " + name + " releasing outer lock"); 
  
          re.unlock(); 
          System.out.println("Lock Hold Count - " + re.getHoldCount()); 
        } 
      } 
      else
      { 
        System.out.println("farmer id: " + name + " waiting for lock to be open"); 
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
  

