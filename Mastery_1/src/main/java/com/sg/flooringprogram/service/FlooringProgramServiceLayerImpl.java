/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.service;


import com.sg.flooringprogram.dao.FlooringProgramAuditDao;
import com.sg.flooringprogram.dao.FlooringProgramDao;
import com.sg.flooringprogram.dao.FlooringProgramDaoException;
import com.sg.flooringprogram.dao.FlooringProgramDaoFileImpl;
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
public class FlooringProgramServiceLayerImpl implements FlooringProgramServiceLayer {

    FlooringProgramAuditDao auditDao;
    FlooringProgramDao dao;

    public FlooringProgramServiceLayerImpl(FlooringProgramAuditDao auditDao, FlooringProgramDao dao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }


    @Override
    public Order addOrder(String name, String stateName, String producType, BigDecimal area, LocalDate date)
            throws InvalidProductException, 
            InvalidStateException, 
            InvalidCustomerException,
            InvalidAreaException {
        
        Product product = dao.getProduct(producType);
      
        if (product == null) {
            throw new InvalidProductException("Invalid Material");
        }
        StateTax state = dao.getState(stateName);
        if (state == null) {
            throw new InvalidStateException("Invalid State");
        }

        if (name == null || name.isEmpty()) {
            throw new InvalidCustomerException("Invalid Customer Name");
        }

        if (area == null || area.compareTo(new BigDecimal(0)) <= 0) {
            throw new InvalidAreaException("Incorrect Area measurment");
        }

        Order newOrder = new Order(name, state, product, area, date);
//        newOrder.setProduct(product);
//        newOrder.setStateTax(state);
        return dao.addOrder(newOrder);

    }
    
    @Override
    public List<Order> listOrdersByDate(LocalDate date) throws InvalidOrderDate{
        List ordersByDate = dao.listAllOrdersByDate(date);
        
        if(ordersByDate == null || ordersByDate.isEmpty()) {
            throw new InvalidOrderDate("There are no orders placed on that date.");
        }
        
        return ordersByDate;
    }
    
    @Override
    public Order removeOrder(int orderNumber) throws OrderNotFoundException{
        Order removedOrder =  dao.removeOrder(orderNumber);
        if (removedOrder == null) {
            throw new OrderNotFoundException("Order doesn't exist.");
        }
        return removedOrder;
    }
    
    @Override
    public Order listOrder(int orderNumber) {
        return dao.listOrder(orderNumber);
    }

    @Override
    public List<StateTax> getStateTaxList() {
        return dao.getStateTaxList();
    }
    
    @Override
    public StateTax getState(String state) throws InvalidStateException {

        StateTax states = dao.getState(state);

        if (states == null) {
            throw new InvalidStateException("Invalid State");
        }

        return states;
    }
    
    @Override
    public Product getProduct(String material) throws InvalidProductException {
        Product product = dao.getProduct(material);

        if (product == null) {
            throw new InvalidProductException("Invalid Material");
        }
        return product;
    }

    @Override
    public List<Product> getProductList() {
        return dao.getProductList();
    }
   
    @Override
    public void loadAll() throws FlooringProgramDaoException {
       dao.loadAll();
    }

    @Override
    public void writeAll() throws FlooringProgramDaoException {
        dao.writeAll();
    }
   
}

