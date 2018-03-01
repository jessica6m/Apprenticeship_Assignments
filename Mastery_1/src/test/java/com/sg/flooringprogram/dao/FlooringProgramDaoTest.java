/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.dao;

import com.sg.flooringprogram.dto.Order;
import com.sg.flooringprogram.dto.Product;
import com.sg.flooringprogram.dto.StateTax;
import com.sg.flooringprogram.service.FlooringProgramServiceLayer;
import com.sg.flooringprogram.service.FlooringProgramServiceLayerImpl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jesse
 */
public class FlooringProgramDaoTest {
    
//    private FlooringProgramDao dao = new FlooringProgramDaoFileImpl("TESTorder.txt");
    
    ApplicationContext ctx
                        = new ClassPathXmlApplicationContext("applicationContext.xml");
    FlooringProgramDao dao
                        = ctx.getBean("flooringProgramDao", FlooringProgramDaoFileImpl.class);
    File file = new File("TESTorder.txt");
    
    public FlooringProgramDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("TESTorder.txt"), "utf-8"))) { 
        }
        
        dao.loadAll();
        
    }
    
    @After
    public void tearDown() {
        
        file.delete();
    
        if(file.exists())
        {
            System.out.println("Failed to delete the file");
        }
        else
        {
          
            System.out.println("File deleted successfully");
        }
    }

    /**
     * Test of addOrder method, of class FlooringProgramDao.
     */
    @Test
    public void testAddOrder() {
        Order order = new Order("Jess",dao.getState("PA"), dao.getProduct("wood"), new BigDecimal("1234"), LocalDate.now());
        dao.addOrder(order);
        assertEquals(order, dao.listOrder(1));
    }

    /**
     * Test of editOrder method, of class FlooringProgramDao.
     */
    @Test
    public void testEditOrder() {
        Order order = new Order("Jess",dao.getState("PA"), dao.getProduct("wood"), new BigDecimal("1234"), LocalDate.now());
        order.setOrderNumber(1);
        dao.editOrder(order.getOrderNumber(),order);
        assertEquals(order, dao.listOrder(1));
    }

    /**
     * Test of removeOrder method, of class FlooringProgramDao.
     */
    @Test
    public void testRemoveOrder() {
       Order order = new Order("Jess",dao.getState("PA"), dao.getProduct("wood"), new BigDecimal("1234"), LocalDate.now());
       dao.addOrder(order);
       assertNotNull(dao.listOrder(1));
       dao.removeOrder(1);
       assertNull(dao.listOrder(1));
    }

    /**
     * Test of listAllOrdersByDate method, of class FlooringProgramDao.
     */
    @Test
    public void testListAllOdersByDate() {
        Order order = new Order("Jess",dao.getState("PA"), dao.getProduct("wood"), new BigDecimal("1234"), LocalDate.now());
        dao.addOrder(order);
        List<Order> orders = dao.listAllOrdersByDate(LocalDate.now());
        assertEquals(1, orders.size());       
    }

    /**
     * Test of getProductList method, of class FlooringProgramDao.
     */
    @Test
    public void testGetProductList() {
        List<Product> products = dao.getProductList();
        assertNotNull(products);
    }

    /**
     * Test of getStateTaxList method, of class FlooringProgramDao.
     */
    @Test
    public void testGetStateTaxList() {
        List<StateTax> states = dao.getStateTaxList();
        assertNotNull(states);
    }

    /**
     * Test of getNextOrderNumber method, of class FlooringProgramDao.
     */
    @Test
    public void testGetNextOrderNumber() {
       Order order = new Order("Jess",dao.getState("PA"), dao.getProduct("wood"), new BigDecimal("1234"), LocalDate.now());
       dao.addOrder(order);
       assertEquals(2, dao.getNextOrderNumber());
    }
    
}
