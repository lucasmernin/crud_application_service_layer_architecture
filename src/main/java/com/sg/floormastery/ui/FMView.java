package com.sg.floormastery.ui;

import com.sg.floormastery.dto.Order;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author lukem
 */
public class FMView {

    FMUserIO io;

    public FMView(FMUserIO io) {
        this.io = io;
    }

    public int displayMenuAndGetOption() {

        io.print("");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Quit");
        io.print("");

        return io.readInt(("Please selected an option from above."), 1, 5);
    }

    public String getDateEnteredByUser() {
        io.print("");
        return io.readString("Date the Order was entered:  MMDDYYYY    ");
    }

    public int getOrderNumber() {
        io.print("");
        return io.readInt("Enter your Order Number.");
    }

    public void displayAllOrders(List<Order> order) {
        io.print("");
        io.print("Listing all Orders");
        for (Order o : order) {
            io.print(o.getOrderNumber() + "");
        }
        io.readString("Press enter to continue");
    }

    public String getNewOrderCustomerName() {
        io.print("");
        io.print("Create a new ORDER");
        return io.readString("Enter your Name:");
    }
    
    public String nameNotEntered() {
        io.print("");
        io.print("WARNING:  Name was not entered");
        return io.readString("Enter your Name:");
    }

    public String getNewOrderProductType() {
        io.print("");
        io.print("Product Options: Carpet, Laminate, Wood, Tile");
        return io.readString("Enter your Product Type: see above for valid options");
    }
    
     public String productNotEntered() {
        io.print("");
        io.print("WARNING:  Product Type was not entered");
        return io.readString("Enter your Product Type:");
    }

    public String getNewOrderArea() {
        io.print("");
        return io.readString("Enter your Area:");
    }
    
     public String areaNotEntered() {
        io.print("");
        io.print("WARNING:  Total Area was not entered");
        return io.readString("Enter your Total Area:");
    }

    public String getNewOrderState() {
        io.print("");
        io.print("Valid States: MN, IA, ND, WI");
        return io.readString("Enter your State :  see above for valid options (enter your states 2 Char symbol");
    }
    
     public String stateNotEntered() {
        io.print("");
        io.print("WARNING:  State was not entered");
        return io.readString("Enter your State:");
    }

    public boolean askFinalSubmit(Order currentOrder) {
        displayOrder(currentOrder);
        io.print("");
        String confirmAdd = io.readString("Commit Y/N");

        if (confirmAdd.equalsIgnoreCase("Y")) {
            return true;
        } else {
            io.print("Your Order was NOT saved.");
        
        }
        return false;
    }

    public String removeOrderConfirmation() {
        io.print("");
        io.print("WARNING: Once deleted file cannot be retrieved");
        return io.readString("press enter to continue or exit program IMMEDIATELY");
    }

    public String editOrderConfirmation() {
        io.print("");
        io.print("CAUTION: Editing information will delete any prior saved information.");
        return io.readString("press enter to continue or exit program IMMEDIATELY");
    }

    public void displayOrder(Order order) {

        io.print("");
        io.print("Order Number: " + order.getOrderNumber());
        io.print("Order Date: " + order.getDateEntered());
        io.print("");
        io.print("Customer Name: " + order.getCustomerName());
        io.print("Product Type: " + order.getProductType());
        io.print("State: " + order.getState());
        io.print("Total Area: " + order.getArea());
        io.print("");
        io.print("State Tax Rate:  %" + order.getTaxRate());
        io.print("Cost Per Square Foot:  $" + order.getCostPerSqFt());
        io.print("Labor Cost per square foot:  $" + order.getLaborCostPerSqFt());
        io.print("Material Cost:  $" + order.getMaterialCost());
        io.print("Labor Cost:  $" + order.getLaborCost());
        io.print("");
        io.print("Total Tax:  $" + order.getTotalTax().setScale(2, RoundingMode.HALF_UP));
        io.print("Total Cost:  $" + order.getTotalBill().setScale(2, RoundingMode.HALF_UP));
        io.print("");

    }

    public void errorMessage(String message) {
        io.print("");
        io.print("There was an error!");
        io.print("");
        io.print(message);
        io.readString("Press any key to continue");
    }

    public void exitMessage() {
        io.print("");
        io.print("Good Bye!");
    }

    public void displayOrderSuccessfullyCreated() {
        io.print("");
        io.print("Order created successfully");
    }

    public void displayOrderSuccessfullyRemoved() {
        io.print("");
        io.print("Order successfully removed");
    }

    public void unknownCommand() {
        io.print("");
        io.print("Invalid response. Please try again.");
    }

    public void editOrderBanner() {
        io.print("");
        io.print("Edit an Existing Order");
    }

    public void displaySuccessfulOrderEdit() {
        io.print("");
        io.print("Your order was successfully edited.");
    }

    public String editCustomerName(String customerName) {
        io.print("");
        io.print("------------------------------------------");
        io.print("CURRENT NAME: " + customerName + " <-----");
        return io.readString("Edit Name:  (press enter to keep the existing Name on file)");
    }

    public String editProductType(String productType) {
        io.print("");
        io.print("------------------------------------------");
        io.print("CURRENT PRODUCT TYPE: " + productType + " <-----");
        io.print("");
        io.print("Product Options: Carpet, Laminate, Wood, Tile");
        return io.readString("Edit Product Type:  (press enter to keep the existing Product Type on file)");
    }

    public String editState(String state) {
        io.print("");
        io.print("------------------------------------------");
        io.print("CURRENT STATE: " + state + " <-----");
        io.print("");
        io.print("Valid States: MN, IA, ND, WI");
        return io.readString("Edit State: (press enter to keep the existing State on file)");
    }

    public String editArea(BigDecimal area) {
        io.print("");
        io.print("------------------------------------------");
        io.print("CURRENT AREA: " + area + " <-----");
        return io.readString("Edit Area: (press enter to keep the existing Area on file)");
    }

    public void printPageDivider() {
        io.print("");
        io.print("------------------------------------------");
       
    }

    public void displayLineBreak() {
        io.print("");
    }

}
