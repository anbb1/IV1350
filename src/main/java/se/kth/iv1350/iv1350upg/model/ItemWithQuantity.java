/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.iv1350upg.model;

import se.kth.iv1350.iv1350upg.integration.ItemDTO;

/**
 * This class represents an item and and the quantity of said item. 
 * Immutable
 * @author Anders
 */
public class ItemWithQuantity {
    private ItemDTO item;
    private int quantity;
    
    ItemWithQuantity(ItemDTO item,int quantity)
    {
        this.item = item;
        this.quantity = quantity;
    }
    
    public ItemDTO getItem()
    {
        return item;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
}
