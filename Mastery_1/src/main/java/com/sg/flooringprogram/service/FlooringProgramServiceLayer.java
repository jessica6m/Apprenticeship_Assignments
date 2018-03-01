/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.service;

import com.sg.flooringprogram.dao.FlooringProgramDaoException;
import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Product;
import com.sg.flooringprogram.dto.StateTax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jesse
 */
public interface FlooringProgramServiceLayer {

    Order addOrder(String name, String state, String product, BigDecimal area, LocalDate date)
            throws InvalidProductException, InvalidStateException, 
            InvalidCustomerException, InvalidAreaException;

    List<StateTax> getStateTaxList();

    List<Product> getProductList();
    
    Order listOrder(int orderNumber);
    
    List<Order> listOrdersByDate(LocalDate date) throws InvalidOrderDate;
    
    Order removeOrder(int orderNumber) throws OrderNotFoundException;
    
    StateTax getState(String state) throws InvalidStateException;
    
    Product getProduct(String material) throws InvalidProductException;
    
    void writeAll() throws FlooringProgramDaoException;
    
    void loadAll() throws FlooringProgramDaoException;
}
