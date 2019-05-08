package com.sg.floormastery.service;

import com.sg.floormastery.dao.FMDao;
import com.sg.floormastery.dto.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lukem
 */
public class FMServiceImpl implements FMService {

    FMDao dao;

    public FMServiceImpl(FMDao dao) {
        this.dao = dao;
    }

    @Override
    public Order createOrder(String customerName, String productType, BigDecimal area, String state) throws FMProductUnknownException, FMStateUnknownException, FMOrderUnknownException {

        BigDecimal taxRate = dao.getStateTaxRates(state);
        BigDecimal costPerSqFt = dao.getProducts(productType).getCostPerSqFt();
        BigDecimal laborCostPerSqFt = dao.getProducts(productType).getLaborCostPerSqFtCarpet();
        BigDecimal materialCost = costPerSqFt.multiply(area);
        BigDecimal laborCost = laborCostPerSqFt.multiply(area);
        BigDecimal totalTax = taxRate.multiply(materialCost.add(laborCost).setScale(2, RoundingMode.HALF_UP));
        BigDecimal totalBill = totalTax.add(materialCost).add(laborCost).setScale(2, RoundingMode.HALF_UP);

        LocalDate myDate = LocalDate.now();
        String dateEntered = myDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));

        List<Order> orderList;
        try {
            orderList = dao.getOrdersByDate(dateEntered);
        } catch (FMOrderUnknownException ex) {
            orderList = new ArrayList<>();
        }
        int orderNumber = orderList.stream().mapToInt(g -> g.getOrderNumber()).max().orElse(0) + 1;

        Order newOrder = new Order();

        newOrder.setOrderNumber(orderNumber);
        newOrder.setDateEntered(dateEntered);
        newOrder.setCustomerName(customerName);
        newOrder.setProductType(productType);
        newOrder.setArea(area);
        newOrder.setState(state);
        newOrder.setTaxRate(taxRate);
        newOrder.setCostPerSqFt(costPerSqFt);
        newOrder.setLaborCostPerSqFt(laborCostPerSqFt);
        newOrder.setMaterialCost(materialCost);
        newOrder.setLaborCost(laborCost);
        newOrder.setTotalTax(totalTax);
        newOrder.setTotalBill(totalBill);

        return newOrder;

    }

    @Override
    public void addOrder(Order order, String dateEntered) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        if (dao.loadMode().equalsIgnoreCase("production")) {
            dao.addOrder(order, dateEntered);
        }
    }

    @Override
    public Order getOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        if (dao.geOrderByOrderNumber(dateEntered, orderNumber) == null) {
            throw new FMOrderUnknownException(orderNumber + " does not exist");
        }
        return dao.geOrderByOrderNumber(dateEntered, orderNumber);

    }

    @Override
    public List<Order> getOrdersByDateEntered(String dateEntered) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        return dao.getOrdersByDate(dateEntered);
    }

    @Override
    public void editOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        if (dao.loadMode().equalsIgnoreCase("production")) {

            if (dao.geOrderByOrderNumber(dateEntered, orderNumber) == null) {
                throw new FMOrderUnknownException(orderNumber + " does not exist");
            }
            dao.editOrderByOrderNumber(dateEntered, orderNumber);
        }
    }

    @Override
    public void removeOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        if (dao.loadMode().equalsIgnoreCase("production")) {

            if (dao.geOrderByOrderNumber(dateEntered, orderNumber) == null) {
                throw new FMOrderUnknownException(orderNumber + " does not exist");
            }
            dao.removeOrderByOrderNumber(dateEntered, orderNumber);
        }
    }

}
