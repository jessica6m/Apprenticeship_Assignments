/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jesse
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getAllItems() throws 
            VendingMachinePersistenceException;
     
      Item getItem(String itemNumber) throws
            VendingMachinePersistenceException;
      
       String purchaseItem(String itemNumber, BigDecimal deposit) throws
            VendingMachinePersistenceException,
            InsufficientFundsException, 
            NoItemInventoryException;
       
    
}
