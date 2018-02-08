/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jesse
 */
public class VendingMachineServiceLayerTest {
    
    VendingMachineDao dao = new VendingMachineDaoFileImpl("TESTreciept.txt");
    File file = new File("TESTreciept.txt");
    
    VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
    Change change = new Change();
    VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao, auditDao, change);
    Item item = new Item("1");
    
    
    public VendingMachineServiceLayerTest() {
       
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws UnsupportedEncodingException, FileNotFoundException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("TESTreciept.txt"), "utf-8"))) {
            writer.write("1::Coke::1.50::10"
                    + "\n2::Sprite::.75::8"
                    + "\n3::CherryCoke::1.00::0");
        }
        item.setItemName("Coke");
        item.setItemPrice(new BigDecimal ("1.50"));
        item.setItemQuantity(10);
    }
    
    @After
    public void tearDown() {
        file.delete();
    
        if(file.delete())
        {
            System.out.println("File deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file");
        }
    }

    /**
     * Test of getAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAllItems() throws Exception {
        List<Item> allItems = service.getAllItems();
        assertEquals(3, allItems.size());
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItem() throws Exception {
          assertEquals(item, service.getItem("1"));
    }

    /**
     * Test of purchaseItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testPurchaseItem() throws Exception {
      String purchaseItem = service.purchaseItem("1", new BigDecimal("2.00"));
       assertEquals( "Your recieving 0 Dollars \nYour recieving 2 Quarters \nYour recieving 0 Dimes \nYour recieving 0 Nickels \nYour recieving 0 Pennies", purchaseItem);
    }
    
    @Test 
    public void testPurchaseItemInsufficientFundsExp() throws Exception {
        try{
          service.purchaseItem("1", new BigDecimal(".25"));
        }catch (InsufficientFundsException e) {
        }
    }
    
    @Test
    public void testNoInventoryExp() throws Exception {
       try{
       service.purchaseItem("3", new BigDecimal("5.00"));
       } catch (NoItemInventoryException e){
           
       }
    }
    
}
