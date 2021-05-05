package se.kth.iv1350.iv1350upg.model;

import se.kth.iv1350.iv1350upg.integration.Amount;

/**
 * This class keeps track of the amount of cash currently in the cash register.
 * @author Anders
 */
public class CashRegister {
    private Amount cashInRegister;
    
    /**
     * creates an incstance of CashRegister
     * @param cashInRegister cash in cashRegister at system start
     */
    CashRegister(Amount cashInRegister)
    {
        this.cashInRegister = cashInRegister;
    }
    
    /**
     * Adds a specified amount to the cash register
     * @param amountToAdd specifies the added amount
     */
    void addAmountToCashRegister(Amount amountToAdd)
    {
        cashInRegister= new Amount(amountToAdd.getAmount()+cashInRegister.getAmount());
    }
    
    
    public Amount getCashInRegister()
    {
        return cashInRegister;
    }
}
