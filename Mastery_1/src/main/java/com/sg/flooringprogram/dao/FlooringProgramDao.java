/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.dao;

import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Product;
import com.sg.flooringprogram.dto.StateTax;
import com.sg.flooringprogram.service.InvalidProductException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jesse
 */
public interface FlooringProgramDao {
   Order addOrder(Order order);
   Order editOrder(int orderNumber, Order order);
   Order listOrder(int orderNumber);
   Order removeOrder (int orderNumber);
   List<Order> listAllOrdersByDate(LocalDate date);
   List<Product> getProductList();
   List<StateTax> getStateTaxList();
   StateTax getState(String stateName);
   Product getProduct(String material);
   int getNextOrderNumber();
   void writeOrder() throws FlooringProgramDaoException;
   void writeStateTax() throws FlooringProgramDaoException;
   void writeProduct() throws FlooringProgramDaoException;
   void loadAll() throws FlooringProgramDaoException;
   void writeAll() throws FlooringProgramDaoException;
}
