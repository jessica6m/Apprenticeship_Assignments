/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m2_vm.service;

import com.sg.m2_vm.Change;
import com.sg.m2_vm.Item;
import com.sg.m2_vm.dao.VendingMachineDao;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jesse
 */
public class ServiceLayerImpl implements Service {

    VendingMachineDao dao;
    Change change;
    String message;
    String changeMessage;

    public ServiceLayerImpl(VendingMachineDao dao, Change change) {
        this.dao = dao;
        this.change = change;
        this.message = "";
        this.changeMessage = "";
    }

    @Override
    public void purchaseItem() {
        Item vendingItem = dao.getItem(dao.getItemSelected());
        BigDecimal amountEntered = dao.getAmountEntered();

        if (dao.getItemSelected().isEmpty()) {
            message = "Please Select an Item  <------";
        } else if (vendingItem.getItemQuantity() <= 0) {
            message = "SOLD OUT!";
        } else if (amountEntered.compareTo(vendingItem.getItemPrice()) < 0) {
            BigDecimal difference = vendingItem.getItemPrice().subtract(amountEntered);
            message = "Please enter $" + difference;
        } else {
            change.makeChange(vendingItem, amountEntered);
            int newInventory = vendingItem.getItemQuantity() - 1;
            vendingItem.setItemQuantity(newInventory);
            dao.setDeposit(new BigDecimal(0));
            BigDecimal difference = vendingItem.getItemPrice().subtract(amountEntered);
            message = "Thank You for Your Purchase. Your change is " + difference;
            changeMessage = "Your change is " + change.getDollars() + " Dollars "
                    + change.getQuarters() + " Quarters " + change.getDimes() + " Dimes " + change.getNickels()
                    + " Nickels " + change.getNickels() + " Pennies " + change.getPennies();
        }

    }

    @Override
    public List<Item> getAllItems() {
        return dao.listItems();
    }

    @Override
    public Item getItem(String itemNumber) {
        return dao.getItem(itemNumber);
    }

    @Override
    public String getItemSelected() {
        return dao.getItemSelected();
    }

    @Override
    public void addChange(BigDecimal amount) {
        dao.addChange(amount);
    }

    @Override
    public BigDecimal getAmountEntered() {
        return dao.getAmountEntered();
    }

    @Override
    public void setItemSelected(String itemNumber) {
        dao.setItemSelected(itemNumber);
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getChangeMessage() {
        return this.changeMessage;
    }

    @Override
    public void changeReturn() {
        dao.setDeposit(new BigDecimal(0));
        message = "";
        changeMessage = "";
        dao.setItemSelected("");
    }

}
