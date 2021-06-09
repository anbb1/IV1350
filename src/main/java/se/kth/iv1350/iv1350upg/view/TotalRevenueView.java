package se.kth.iv1350.iv1350upg.view;

import se.kth.iv1350.iv1350upg.integration.Amount;
import se.kth.iv1350.iv1350upg.model.PaymentObserver;

/**
 * shows the amount paid since programs start
 * @author Anders
 */
public class TotalRevenueView implements PaymentObserver {
    private Amount totalPaid;
    
    public TotalRevenueView()
    {
        totalPaid=new Amount(0);
    }

    @Override
    public void newPayment(Amount paid) {
        totalPaid = new Amount(totalPaid.getAmount() + paid.getAmount());
        System.out.println("\n@@@ Total Amount paid during current session:" + String.format("%.2f", totalPaid.getAmount()) + " sek @@@");
    }
    
   
}
