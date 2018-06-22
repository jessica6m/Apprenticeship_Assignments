/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m2_vm.dao;

import com.sg.m2_vm.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jesse
 */
public class VendingMachineDaoImpl implements VendingMachineDao{

    private Map<String, Item> items = new HashMap<>();
    private static String _itemNumber;
    private static BigDecimal _cash;

    public VendingMachineDaoImpl() {
        Item item = new Item("1");
        item.setItemName("Coke");
        item.setItemPrice(new BigDecimal("1.50"));
        item.setItemQuantity(7);

        Item item2 = new Item("2");
        item2.setItemName("Sprite");
        item2.setItemPrice(new BigDecimal("3.00"));
        item2.setItemQuantity(9);

        Item item3 = new Item("3");
        item3.setItemName("Diet Coke");
        item3.setItemPrice(new BigDecimal("1.50"));
        item3.setItemQuantity(8);

        Item item4 = new Item("4");
        item4.setItemName("Ice Tea");
        item4.setItemPrice(new BigDecimal("1.50"));
        item4.setItemQuantity(1);

        Item item5 = new Item("5");
        item5.setItemName("Lemonade");
        item5.setItemPrice(new BigDecimal("2.00"));
        item5.setItemQuantity(6);

        Item item6 = new Item("6");
        item6.setItemName("Water");
        item6.setItemPrice(new BigDecimal("1.50"));
        item6.setItemQuantity(10);

        Item item7 = new Item("7");
        item7.setItemName("Orange Juice");
        item7.setItemPrice(new BigDecimal("1.50"));
        item7.setItemQuantity(7);

        Item item8 = new Item("8");
        item8.setItemName("Apple Juice");
        item8.setItemPrice(new BigDecimal("3.00"));
        item8.setItemQuantity(10);

        Item item9 = new Item("9");
        item9.setItemName("Red Bull");
        item9.setItemPrice(new BigDecimal("3.50"));
        item9.setItemQuantity(10);

        items.put("1", item);
        items.put("2", item2);
        items.put("3", item3);
        items.put("4", item4);
        items.put("5", item5);
        items.put("6", item6);
        items.put("7", item7);
        items.put("8", item8);
        items.put("9", item9);
        
        this._cash = new BigDecimal(0);
        this._itemNumber = "";
    }

    @Override
    public Item getItem(String itemNumber) {
      return items.get(itemNumber);
    }

    @Override
    public List<Item> listItems() {
      return new ArrayList(items.values());
    }

    @Override
    public String getItemSelected() {
        return this._itemNumber;
    }
    
    @Override
    public void setItemSelected(String itemNumber) {
        this._itemNumber = itemNumber;
    }

    @Override
    public void addChange(BigDecimal amount) {
      this._cash = this._cash.add(amount);
    }

    @Override
    public BigDecimal getAmountEntered() {
       return this._cash;
    }
    
    @Override
    public void setDeposit(BigDecimal deposit){
     this._cash = deposit;
    }
    
}
