
package se.kth.iv1350.iv1350upg.integration;

/**
 * This class represents an amount of money. Instances are immutable
 * @author Anders
 */
public class Amount {
    private final double amount;
    
    public Amount(double amount)
    {
        this.amount=amount;
    }
    
    public double getAmount()
    {
        return amount;
    }
}
