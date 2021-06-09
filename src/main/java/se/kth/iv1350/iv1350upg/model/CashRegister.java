package se.kth.iv1350.iv1350upg.model;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.iv1350upg.integration.Amount;

/**
 * This class keeps track of the amount of cash currently in the cash register.
 * @author Anders
 */
public class CashRegister {
    private Amount cashInRegister;
    private List<PaymentObserver> paymentObservers = new ArrayList<>();
    private PaymentObserver paymentLogger = new TotalRevenueFileOutput();
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
        notifyObserver(amountToAdd);
    }
    
    public Amount getCashInRegister()
    {
        return cashInRegister;
    }
    
    /**
     * adds a new observer
     * @param newPaymentObserver observer to be added
     */
    public void addPaymentObserver(PaymentObserver newPaymentObserver)
    {
        paymentObservers.add(newPaymentObserver);
    }
    
    private void notifyObserver(Amount amountToAdd)
    {
        for(PaymentObserver obs : paymentObservers)
        {
            obs.newPayment(amountToAdd);
        }
        paymentLogger.newPayment(amountToAdd);
    }
}
