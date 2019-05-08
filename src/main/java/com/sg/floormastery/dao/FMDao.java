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

/**
 *
 * @author lukem
 */
public interface FMDao {
    
    void addOrder(Order order, String dateEntered) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    Order geOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    BigDecimal getStateTaxRates(String state) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    Products getProducts(String productType) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    List<Order> getOrdersByDate(String dateEntered) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    void editOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    void removeOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    List<Order> getAllOrders () throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    String loadMode() throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException; 
}
