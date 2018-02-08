/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author jesse
 */
public interface VendingMachineDao {

    List<Item> getAllItems()
            throws VendingMachinePersistenceException;

    Item getItem(String itemNumber)
             throws VendingMachinePersistenceException;

    void purchaseItem(String itemNumber)
             throws VendingMachinePersistenceException;

}
