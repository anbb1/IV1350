package se.kth.iv1350.iv1350upg.model;

import java.time.LocalDateTime;

/**
 * this class represents the receipt
 * @author Anders
 */
public class Receipt 
{
    private final SaleInfo currentSale;
    private final Store store;
    private final Payment payment;
    private LocalDateTime saleTimeAndDate;
    
    
    Receipt(SaleInfo currentSale,Store store,Payment payment)
    {
        this.currentSale = currentSale;
        this.store = store;
        this.payment = payment;
        setTimeAndDate();
    }
    
    private void setTimeAndDate()
    {    
        saleTimeAndDate= LocalDateTime.now();
    }
    /**
     * Creates a printable information string
     * @return string containing receipt information
     */
    public String createReceiptString()
    {
        String receipt="\n################################\n" +
                "Receipt\n"+store.getName()+"\n" +
                store.getAddress().getStreetAddress() + 
                "\n" + saleTimeAndDate.toString()+"\n\n";
        
        for (ItemWithQuantity itemList : currentSale.getItemList()) 
        {
            receipt = receipt + receiptItemString(itemList)+"\n";
        }
        receipt = receipt + "********************************\nTotal: " + 
                String.format("%.2f",currentSale.getItemPriceTotal().getAmount()) + "sek" +
                "\nVAT:" + String.format("%.2f",currentSale.getItemVATaxTotal().getAmount()) + "sek" + 
                "\nPaid: " + String.format("%.2f",payment.getPaid().getAmount()) + "sek" + 
                "\nChange: " + String.format("%.2f",payment.getChange().getAmount()) + "sek" + 
                "\n################################\n";
 
        return receipt;
    }
    
    private String receiptItemString(ItemWithQuantity item)
    {
        String itemString = item.getItem().getName()+"        ";
        if(1<item.getQuantity())
        {
            itemString=itemString + item.getQuantity() + " * ";
        }
        itemString = itemString + item.getItem().getPrice().getAmount()+"sek";
        return itemString;
    }
    
    
}
