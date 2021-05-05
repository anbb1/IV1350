
package se.kth.iv1350.iv1350upg.model;

import se.kth.iv1350.iv1350upg.integration.Amount;

/**
 * This class handels payment
 * @author Anders
 */
public class Payment {
    
    private final Amount toPay;
    private final CashRegister cashRegister;
    private Amount paid;
    private Amount change;
   
    
    Payment(Amount toPay,CashRegister cashRegister)
    {
        this.toPay = toPay;
        this.cashRegister = cashRegister;
        paid=null;
        change=null;
        
    }
    
    /**
     * Checks if the difference between the amount paid and the cost of the products. 
     * If the amount paid is sufficient then the cashRegister will be updated. 
     * @param paid The amount the coustumer paid
     * @return The difference between the amount paid and the amount to pay.
     */
    public Amount handelPayment(Amount paid)
    {
        if(paid!=null)
        {
            this.paid=paid;
            double difference = paid.getAmount() - toPay.getAmount();
            if(0<=difference)
            {
                cashRegister.addAmountToCashRegister(toPay);
            }        

            change= new Amount(difference);
        }
        return change;
    }
    
    public Amount getPaid()
    {
        return paid;
    }
    public Amount getChange()
    {
        return change;
    }
}
