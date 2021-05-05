package se.kth.iv1350.iv1350upg.model;

import se.kth.iv1350.iv1350upg.integration.Amount;
import se.kth.iv1350.iv1350upg.integration.ExternalSystemIntegration;

/**
 * This class represents the parts of the program thats not unique to the current sale.
 * @author Anders
 */
public class PointOfSale {
    
    private final Printer printer;
    private final Store store;
    private final CashRegister cashRegister;
    private final ExternalSystemIntegration externalSystems;
    
    
    /**
     * Creates an instance of the the PointOfSale class using hardcoded information. 
     */
    public PointOfSale()
    {
        printer = new Printer();
        Address storeAddress = new Address("affarsvagen 65",123567,"Stockholm","Sverige");
        store = new Store("Nyöppet",storeAddress);
        Amount cashInCashRegisterAtStartup = new Amount(1200);
        cashRegister = new CashRegister(cashInCashRegisterAtStartup);
        externalSystems = new ExternalSystemIntegration();
    }
    
    public Printer getPrinter()
    {
        return printer;
    }
    
    public Store getStore()
    {
        return store;
    }
    
    public CashRegister getCashRegister()
    {
        return cashRegister;
    }
    
    public ExternalSystemIntegration getExternalSystems()
    {
        return externalSystems;
    }
    
    
    
    
}
