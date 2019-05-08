/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormastery.dao;

import com.sg.floormastery.dto.Order;
import com.sg.floormastery.dto.Products;
import com.sg.floormastery.service.FMOrderUnknownException;
import com.sg.floormastery.service.FMProductUnknownException;
import com.sg.floormastery.service.FMStateUnknownException;
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
 * @author lukem
 */
public class FMDaoTest {
    
    private FMDao dao = new FMDaoImpl();
    
    public FMDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        String dateEntered = null;
        int orderNumber = 0;
        List<Order> orderList = dao.getOrdersByDate(dateEntered);
        for (Order order : orderList) {
            dao.removeOrderByOrderNumber(order.getDateEntered(), order.getOrderNumber());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder method, of class FMDao.
     */
    @Test
    public void testAddGetOrder() throws Exception {
        Order order = new Order();
        order.setCustomerName("Joe");
        order.setArea(new BigDecimal("5"));
        order.setOrderNumber(1);
        order.setState("MN");
        order.setTaxRate(new BigDecimal("4"));
        order.setProductType("carpet");
        order.setCostPerSqFt(new BigDecimal("10"));
        order.setLaborCostPerSqFt(new BigDecimal("8"));
        order.setMaterialCost(new BigDecimal("100"));
        order.setLaborCost(new BigDecimal("150"));
        order.setTotalTax(new BigDecimal("25"));
        order.setTotalBill(new BigDecimal("1000"));
        String dateEntered;
        
        dao.addOrder(order, order.getDateEntered());
        
        Order fromDao = dao.geOrderByOrderNumber(order.getDateEntered(), 1);
        
        assertEquals(order, fromDao);
    }

    /**
     * Test of geOrderByOrderNumber method, of class FMDao.
     */
    @Test
    public void testGeOrderByOrderNumber() throws Exception {
    }

    /**
     * Test of getStateTaxRates method, of class FMDao.
     */
    @Test
    public void testGetStateTaxRates() throws Exception {
    }

    /**
     * Test of getProducts method, of class FMDao.
     */
    @Test
    public void testGetProducts() throws Exception {
    }

    /**
     * Test of getOrdersByDate method, of class FMDao.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
    }

    /**
     * Test of editOrderByOrderNumber method, of class FMDao.
     */
    @Test
    public void testEditOrderByOrderNumber() throws Exception {
    }

    /**
     * Test of removeOrderByOrderNumber method, of class FMDao.
     */
    @Test
    public void testRemoveOrderByOrderNumber() throws Exception {
    }

    /**
     * Test of getAllOrders method, of class FMDao.
     */
    @Test
    public void testGetAllOrders() throws Exception {
    }

    /**
     * Test of loadMode method, of class FMDao.
     */
    @Test
    public void testLoadMode() throws Exception {
    }

   
    
}
