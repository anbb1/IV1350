/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.iv1350upg.model;

import java.util.ArrayList;
import se.kth.iv1350.iv1350upg.integration.Amount;
import se.kth.iv1350.iv1350upg.integration.InventorySystem;
import se.kth.iv1350.iv1350upg.integration.ItemDTO;
import se.kth.iv1350.iv1350upg.integration.ItemID;

/**
 * This class contains information about a current sale.
 * @author Anders
 */
public class SaleInfo 
{
    private ArrayList<ItemWithQuantity> itemList;
    private Amount itemPriceTotal;
    private Amount itemVATaxTotal;
    private final InventorySystem inventorySystem;
    
    /**
     * creates an new empty instance of the class.
     * @param inventorySystem lets the class contact the external inventory database 
     */
    public SaleInfo(InventorySystem inventorySystem)
    {
        this.inventorySystem=inventorySystem;
        itemPriceTotal=new Amount(0);
        itemVATaxTotal=new Amount(0);
        itemList=new ArrayList<>();
    }
    /**
     * adds an item with matching item id to the sale
     * @param itemID used to identify the item.
     * @param quantity of the identifed item that should be added to the list.
     * @return item with matching itemID.
     */
    public ItemDTO addItem(ItemID itemID,int quantity)
    {
        ItemDTO addedItem=null;
        if(quantity>0&&itemID!=null)
        {
            int FoundItemIndex = searchListForItem(itemID);
            if(FoundItemIndex>=0)
            {
                addedItem=increaseQuantityOfItem(FoundItemIndex,quantity);
                updateTotals(addedItem,quantity);
            }
            else
            {
                addedItem = inventorySystem.fetchInventoryInfo(itemID);
                if(addedItem!=null)
                {
                    addNewItem(addedItem,quantity);
                    updateTotals(addedItem,quantity);
                }
            }
        }
        return addedItem;
    }
    
    /**
     * Lets other parts of the program read whats in the item list.
     * @return a copy of the item list
     */
    public ArrayList<ItemWithQuantity> getItemList()
    {
        ArrayList<ItemWithQuantity> itemListCopy = (ArrayList<ItemWithQuantity>)itemList.clone();
        return itemListCopy;
    }
    
    public Amount getItemPriceTotal()
    {
        return itemPriceTotal;
    }
    
    public Amount getItemVATaxTotal()
    {
        return itemVATaxTotal;
    }
    
    /**
     * Provides the total value of all items entered during the current sale, including VATax.
     * @return the items total price including VATax
     */
    public Amount getPriceWithVATaxTotal()
    {   
        return addToAmount(itemPriceTotal,itemVATaxTotal.getAmount());
    }
    
    
    private int searchListForItem(ItemID itemID)
    {
        int indexOfFoundItem = -1;
        boolean itemFound=false;
        if(itemList.size()>0)
        {
            for(int i=0; i<itemList.size() && itemFound==false; i++)
            {
                itemFound = sameItemID(itemList.get(i),itemID);
                if(itemFound)
                {
                    indexOfFoundItem = i;
                }
            }
        }
        return indexOfFoundItem;
    }

    private boolean sameItemID(ItemWithQuantity listItem,ItemID searchedForItem)
    {
            return listItem.getItem().getItemID().getID()==searchedForItem.getID();
    }

    private ItemDTO increaseQuantityOfItem(int listIndex,int quantityToAdd)
    {
        ItemDTO item = itemList.get(listIndex).getItem();
        int quantityInList = itemList.get(listIndex).getQuantity();
        
        int newQuantity = quantityInList + quantityToAdd;
        ItemWithQuantity increasedQuantityItem = new ItemWithQuantity(item,newQuantity);
        itemList.set(listIndex, increasedQuantityItem);
        
        return item;
    }
    
    private void addNewItem(ItemDTO itemToAdd, int quantity)
    {
        ItemWithQuantity newListItem= new ItemWithQuantity(itemToAdd,quantity);
        itemList.add(newListItem);
    }
        
    private void updateTotals(ItemDTO currentItem,int addedQuantity)
    {
        double itemPrice=currentItem.getPrice().getAmount();
        int itemVATax=currentItem.getVATax();
        
        double totalPriceToAdd =(double)itemPrice*addedQuantity;
        double totalVATaxToAdd =totalPriceToAdd*((double)itemVATax/100);
            
        itemPriceTotal = addToAmount(itemPriceTotal,totalPriceToAdd);
        itemVATaxTotal =  addToAmount(itemVATaxTotal,totalVATaxToAdd);
        
    }


    private Amount addToAmount(Amount currentAmount,double toBeAdded)
    {
        Amount finalAmount = new Amount(currentAmount.getAmount()+toBeAdded);
        return finalAmount;
    }

}   