package com.sg.floormastery.dao;

import com.sg.floormastery.dto.Mode;
import com.sg.floormastery.dto.Order;
import com.sg.floormastery.dto.Products;
import com.sg.floormastery.dto.StateTax;
import com.sg.floormastery.service.FMOrderUnknownException;
import com.sg.floormastery.service.FMProductUnknownException;
import com.sg.floormastery.service.FMService;
import com.sg.floormastery.service.FMStateUnknownException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author lukem
 */
public class FMDaoImpl implements FMDao {

    public static final String MODE_FILE = "mode.txt";
    public static final String PRODUCTS_FILE = "products.txt";
    public static final String STATETAX_FILE = "statetax.txt";
    public static final String DELIMITER = "::";
    

    List<Mode> modeList = new ArrayList<>();
    List<StateTax> stateTaxList = new ArrayList<>();
    List<Products> productsList = new ArrayList<>();
    Map<Integer, Order> orderMap = new HashMap<>();

    FMService service;
    FMDao dao;

    public void FMDaoImpl(FMService service, FMDao dao) {
        this.dao = dao;
        this.service = service;
    }

    @Override
    public void addOrder(Order order, String dateEntered) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        orderMap.put(order.getOrderNumber(), order);
        writeOrders(dateEntered);

    }

    @Override
    public BigDecimal getStateTaxRates(String state) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        loadStateTax();
        for (StateTax s : stateTaxList) {
            if (s.getState().equalsIgnoreCase(state)) {
                return s.getTaxRate();
            }

        }
        return null;
    }

    @Override
    public Products getProducts(String productType) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        loadProducts();
        for (Products p : productsList) {
            if (p.getProductType().equalsIgnoreCase(productType)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Order geOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        loadOrders(dateEntered);
        return orderMap.get(orderNumber);

    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<Order>(orderMap.values());

    }

    @Override
    public List<Order> getOrdersByDate(String dateEntered) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        loadOrders(dateEntered);
        return new ArrayList<> (orderMap.values());
                
    }

    @Override
    public void editOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        loadOrders(dateEntered);
        orderMap.get(orderNumber);
        writeOrders(dateEntered);
    }

    @Override
    public void removeOrderByOrderNumber(String dateEntered, int orderNumber) throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        loadOrders(dateEntered);
        orderMap.remove(orderNumber);
        writeOrders(dateEntered);
    }
   
    public void writeOrders(String dateEntered) throws FMOrderUnknownException, FMStateUnknownException, FMProductUnknownException {
        PrintWriter out;

        String ORDERS_MMddyyyy = "ORDERS_" + dateEntered;
        
        try {
            out = new PrintWriter(new FileWriter(ORDERS_MMddyyyy));
        } catch (IOException e) {
            throw new FMOrderUnknownException("Order does not Exist.", e);

        }

        
        List<Order> orderList = this.getAllOrders();
        for (Order currentItem : orderList) {

            out.println(
                    currentItem.getOrderNumber() + DELIMITER
                    + currentItem.getCustomerName() + DELIMITER
                    + currentItem.getProductType() + DELIMITER
                    + currentItem.getArea() + DELIMITER
                    + currentItem.getState() + DELIMITER
                    + currentItem.getTaxRate() + DELIMITER
                    + currentItem.getCostPerSqFt() + DELIMITER
                    + currentItem.getLaborCostPerSqFt() + DELIMITER
                    + currentItem.getMaterialCost() + DELIMITER
                    + currentItem.getLaborCost() + DELIMITER
                    + currentItem.getTotalTax().setScale(2, RoundingMode.HALF_UP) + DELIMITER
                    + currentItem.getTotalBill().setScale(2, RoundingMode.HALF_UP));

        }

        
        out.close();
    }

    public void loadOrders(String dateEntered) throws FMOrderUnknownException, FMStateUnknownException, FMProductUnknownException {
        Scanner sc;

        String ORDERS_MMddyyyy = "ORDERS_" + dateEntered;
        
        try {

            sc = new Scanner(new BufferedReader(new FileReader(ORDERS_MMddyyyy)));
        } catch (FileNotFoundException o) {
            throw new FMOrderUnknownException("Order cannot be found", o);
        }

        String currentLine;
        String[] currentValue;

        orderMap.clear();

        while (sc.hasNextLine()) {

            currentLine = sc.nextLine();
            currentValue = currentLine.split(DELIMITER);

            Order currentOrder = new Order();

            currentOrder.setOrderNumber(Integer.parseInt(currentValue[0]));
            currentOrder.setCustomerName(currentValue[1]);
            currentOrder.setProductType(currentValue[2]);
            currentOrder.setArea(new BigDecimal(currentValue[3]));
            currentOrder.setState(currentValue[4]);
            currentOrder.setTaxRate(new BigDecimal(currentValue[5]));
            currentOrder.setCostPerSqFt(new BigDecimal(currentValue[6]));
            currentOrder.setLaborCostPerSqFt(new BigDecimal(currentValue[7]));
            currentOrder.setMaterialCost(new BigDecimal(currentValue[8]));
            currentOrder.setLaborCost(new BigDecimal(currentValue[9]));
            currentOrder.setTotalTax(new BigDecimal(currentValue[10]));
            currentOrder.setTotalBill(new BigDecimal(currentValue[11]));

            orderMap.put(currentOrder.getOrderNumber(), currentOrder);
        }

        sc.close();
    }

    public void loadProducts() throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        Scanner sc;

        try {

            sc = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException p) {
            throw new FMProductUnknownException("Product cannot be found", p);
        }

        String currentLine;
        String[] currentValue;

        productsList.clear();

        while (sc.hasNextLine()) {

            currentLine = sc.nextLine();
            currentValue = currentLine.split(DELIMITER);

            Products currentProduct = new Products();

            currentProduct.setProductType(currentValue[0]);
            currentProduct.setCostPerSqFt(new BigDecimal(currentValue[1]));
            currentProduct.setLaborCostPerSqFtCarpet(new BigDecimal(currentValue[2]));

            productsList.add(currentProduct);

        }

        sc.close();
    }

    public void loadStateTax() throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        Scanner sc;

        try {

            sc = new Scanner(new BufferedReader(new FileReader(STATETAX_FILE)));
        } catch (FileNotFoundException s) {
            throw new FMStateUnknownException("State cannot be found", s);
        }

        String currentLine;
        String[] currentValue;

        stateTaxList.clear();

        while (sc.hasNextLine()) {

            currentLine = sc.nextLine();
            currentValue = currentLine.split(DELIMITER);

            StateTax currentStateTax = new StateTax();

            currentStateTax.setState(currentValue[0]);
            currentStateTax.setTaxRate(new BigDecimal(currentValue[1]));

            stateTaxList.add(currentStateTax);

        }

        sc.close();
    }
    
    @Override
     public String loadMode() throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        Scanner sc;

        // production or training are the only valid options
        try {

            sc = new Scanner(new BufferedReader(new FileReader(MODE_FILE)));
        } catch (FileNotFoundException p) {
            throw new FMProductUnknownException("mode cannot be found", p);
        }

        String currentLine;
        

        modeList.clear();

            currentLine = sc.nextLine();

            Mode currentMode = new Mode();

            currentMode.setMode(currentLine);
            
            modeList.add(currentMode);

        sc.close();
        
        return currentLine;

     }

    

    

}
