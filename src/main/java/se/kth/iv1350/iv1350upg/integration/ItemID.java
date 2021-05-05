/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.iv1350upg.integration;

/**
 * This class contains data used to identify different items. Immutable
 * @author Anders
 */
public class ItemID {
    private final int ID;
    /**
     * creates instance of
     * @param itemID Unique item identification 
     */
    public ItemID(int itemID)
    {
        ID=itemID;
    }
    
    public int getID(){
        return ID;
    }
}
