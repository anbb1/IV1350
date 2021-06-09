package se.kth.iv1350.iv1350upg.model;

import se.kth.iv1350.iv1350upg.integration.Amount;

/**
 * Sends information about amount Paid
 * @author Anders
 */
public interface PaymentObserver {
    /**
     * Invoked when a sale has ended
     * @param paid the amount paid by the customer
     */
    void newPayment(Amount paid);
}
