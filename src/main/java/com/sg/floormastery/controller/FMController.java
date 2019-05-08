package com.sg.floormastery.controller;

import com.sg.floormastery.dto.Order;
import com.sg.floormastery.service.FMOrderUnknownException;
import com.sg.floormastery.service.FMProductUnknownException;
import com.sg.floormastery.service.FMService;
import com.sg.floormastery.service.FMStateUnknownException;
import com.sg.floormastery.ui.FMView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author lukem
 */
public class FMController {

    FMView view;
    FMService service;

    public FMController(FMView view, FMService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {

        try {

            while (true) {

                int userSelection = view.displayMenuAndGetOption();
                view.printPageDivider();

                switch (userSelection) {
                    case 1:
                        DisplayOrders();
                        break;
                    case 2:
                        AddOrder();
                        break;
                    case 3:
                        EditOrder();
                        break;
                    case 4:
                        RemoveOrder();
                        break;
                    case 5:
                        view.exitMessage();
                        System.exit(0);
                        break;
                    default:
                        view.unknownCommand();
                        break;

                }
            }

        } catch (FMOrderUnknownException e) {
            view.errorMessage(e.getMessage());

        } catch (FMProductUnknownException o) {
            view.errorMessage(o.getMessage());

        } catch (FMStateUnknownException s) {
            view.errorMessage(s.getMessage());

        }
    }

    private void DisplayOrders() throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        String dateEntered = view.getDateEnteredByUser();
        view.printPageDivider();
        List<Order> displayOrders = service.getOrdersByDateEntered(dateEntered);
        view.displayAllOrders(displayOrders);

    }

    private void AddOrder() throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {

        BigDecimal area;

        String customerName = view.getNewOrderCustomerName();
        if (customerName.isEmpty()) {
            customerName = view.nameNotEntered();
        }
        String productType = view.getNewOrderProductType();
        if (productType.isEmpty()) {
            productType = view.productNotEntered();
        }
        String state = view.getNewOrderState();
        if (state.isEmpty()) {
            state = view.stateNotEntered();
        }
        String areaEntered = view.getNewOrderArea();

        if (areaEntered.isEmpty()) {
            areaEntered = view.areaNotEntered();
            area = new BigDecimal(areaEntered);
        }
        area = new BigDecimal(areaEntered);

        Order newOrder = service.createOrder(customerName, productType, area, state);
        view.printPageDivider();
        String dateEntered = newOrder.getDateEntered();
        boolean answer = view.askFinalSubmit(newOrder);
        if (answer == true) {
            service.addOrder(newOrder, dateEntered);
            view.displayOrderSuccessfullyCreated();
            view.printPageDivider();
        }

    }

    private void EditOrder() throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        String dateEntered = view.getDateEnteredByUser();
        int orderNumber = view.getOrderNumber();
        view.printPageDivider();
        Order order = service.getOrderByOrderNumber(dateEntered, orderNumber);
        view.displayOrder(order);
        view.editOrderConfirmation();
        String customerName = service.getOrderByOrderNumber(dateEntered, orderNumber).getCustomerName();
        String productType = service.getOrderByOrderNumber(dateEntered, orderNumber).getProductType();
        String state = service.getOrderByOrderNumber(dateEntered, orderNumber).getState();
        BigDecimal area = service.getOrderByOrderNumber(dateEntered, orderNumber).getArea();
        customerName = view.editCustomerName(customerName);
        if (customerName.isEmpty()) {
            customerName = service.getOrderByOrderNumber(dateEntered, orderNumber).getCustomerName();
        }
        productType = view.editProductType(productType);
        if (productType.isEmpty()) {
            productType = service.getOrderByOrderNumber(dateEntered, orderNumber).getProductType();
        }
        state = view.editState(state);
        if (state.isEmpty()) {
            state = service.getOrderByOrderNumber(dateEntered, orderNumber).getState();
        }
        String editArea = view.editArea(area);
        if (editArea.isEmpty()) {
            area = service.getOrderByOrderNumber(dateEntered, orderNumber).getArea();
        } else {
            area = new BigDecimal(editArea);
        }
        view.printPageDivider();
        Order editedOrder = service.createOrder(customerName, productType, area, state);
        boolean answer = view.askFinalSubmit(editedOrder);
        if (answer == true) {
            service.addOrder(editedOrder, dateEntered);
            service.removeOrderByOrderNumber(dateEntered, orderNumber);
            view.displaySuccessfulOrderEdit();
            view.printPageDivider();
        }

    }

    private void RemoveOrder() throws FMOrderUnknownException, FMProductUnknownException, FMStateUnknownException {
        String dateEntered = view.getDateEnteredByUser();
        int orderNumber = view.getOrderNumber();
        view.printPageDivider();
        Order removeOrder = service.getOrderByOrderNumber(dateEntered, orderNumber);
        view.removeOrderConfirmation();
        boolean answer = view.askFinalSubmit(removeOrder);
        if (answer == true) {
            service.removeOrderByOrderNumber(dateEntered, orderNumber);
            view.displayOrderSuccessfullyRemoved();
        }
     
    }
}
