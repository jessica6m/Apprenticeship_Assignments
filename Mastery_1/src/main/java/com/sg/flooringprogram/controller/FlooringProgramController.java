/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.controller;

import com.sg.flooringprogram.dao.FlooringProgramDaoException;
import com.sg.flooringprogram.service.InvalidAreaException;
import com.sg.flooringprogram.service.InvalidCustomerException;
import com.sg.flooringprogram.service.InvalidOrderDate;
import com.sg.flooringprogram.service.InvalidProductException;
import com.sg.flooringprogram.service.InvalidStateException;
import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Product;
import com.sg.flooringprogram.dto.StateTax;
import com.sg.flooringprogram.service.FlooringProgramServiceLayer;
import com.sg.flooringprogram.service.OrderNotFoundException;
import com.sg.flooringprogram.ui.FlooringProgramView;
import com.sg.flooringprogram.ui.UserIO;
import com.sg.flooringprogram.ui.UserIOConsoleImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jesse
 */
public class FlooringProgramController {

    FlooringProgramView view;
    private FlooringProgramServiceLayer service;

    private UserIO io = new UserIOConsoleImpl();

    public FlooringProgramController(FlooringProgramView view, FlooringProgramServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        
 
     try{   
        
        service.loadAll();
        
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            try {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        // io.print("DISPLAY ORDERS");
                        this.listOrdersByDate();
                        break;
                    case 2:
                        //  io.print("ADD ORDER");
                        this.addOrder();
                        break;
                    case 3:
                        this.editOrder();
                        break;
                    case 4:
                        this.removeOrder();
                        break;
                    case 5:
                        this.saveWork();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                    default:
                        io.print("UNKNOWN COMMAND");
                }
            } catch (InvalidOrderDate ex) {
                view.displayErrorMessage(ex.getMessage());
            }

        }
        //service method call to wrtie all
        service.writeAll();
        io.print("GOOD BYE");
        
     }catch(FlooringProgramDaoException e){
          view.displayErrorMessage(e.getMessage());
     } 
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    public void addOrder() {
        view.displayOrderBanner();
        //get states from service layer
        //service get products from service Layer
        List<StateTax> states = service.getStateTaxList();
        List<Product> products = service.getProductList();

        String name = view.getNewOrderCustomerName();
        String state = view.getNewOrderStateName("Enter State", states);
        String product = view.getNewOrderProductName("Choose a product", products);
        BigDecimal area = view.getNewOrderArea();
        LocalDate ld = LocalDate.now();

        boolean isValid = false;

        while (isValid == false) {

            try {
                Order order = service.addOrder(name, state, product, area, ld);
                view.displayOrder(order);
                view.displayAddOrderSucessBanner();
                view.displayThankYouPurchase();
                isValid = true;
            } catch (InvalidCustomerException e) {
                view.displayErrorMessage(e.getMessage());
                name = view.getNewOrderCustomerName();
            } catch (InvalidStateException e) {
                view.displayErrorMessage(e.getMessage());
                state = view.getNewOrderStateName("Enter State", states);
            } catch (InvalidProductException e) {
                view.displayErrorMessage(e.getMessage());
                product = view.getNewOrderProductName("Choose a product", products);
            } catch (InvalidAreaException e) {
                view.displayErrorMessage(e.getMessage());
                area = view.getNewOrderArea();
            }
        }
    }

    public void listOrdersByDate()
            throws InvalidOrderDate {

        try {
            view.displayDisplayAllBanner();
            LocalDate date = view.getOrderDate();
            List<Order> orders = service.listOrdersByDate(date);
            view.displayAllOrders(orders);
        } catch (InvalidOrderDate e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    public void editOrder() {

        List<StateTax> states = service.getStateTaxList();
        List<Product> products = service.getProductList();

        try {
            listOrdersByDate();
            view.editOrderBanner();
            int orderNumber = view.getNewOrderNum();
            Order order = service.listOrder(orderNumber);
            view.displayOrder(order);

            String answer = view.editOrderConfirmation(); // confirm if they want to edit

            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                
                boolean isValid = true;
               
                do {
                    
                    String choice = view.chooseEdit().toLowerCase();

                    

                    switch (choice) {
                        case "customer name":
                            String name = view.getNewOrderCustomerName();
                            if(name != null || name.trim().length() != 0){
                            order.setCustomerName(name);
                            view.displayEditSucessBanner();
                            view.displayOrder(order);
                            isValid = false;
                            }
                            break;
                        case "state":
                            try {
                                view.listAllStates(states);
                                String state = view.getNewOrderStateName("Enter State", states).toUpperCase();
                                if(state != null || state.trim().length() != 0){
                                StateTax stateObj = service.getState(state);
                                order.setStateTax(stateObj);
                                view.displayEditSucessBanner();
                                view.displayOrder(order);
                                isValid = false;
                                }
                            } catch (InvalidStateException e) {
                                view.displayErrorMessage(e.getMessage());
                                isValid = true;
                            }
                            break;
                        case "area":
                            BigDecimal area = view.getNewOrderArea();
                            if(area.compareTo(new BigDecimal("0")) > 0){
                            order.setArea(area);
                            view.displayEditSucessBanner();
                            view.displayOrder(order);
                            isValid = false;
                            }
                            break;
                        case "material":
                            try {
                                String material = view.getNewOrderProductName("Choose a product", products).toLowerCase();
                                if(material != null || material.trim().length() != 0){
                                Product productObj = service.getProduct(material);
                                order.setProduct(productObj);
                                view.displayOrder(order);
                                isValid = false;
                                }
                            } catch (InvalidProductException e) {
                                view.displayErrorMessage(e.getMessage());
                                isValid = true;
                            }
                            break;
                    }
                } while (isValid);
            } else {
                view.cancelEditOrder();
            }

        } catch (InvalidOrderDate ex) {
            view.displayErrorMessage(ex.getMessage());
        }

    }

    public void removeOrder() throws InvalidOrderDate {

        listOrdersByDate();
 
        view.removeOrderBanner();
        int orderNumber = view.getNewOrderNum();
        Order order = service.listOrder(orderNumber);
        view.displayOrder(order);
        String answer = view.removeOrderComfirmation().toLowerCase();

        if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
            try {
                view.removeOrderBanner();
                service.removeOrder(orderNumber);
                view.displayRemoveSucessBanner();
            } catch (OrderNotFoundException e) {
                view.displayErrorMessage(e.getMessage());
            }
            
        } else {
            view.cancelRemoveOrder();
        }

    }

    public void saveWork() throws FlooringProgramDaoException {
        service.writeAll();
    }

}
