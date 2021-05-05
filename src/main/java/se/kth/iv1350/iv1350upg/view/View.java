
package se.kth.iv1350.iv1350upg.view;

import se.kth.iv1350.iv1350upg.controller.Controller;
import se.kth.iv1350.iv1350upg.integration.Amount;
import se.kth.iv1350.iv1350upg.integration.ItemDTO;
import se.kth.iv1350.iv1350upg.integration.ItemID;

/**
 * This is a placeholder view that contains a hardcoded sale scenario.
 * @author Anders
 */
public class View 
{
    private Controller controller;
    
    public View(Controller controller)
    {
        this.controller=controller;
    }
    
    public void runStandardSale()
    {
        controller.startNewSale();
        addItemsToSale();
        Amount toPay = endSale();
        Amount paid = new Amount(toPay.getAmount()+50); 
        
        
        controller.Payment(paid);
    }
    
    
    private void addItemsToSale()
    {
        
        
        ItemID[] IDList= new ItemID[8];
        
        for(int i=0; i<8;i++)
        {
            IDList[i]= new ItemID(i+1);
        }
        
        addItem(IDList[2],1);
        addItem(IDList[3],1);
        addItem(IDList[2],1);
        addItem(IDList[3],1);
        addItem(IDList[1],1);
        addItem(IDList[6],2);
        
        
    }

    private void addItem(ItemID itemID,int quantity)
    {
        ItemDTO foundItem= null;
        foundItem = controller.addItem(itemID,quantity);
        if(foundItem!=null)
        {
            System.out.println(itemDTOString(foundItem));
        }
        else
        {
            System.out.println("***ItemID is invalid***");
        }
        System.out.println("Running total:" + String.format("%.2f",controller.updateRunningTotal().getAmount()) + " Sek");
    }
    
    private String itemDTOString(ItemDTO item)
    {
        String DTOString = "-----------" + 
                "\nname: "+item.getName() + 
                "\nID: "+item.getItemID().getID() + 
                "\nPrice: " + item.getPrice().getAmount() + "Sek " + 
                "\nVATax: "+item.getVATax() + "%" + 
                "\nDescription: "+item.getItemDescription();
        return DTOString;
    }
    
    private Amount endSale()
    {
        Amount toPay = controller.endSale();
        System.out.println("************\nTotal Amount To Pay: "+String.format("%.2f", toPay.getAmount()));
        return toPay;
    }
}


