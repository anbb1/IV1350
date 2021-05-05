
package se.kth.iv1350.iv1350upg.model;

import se.kth.iv1350.iv1350upg.integration.Amount;

/**
 * This class handles the payment and the post processing of the sale.
 * @author Anders
 */
public class SaleFinalizationStep 
{
    private final SaleInfo currentSale;
    private final PointOfSale pointOfSale;
    private Receipt receipt;
    
    public SaleFinalizationStep(SaleInfo currentSale,PointOfSale pointOfSale)
    {
        this.currentSale = currentSale;
        this.pointOfSale = pointOfSale;
        receipt = null;
    }
    /**
     * Processes the payment.
     * If the payment is sufficient then  the sale will be recorded externally
     * @param paid Amount paid by the customer
     * @return the amount of change 
     */
    public Amount Payment(Amount paid)
    {
        Payment payment = new Payment(currentSale.getPriceWithVATaxTotal(),pointOfSale.getCashRegister());
        Amount change = payment.handelPayment(paid);
        if(0<=change.getAmount())
        {
            handelReceipt(payment);
            handelInventorySystem();
            handelAccountingSystem();
        }
        else
        {
            //payment failed
        }
        return change;
    }
    
    private void handelReceipt(Payment payment)
    {
        receipt = new Receipt(currentSale,pointOfSale.getStore(),payment);
        String receiptString = receipt.createReceiptString();
        pointOfSale.getPrinter().printReceipt(receiptString);
    }
    
    private void handelAccountingSystem()
    {
        AccountingInformation accountingInfo = new AccountingInformation(receipt);
        pointOfSale.getExternalSystems().getAccountingSystem().sentSaleInformation(accountingInfo);
    }
    
    private void handelInventorySystem()
    {
        pointOfSale.getExternalSystems().getInventorySystem().updateInventory(currentSale.getItemList());
    }
}
