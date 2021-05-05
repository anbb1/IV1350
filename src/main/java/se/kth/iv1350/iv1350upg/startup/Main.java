/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.iv1350upg.startup;

import se.kth.iv1350.iv1350upg.controller.Controller;
import se.kth.iv1350.iv1350upg.view.View;

/**
 * Starts the application
 * @author Anders
 */
public class Main {
   /**
    * The main method used to start the application.
    * 
    * @param args The application does not take any command line parameters.
    */
    
    public static void main(String[] args){
        Controller controller = new Controller();
        View view = new View(controller);
        
        view.runStandardSale();
    }
}
