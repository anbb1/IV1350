/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.iv1350upg.integration;

/**
 * This class makes the database handlers in the integration layer accessible from other layers.
 * @author Anders
 */
public class ExternalSystemIntegration {
    
    private final AccountingSystem accountingSystem;
    private final InventorySystem inventorySystem;
    
    /**
     * creates an instance of this class.
     * creats and saves instances of database handlers.
     */
    public ExternalSystemIntegration()
    {
        accountingSystem = new AccountingSystem();
        inventorySystem = new InventorySystem();
    }
    
    public AccountingSystem getAccountingSystem(){
        return accountingSystem;
    }
    
    public InventorySystem getInventorySystem(){
        return inventorySystem;
    }
    
}
