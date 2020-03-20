package projeto1.controllers;
import java.util.ArrayList;
import java.io.*; 
import java.util.*; 

/**
 * Arquitetura de Software - Project 1 ::
 * The "practical assignment I" is focused on an architecture where concurrency (processes and threads) is key aspect.
 * @author TiagoRodrigues and DanteMarinho
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        int number_farmers = 0;
        
        ArrayList<String> storehouse = new ArrayList<>();
        ArrayList<String> standing_area = new ArrayList<>();
        // decidir a estrutura de dados a utilizar para o caminho
        ArrayList<String> granary = new ArrayList<>();
        
        // Open Control Center Window
        //FrameControlCenter controlCenter = new FrameControlCenter();
        // controlCenter.pack();
        // controlCenter.setLocation(300, 100);
        // controlCenter.setVisible(true);
        
        // Open Farm Infrasructure Window
        //FrameFarmInfrastructure farmInfrastructure = new FrameFarmInfrastructure();
        //farmInfrastructure.pack();
        //farmInfrastructure.setLocation(300, 340);
        //farmInfrastructure.setVisible(true);
        
        // Calls a monitor with the amount of farmers and ste
        monitor newMonitor = new monitor(3, 5, 4);
        newMonitor.Stop(true);
    }
    
    
}


