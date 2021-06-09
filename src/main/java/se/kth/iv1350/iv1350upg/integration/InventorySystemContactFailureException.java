/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.iv1350upg.integration;

import java.time.LocalDateTime;

/**
 * thrown when a database call fails
 * @author Anders
 */
public class InventorySystemContactFailureException extends Exception  {

    

    /**
     * Constructs an instance of <code>InventorySystemContactException</code>
     * 
     */
    public InventorySystemContactFailureException() {
        super("failed conection to the InventorySystem at:"+LocalDateTime.now());
    }
}
