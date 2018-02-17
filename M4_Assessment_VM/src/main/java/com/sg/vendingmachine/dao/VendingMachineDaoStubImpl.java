/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;


import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jesse
 */
 
public class VendingMachineDaoStubImpl implements  VendingMachineDao{

     private Map<String, Item> items = new HashMap<>();
 
 
    
    public VendingMachineDaoStubImpl() {
        Item item = new Item("1");
        item.setItemName("Coke");
        item.setItemPrice(new BigDecimal ("1.50"));
        item.setItemQuantity(10);
        items.put("1",item);
       
        Item item2 = new Item("2");
        item2.setItemName("Sprite");
        item2.setItemPrice(new BigDecimal ("3.00"));
        item2.setItemQuantity(10);
        
        Item item3 = new Item("3");
        item3.setItemName("CherryCoke");
        item3.setItemPrice(new BigDecimal ("1.50"));
        item3.setItemQuantity(10);
        
        items.put("1",item);
        items.put("2",item2);
        items.put("3",item3);
    }

   
    @Override
    public List<Item> getAllItems()
            throws VendingMachinePersistenceException {

        return new ArrayList<>(items.values());
    }

    @Override
    public Item getItem(String itemNumber)
            throws VendingMachinePersistenceException {
        return items.get(itemNumber);
    }

    @Override
    public void purchaseItem(String itemNumber) 
        throws VendingMachinePersistenceException {
        
       Item item = getItem(itemNumber);
       item.purchaseItem();
        
      
    }

}
