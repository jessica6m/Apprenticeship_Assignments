/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.ui;

import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Product;
import com.sg.flooringprogram.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author jesse
 */
public class FlooringProgramView {

    private UserIO io;

    public FlooringProgramView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("<<Flooring Program>>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Save Current Work");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }
    
    public String chooseEdit(){
        io.print("Which field would you like to edit?");
    return io.readString("\nCustomer Name \nState \nMaterial \nArea ");
    }
    
    
    public void chooseMode() {
        io.print("Please enter a mode");
        io.readString("Training or Prod");
    }

    public int getNewOrderNum() {
        int orderNumber = io.readInt("Please enter the Order Number: ");
        return orderNumber;
    }

    public String getNewOrderCustomerName() {
        String customersName = io.readString("Please enter the customers name: ");
        return customersName;
    }

    public String getNewOrderProductName(String prompt, List<Product> products) {
        for (Product product : products) {
            io.print(product.getProductType());
        }

        String materialName = io.readString("Please enter the Material you would like to use:  ");
        return materialName;
    }

    public String getNewOrderStateName(String prompt, List<StateTax> states) {
        for (StateTax state : states) {
            io.print(state.getStateName() + " " + state.getTaxRate());
        }
        String state = io.readString("Please enter your State's Name: ");

        return state;
    }
    

    public BigDecimal getNewOrderArea(){
        BigDecimal area = io.readBigDecimal("Please enter the Area size:  ");
        return area;
    }

    public LocalDate getOrderDate() {
        String orderDate = io.readString("Please enter the order date. (MM/dd/yyyy)");
        LocalDate ld = LocalDate.parse(orderDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        return ld;
    }
    
    public void displayOrderBanner() {
        io.print("=== Order ===");
    }
    
    public void editOrderBanner() {
        io.print("=== Edit Order ===");
    } 
    
    public void removeOrderBanner() {
        io.print("=== Remove Order ===");
    }

    public void displayOrder(Order order) {
        if (order != null) {
        
        io.print("Order Number#: "+ order.getOrderNumber()+ " | Customer Name: " + order.getCustomerName() + ", Order Date: " + order.getDate()
                + ", Material: " + order.getProduct().getProductType() + ", Area: " + order.getArea() + ", State: " + order.getStateTax().getStateName()
                + ", Total: " + order.getTotal());
        } else {
            io.print("No such Order.");
        }
            io.print("Please hit enter to continue.");
    }
    
    public void displayAllOrders(List<Order> list){
        
        if(list != null){
        
        for(Order order: list){
            io.print("Order Number #: "+ order.getOrderNumber()+" | Customer Name: " + order.getCustomerName() + ", Order Date: " + order.getDate()
                + ", Material: " + order.getProduct().getProductType() + ", Area: " + order.getArea() + ", State: " + order.getStateTax().getStateName()
                + ", Total: " + order.getTotal());
        }
       } else {
            io.print("No Orders were placed.");
        }
            io.print("Please hit enter to continue.");
    }

    public void displayOrderPurchaseBanner() {
        io.print("=== Purchase Order ===");
    }

    public String getOrderNumberChoice() {
        return io.readString("Please enter the Order Number.");
    }

    public String displayThankYouPurchase() {
        return io.readString("Thank You for your purchase \nPress enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== All Orders ===");
    }

    public void listAllOrders(LocalDate date) {
        io.print("Listing of  all orders placed on " + date + ":");
    }

    public void listAllProducts(List<Product> list) {
        for(Product product : list){
            io.print("List of all products available: " + product.getProductType());
        }
    }
    
    public void listAllStates (List<StateTax> list) {
        for(StateTax states : list){
            io.print("List of all products available: "+ states.getStateName());
        }
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public String removeOrderComfirmation() {
        return io.readString("\nAre you sure you want to remove this Order?\nyes or no");
    }

    public String editOrderConfirmation() {
        return io.readString("\nAre you sure you want to edit this Order?\nyes or no");
    }
    
    public void cancelEditOrder(){
      io.print("You have choosen not to edit your order");
    }
    
    public void cancelRemoveOrder(){
     io.print("You have choosen not to remove your order");
    }
    
    public void displayEditSucessBanner(){
        io.print("You have sucessfully edited your order");
    }
    
     public void displayRemoveSucessBanner(){
        io.print("You have sucessfully removed your order");
    }
     
     public void displayAddOrderSucessBanner(){
        io.print("You have sucessfully added your order");
    }

}

