/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m2_vm.dao;

import com.sg.m2_vm.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jesse
 */
public interface VendingMachineDao {
    
//    public void purchaseItem(String itemNumber);
    
    public Item getItem(String itemNumber);
    
    public List<Item> listItems();

    public String getItemSelected();

    public void addChange(BigDecimal amount);

    public BigDecimal getAmountEntered();
    
    public void setItemSelected(String itemNumber);
    
    public void setDeposit(BigDecimal deposit);

}
