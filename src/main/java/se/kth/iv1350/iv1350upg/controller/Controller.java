
package se.kth.iv1350.iv1350upg.controller;

import se.kth.iv1350.iv1350upg.integration.Amount;
import se.kth.iv1350.iv1350upg.integration.InventorySystemContactFailureException;
import se.kth.iv1350.iv1350upg.integration.ItemDTO;
import se.kth.iv1350.iv1350upg.integration.ItemID;
import se.kth.iv1350.iv1350upg.integration.NoItemFoundException;
import se.kth.iv1350.iv1350upg.model.PaymentObserver;
import se.kth.iv1350.iv1350upg.model.PointOfSale;
import se.kth.iv1350.iv1350upg.model.SaleFinalizationStep;
import se.kth.iv1350.iv1350upg.model.SaleInfo;

/**
 * This is the programs only controller class.
 * @author Anders
 */
public class Controller 
{
    private final PointOfSale pointOfSale;
    private SaleInfo currentSale;
    
    public Controller()
    {
        pointOfSale = new PointOfSale();
        startNewSale();
    }
    
    /**
     * Initializes a new empty sale object
     */
    public void startNewSale()
    {
        currentSale = new SaleInfo(pointOfSale.getExternalSystems().getInventorySystem());
    }
    
    /**
     * adds a item of a stated quantity to the sale object
     * @param itemID Used to identify the item
     * @param quantity Specifies how many of the item to add   
     * @return The identified item
     * @throws se.kth.iv1350.iv1350upg.integration.NoItemFoundException when no matching itemID is found
     */
    public ItemDTO addItem(ItemID itemID,int quantity) throws NoItemFoundException
    {
        try {
            ItemDTO foundItem = currentSale.addItem(itemID, quantity);
            
            return foundItem;
        } catch (InventorySystemContactFailureException ex) {
            //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("                !intended for log! "+ex.getMessage()+" !intended for log!");
            return null;
        }
    }
    
    /**
     * adds an item to the sale object
     * @param itemID Used to identify the item
     * @return The identified item
     * @throws se.kth.iv1350.iv1350upg.integration.NoItemFoundException when no matching itemID is found
     */
    public ItemDTO addItemSingle(ItemID itemID) throws NoItemFoundException
    {
        return addItem(itemID,1);
    }
    
    /**
     * Communicates the amount to pay for current items
     * @return amount to pay for current items
     */
    public Amount updateRunningTotal()
    {
        return currentSale.getPriceWithVATaxTotal();
    }
    /**
     * signals that all items been added to sales object
     * @return amount to pay for items 
     */
    public Amount endSale()
    {
        return updateRunningTotal();
    }
    
    /**
     * Handles payment and Puts the finishing touches on the transaction.     
     * @param paid the amount paid by the curtomer
     * @return the amount of change to give the coustomer
     */
    public Amount Payment(Amount paid)
    {
        SaleFinalizationStep saleFinal = new SaleFinalizationStep(currentSale,pointOfSale);
        Amount change = saleFinal.Payment(paid);

        return change;
    }
    /**
     * adds a new observer
     * @param newPaymentObserver observer to be added
     */
    public void addPaymentObserver(PaymentObserver newPaymentObserver)
    {
      pointOfSale.getCashRegister().addPaymentObserver(newPaymentObserver);
    }
}
