/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.service;

import com.sg.flooringprogram.dao.FlooringProgramAuditDao;
import com.sg.flooringprogram.dao.FlooringProgramAuditDaoFileImpl;
import com.sg.flooringprogram.dao.FlooringProgramDao;
import com.sg.flooringprogram.dao.FlooringProgramDaoFileImpl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
        
 *
 * @author jesse
 */
public class FlooringProgramServiceLayerTest {
    
//    private FlooringProgramDao dao = new FlooringProgramDaoFileImpl("TESTorder.txt");
//    private FlooringProgramAuditDao audit = new FlooringProgramAuditDaoFileImpl();
//    private FlooringProgramServiceLayer service = new FlooringProgramServiceLayerImpl(audit, dao);
    
    ApplicationContext ctx
                        = new ClassPathXmlApplicationContext("applicationContext.xml");
    FlooringProgramServiceLayer service
                        = ctx.getBean("serviceLayer", FlooringProgramServiceLayerImpl.class);
    File file = new File("TESTorder.txt");
    
    
    
    public FlooringProgramServiceLayerTest() {
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
        
        service.loadAll();
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
     * Test of addOrder method, of class FlooringProgramServiceLayer.
     */
    @Test
    public void testAddOrder() throws Exception {
    }

    /**
     * Test of listOrdersByDate method, of class FlooringProgramServiceLayer.
     */
    @Test
    public void testInvalidProductException() throws Exception {
        String name = "Jess";
        String stateName = "PA";
        String productName = "granite";
        BigDecimal area = new BigDecimal(50);
        LocalDate ld = LocalDate.now();
        
        try {
            service.addOrder(name, stateName, productName, area, ld);
            fail("Expected exception");
        } catch (InvalidProductException e) {
            
        }
    }
    
    @Test
    public void testInvalidStateException() throws Exception {
        String name = "Jess";
        String stateName = "NY";
        String productName = "wood";
        BigDecimal area = new BigDecimal(50);
        LocalDate ld = LocalDate.now();
        
        try {
            service.addOrder(name, stateName, productName, area, ld);
            fail("Expected exception");
        } catch (InvalidStateException e) {
            
        }
    }
    
    @Test
    public void testInvalidCustomerException() throws Exception {
        String name = "";
        String stateName = "PA";
        String productName = "wood";
        BigDecimal area = new BigDecimal(50);
        LocalDate ld = LocalDate.now();
        
        try {
            service.addOrder(name, stateName, productName, area, ld);
            fail("Expected exception");
        } catch (InvalidCustomerException e) {
            
        }
    }
    
    @Test
    public void testInvalidAreaException() throws Exception {
         String name = "Jess";
        String stateName = "PA";
        String productName = "wood";
        BigDecimal area = new BigDecimal(0);
        LocalDate ld = LocalDate.now();
        
        try {
            service.addOrder(name, stateName, productName, area, ld);
            fail("Expected exception");
        } catch (InvalidAreaException e) {
            
        }
        
    }
    
    @Test
    public void testListOrdersByDateInvalidOrderDate() throws Exception {
        try {
            service.listOrdersByDate(LocalDate.parse("1989-10-10"));
            fail("Expected exception");
        } catch (InvalidOrderDate e) {
            
        }
    }

    /**
     * Test of removeOrder method, of class FlooringProgramServiceLayer.
     */
    @Test
    public void testRemoveOrderOrderNotFoundException() throws Exception {
        try {
            service.removeOrder(2);
            fail("Expected exception");
        } catch (OrderNotFoundException e) {
        }
    }

    /**
     * Test of getState method, of class FlooringProgramServiceLayer.
     */
    @Test
    public void testGetState() throws Exception {
        try {
            service.getState("NJ");
            fail("Expected exception");
        } catch (InvalidStateException e) {
        }
    }

    /**
     * Test of getProduct method, of class FlooringProgramServiceLayer.
     */
    @Test
    public void testGetProduct() throws Exception {
        try {
            service.getProduct("DOG");
            fail("Expected exception");
        } catch (InvalidProductException e) {
        }
    }

    

    
    
}
