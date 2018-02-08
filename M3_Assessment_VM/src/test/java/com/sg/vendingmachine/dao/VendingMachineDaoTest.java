/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.io.BufferedWriter;
import java.io.File;
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
public class VendingMachineDaoTest {
    
    VendingMachineDao dao = new VendingMachineDaoFileImpl("TESTreciept.txt");
    File file = new File("TESTreciept.txt");
    
    Item item = new Item("1");
    
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws UnsupportedEncodingException, IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("TESTreciept.txt"), "utf-8"))) {
            writer.write("1::Coke::1.50::10"
                    + "\n2::Sprite::.75::8"
                    + "\n3::CherryCoke::1.00::6");
        }
        
        item.setItemName("Coke");
        item.setItemPrice(new BigDecimal ("1.50"));
        item.setItemQuantity(10);
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
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testListAllItems() throws Exception {
        List<Item> allItems = dao.getAllItems();
        assertEquals(3, allItems.size());
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     */
    @Test
    public void testGetItem() throws Exception {
        
        assertEquals(item, dao.getItem("1"));
    }

    /**
     * Test of purchaseItem method, of class VendingMachineDao.
     */
    @Test
    public void testPurchaseItem() throws Exception {
        dao.purchaseItem("1");
        assertEquals(9, dao.getItem("1").getItemQuantity());
    }
 
}
