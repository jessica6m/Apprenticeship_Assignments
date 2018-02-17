/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jesse
 */
public class VendingMachineController {

    VendingMachineView view;
    VendingMachineServiceLayer service;

    //private UserIO io = new UserIOConsoleImpl();

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            listItems();

            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        purchaseItem();
                        break;
                    case 2:
                        viewItem();
                        break;
                    case 3:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (VendingMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void listItems()
            throws VendingMachinePersistenceException {
        view.displayDisplayAllBanner();
        List<Item> itemList = service.getAllItems();
        view.displayItemList(itemList);
    }

    private void viewItem()
            throws VendingMachinePersistenceException {
        view.displayItemBanner();
        String itemNumber = view.getItemNumberChoice();
        Item item = service.getItem(itemNumber);
        view.displayItem(item);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void purchaseItem()
            throws VendingMachinePersistenceException {
       
        BigDecimal deposit = requestDeposit();

        boolean hasErrors = false;
     
            String itemNumber = view.getItemNumberChoice();
            try {
                String purchasedItem = service.purchaseItem(itemNumber, deposit);
                view.displayChange(purchasedItem);
                view.displayThankYouPurchase();
                hasErrors = false;
            } catch (InsufficientFundsException | NoItemInventoryException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
    
        // code not complete

    }

    private BigDecimal requestDeposit() {
        BigDecimal deposit = view.displayRequestDeposit();
        view.displayDepositSuccessful();
        return deposit;
    }

   

}
