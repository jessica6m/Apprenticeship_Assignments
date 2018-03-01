/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.dao;

import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Product;
import com.sg.flooringprogram.dto.StateTax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author jesse
 */
public class FlooringProgramDaoFileImpl implements FlooringProgramDao {
// 2 DAOS Training or Prod 

    public static  String ORDER_FILE = "order.txt";
    public static  String STATE_FILE = "state.txt";
    public static  String PRODUCT_FILE = "product.txt";
    public static  String DELIMITER = "::";
 

    private Map<Integer, Order> orders = new HashMap<>();
    private Map<String, StateTax> taxes = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();

    public FlooringProgramDaoFileImpl() {
    }
    
    public FlooringProgramDaoFileImpl(String orderFile) {
        ORDER_FILE = orderFile;
    }
    

    @Override
    public Order addOrder(Order order) {
        order.setOrderNumber(getNextOrderNumber());
        orders.put(order.getOrderNumber(), order);
        return order;
    }

    @Override
    public int getNextOrderNumber() {
        int nextOrderNumber = 0;

        for (int n : orders.keySet()) {
            if (n > nextOrderNumber) {
                nextOrderNumber = n;
            }
        }
        nextOrderNumber++;
        return nextOrderNumber;
    }

    @Override
    public Order editOrder(int orderNumber, Order order) {
        Order editedOrder = orders.put(orderNumber, order);
        return editedOrder;
    }

    @Override
    public Order listOrder(int orderNumber) {
        return orders.get(orderNumber);
    }

    @Override
    public Order removeOrder(int orderNumber) {
        return orders.remove(orderNumber);
    }

    @Override
    public List<Order> listAllOrdersByDate(LocalDate date) {
        return orders.values()
                .stream()
                .filter(s -> s.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> getProductList() {
        // getting the product list
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProduct(String productType) {
        return products.get(productType);
    }

    @Override
    public StateTax getState(String stateName) {
        return taxes.get(stateName);
    }

    @Override
    public List<StateTax> getStateTaxList() {
        return new ArrayList<>(taxes.values());
    }

    @Override
    public void writeOrder() throws FlooringProgramDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ORDER_FILE));
        } catch (IOException e) {
            throw new FlooringProgramDaoException(
                    "Could not save order data.", e);
        }

        List<Order> orderList = new ArrayList(orders.values());

        for (Order currentOrder : orderList) {
 
            out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getStateTax().getStateName() + DELIMITER
                    + currentOrder.getProduct().getProductType() + DELIMITER
                    + currentOrder.getArea() + DELIMITER
                    + currentOrder.getDate());

            out.flush();
        }
        // Clean up
        out.close();
    }

    // read and write for all hashmap

    @Override
    public void writeStateTax() throws FlooringProgramDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(STATE_FILE));
        } catch (IOException e) {
            throw new FlooringProgramDaoException(
                    "Could not save state tax data.", e);
        }

        List<StateTax> stateTaxList = new ArrayList(taxes.values());

        for (StateTax currentStateTax : stateTaxList) {

            out.println(currentStateTax.getStateName() + DELIMITER
                    + currentStateTax.getTaxRate());

            out.flush();
        }

        out.close();
    }

    @Override
    public void writeProduct() throws FlooringProgramDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PRODUCT_FILE));
        } catch (IOException e) {
            throw new FlooringProgramDaoException(
                    "Could not save product data.", e);
        }

        List<Product> productList = new ArrayList(products.values());

        for (Product currentProduct : productList) {
         
            out.println(currentProduct.getProductType() + DELIMITER
                    + currentProduct.getCostPerSqFt() + DELIMITER
                    + currentProduct.getLaborCostPerSqFt());
        
            out.flush();
        }

        out.close();
    }
    
    private void loadOrder() throws FlooringProgramDaoException {
	    Scanner scanner;
	    
	    try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(ORDER_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new FlooringProgramDaoException(
	                "-_- Could not load order data into memory.", e);
	    }

	    String currentLine;

	    String[] currentTokens;
	
	    while (scanner.hasNextLine()) {
	        // get the next line in the file
	        currentLine = scanner.nextLine();
	        // break up the line into tokens
	        currentTokens = currentLine.split(DELIMITER);
	
	        Order currentOrder = new Order(currentTokens[1],
                        taxes.get(currentTokens[2]),
                        products.get(currentTokens[3]), 
                        new BigDecimal(currentTokens[4]),
                        LocalDate.parse(currentTokens[5]));
                
                currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
 
                

	        orders.put(currentOrder.getOrderNumber(), currentOrder);
  
	}
            scanner.close();
    
    }
    
     private void loadStateTax() throws FlooringProgramDaoException {
	    Scanner scanner;
	    
	    try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(STATE_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new FlooringProgramDaoException(
	                "-_- Could not load state data into memory.", e);
	    }

	    String currentLine;

	    String[] currentTokens;
	
	    while (scanner.hasNextLine()) {
	        // get the next line in the file
	        currentLine = scanner.nextLine();
	        // break up the line into tokens
	        currentTokens = currentLine.split(DELIMITER);
	


                StateTax currentState = new StateTax(currentTokens[0], new BigDecimal (currentTokens[1]));
                
                taxes.put(currentState.getStateName(), currentState);
   
	} 
            scanner.close();
    
    }

     private void loadProduct() throws FlooringProgramDaoException {
	    Scanner scanner;
	    
	    try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader(PRODUCT_FILE)));
	    } catch (FileNotFoundException e) {
	        throw new FlooringProgramDaoException(
	                "-_- Could not load product data into memory.", e);
	    }

	    String currentLine;

	    String[] currentTokens;
	
	    while (scanner.hasNextLine()) {
	        // get the next line in the file
	        currentLine = scanner.nextLine();
	        // break up the line into tokens
	        currentTokens = currentLine.split(DELIMITER);
	

                   Product currentProduct = new Product(currentTokens[0], new BigDecimal(currentTokens[1]), 
                           new BigDecimal (currentTokens[2]));

                   products.put(currentProduct.getProductType(), currentProduct);
            } 
            scanner.close();
    }
     
    @Override
     public void loadAll() throws FlooringProgramDaoException {
         this.loadProduct();
         this.loadStateTax();
         this.loadOrder();
     } // write a pass through in service
     
    @Override
     public void writeAll() throws FlooringProgramDaoException {
         this.writeProduct();
         this.writeStateTax();
         this.writeOrder();
     }  // write a pass through in service

     
}
