/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m2_vm.service;

import com.sg.m2_vm.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jesse
 */
public interface Service {
    
   public void purchaseItem();
    
   public List<Item> getAllItems();
            
    public Item getItem(String itemNumber);

    public String getItemSelected();
    
    public void setItemSelected(String itemNumber);

    public void addChange(BigDecimal amount);

    public BigDecimal getAmountEntered();

    public String getMessage();

    public String getChangeMessage();

    public void changeReturn();

}
