/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormastery.service;
import com.sg.floormastery.dto.Order;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author lukem
 */
public interface FMService {
    
    
    Order createOrder(String customerName, String productType, BigDecimal area, String state) throws FMOrderUnknownException, FMStateUnknownException, FMProductUnknownException;
    
    void addOrder(Order order, String dateEntered) throws FMOrderUnknownException, FMStateUnknownException, FMProductUnknownException;
   
    Order getOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
       
    List<Order> getOrdersByDateEntered(String dateEntered) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
 
    void editOrderByOrderNumber (String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    void removeOrderByOrderNumber (String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException;
    
    
      
}

