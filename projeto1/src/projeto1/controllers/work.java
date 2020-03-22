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
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class work implements Runnable {

    static final int MAX_T = 10;
    int name;
    ReentrantLock re;
    int maxSteps;
    monitor m;
    int posX = 0;
    int posY = 0;
    int possiblePosition;
    int[][] tempMatrix;
    boolean foundTheEnd = false;

    public work(ReentrantLock rl, int n, int maxSteps, monitor m) {
        re = rl;
        name = n;
        maxSteps = maxSteps;
        this.m = m;
    }

    public void run() {
        boolean done = false;
        while (!done) {
            //Getting Outer Lock 
            boolean ans = re.tryLock();
            // Returns True if lock is free 
            if (ans) {
                try {
                    // Getting Inner Lock 
                    re.lock();
                    Thread.sleep(1500);
                    try {
                        Thread.sleep(1500);
                        
                        if (m.isStop()) {
                            done = true;
                        }
                        
                        if (posX <= (m.column - 1)) {
                            // finds a viable option to move forward
                            boolean viableOption = false;
                            tempMatrix = m.getMatrix();
                            while (!viableOption) {
                                possiblePosition = getRandomNumberInRange(0, 3);
                                if (tempMatrix[possiblePosition][posX] == 0) {
                                    viableOption = true;
                                }
                            }
                        } else {
                            foundTheEnd = true;
                            done = true;
                        }

                        if (posX != 0) {
                            tempMatrix = m.ClearLastStep(posX, tempMatrix, name);
                            m.setMatrix(tempMatrix);
                        }

                        // moves to the next space
                        tempMatrix[possiblePosition][posX] = name;
                        m.setMatrix(tempMatrix);

                        posX++;
                        posY = possiblePosition;
                        print2D(tempMatrix);
                        // Trying to get inner lock
                        
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        //Inner lock release
                        //System.out.println("farmer id: " + name + " releasing inner lock");
                        re.unlock();
                    }
                    //System.out.println("Lock Hold Count - " + re.getHoldCount()); 
                    //System.out.println("task name - " + name + " work done"); 
  
                    done = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //Outer lock release 
                    //System.out.println("farmer id: " + name + " releasing outer lock");
                    re.unlock();
                    //System.out.println("Lock Hold Count - " + re.getHoldCount());
                }
            } else {
                //System.out.println("farmer id: " + name + " waiting for lock to be open");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max needs to be bigger than the minimum");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static void print2D(int mat[][]) {
        System.out.println("\nmatrix");
        // Loop through all rows 
        for (int[] row : mat) {
            System.out.println(Arrays.toString(row));
        }
    }

}
