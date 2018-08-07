/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.service.ServiceLayerImpl;
import static java.lang.System.out;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author Chad
 */
public class FlooringMasteryView {
    UserIO io;

    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public String getOrderDate() {
        String orderDate;
        orderDate = io.readString("Enter order date (MMDDYYY): ");
        if (orderDate.contains("/")) {
            orderDate = orderDate.replace("/", ""); // keep slashes out of file name
        }
        if (orderDate.contains("-")) {
            orderDate = orderDate.replace("-", ""); // keep dashes out of file name
        }
        if (orderDate.contains(",")) {
            orderDate = orderDate.replace(",", ""); // keep commas out of file name
        }

        return orderDate;
    }

    public int orderNum() {
        return io.orderNum("Enter order number: ");
    }

    public Orders makeNewOrder() {
        String date = io.readString("Enter order date (MMDDYYY):");
        String name = io.readString("Enter customer name: ");
        io.print("-- Material available: Carpet, Laminate, Tile, Wood --");
        String materialType = io.readString("Enter material type: ");
        // force user to enter correct input
        while (!materialType.toLowerCase().equals("carpet") && !materialType.toLowerCase().equals("laminate") && !materialType.toLowerCase().equals("tile") && !materialType.toLowerCase().equals("wood")){
            io.print("Incorrect material. Please try again");
            materialType = io.readString("Enter material type(Carpet, Laminate, Tile, Wood): ");
        }
         //force program to capitalize first letter
        String temp = materialType.substring(0, 1).toUpperCase();
        String materialCapitalized = temp + materialType.substring(1);
        materialType = materialCapitalized;
        
        
        String state = io.readString("Enter state location (OH, PA, MI, or IN): ");
        while (!state.toLowerCase().equals("oh") && !state.toLowerCase().equals("pa") && !state.toLowerCase().equals("mi") && !state.toLowerCase().equals("in")){
            io.print("Incorrect state location. Please try again");
            state = io.readString("Enter state location (OH, PA, MI, or IN): ");
        }
         //force state name capitalized
        String stateCapitalized = state.substring(0, 2).toUpperCase();
        state = stateCapitalized;
        
        BigDecimal area = io.bigDecimal("Enter the area: ");

        Orders order = new Orders();
        order.setDate(date);
        order.setCustomerName(name);
        order.setProductType(materialType);
        order.setState(state.toUpperCase());
        order.setArea(area);

        return order;
    }

    public void showOrder(List<Orders> ord) {
        for (Orders o : ord) {
            out.print("\n-- Order Information -- \nOrder Number: "
                    + o.getOrderNumber() + "\nCustomer name: "
                    + o.getCustomerName() + "\nMaterial: "
                    + o.getProductType() + "\nState Location: "
                    + o.getState() + "\nTax Rate: "
                    + o.getTaxRate() + "%\nArea: "
                    + o.getArea().setScale(2, RoundingMode.HALF_UP) + "\nCost per Square Foot: $"
                    + o.getCostPerSqFoot().setScale(2, RoundingMode.HALF_UP) + "\nLabor Cost per Square Foot: $"
                    + o.getLaborCostPerSqFoot().setScale(2, RoundingMode.HALF_UP) + "\nMaterial Cost: $"
                    + o.getMaterialCost().setScale(2, RoundingMode.HALF_UP) + "\nLabor Cost: $"
                    + o.getLaborCost().setScale(2, RoundingMode.HALF_UP) + "\nTax: $"
                    + o.getTax().setScale(2, RoundingMode.HALF_UP) + "\nTotal: $"
                    + o.getTotal().setScale(2, RoundingMode.HALF_UP) + "\n");

        }
    }
    
    public int displayMenuSelection() {
        int selection;
        io.print("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("*  <<Flooring Program>>");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Save Current Work");
        io.print("* 6. Quit");
        io.print("*");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");

        selection = optionSelect(); // get user input
        return selection;
    }

    public int optionSelect() {
        int menuSelection;
        menuSelection = io.readInt("Please select from the above choices: ", 1, 6);
        io.print("\n");
        return menuSelection;
    }

    public Orders newEditedOrder(Orders ord, String date) {
        io.print("** If not changing certain detail, hit enter to keep it the same **");
        if (ord == null) {
            io.print("Order not found!"); // for personal error checking
        }

        String name = io.readString("Enter customer name(" + ord.getCustomerName() + "):");
        if (!name.isEmpty()) {
            ord.setCustomerName(name);
        }

        io.print("-- States Available: OH, PA, MI, or IN --");
        String state = io.readString("Enter new state location(" + ord.getState() + "): ");
        if (!state.isEmpty()) {
            ord.setState(state);
        }

        io.print("-- Material Available: Carpet, Laminate, Tile, or Wood --");
        String productType = io.readString("Enter new material information(" + ord.getProductType() + "): ");
        if (!productType.isEmpty()) {
            ord.setProductType(productType);
        }

        String area = io.readString("Enter new area in sq feet(" + ord.getArea() + "): ");
        if (!area.isEmpty()) {
            ord.setArea(new BigDecimal(area));
        }

        Orders order = new Orders();
        order.setDate(date);
        order.setCustomerName(name);
        order.setProductType(productType);
        order.setState(state);
        order.setArea(new BigDecimal(area));

        return order;
    }

    public String saveOrder() {
        String add = io.readString("Confirm order(s)? (Y/N): ");
        while (!add.equalsIgnoreCase("y") && !add.equalsIgnoreCase("n"))  {
            // force user enter yes or no properly
            add = io.readString("Please enter Y (for yes) or N (for no):");
        }
        
        return add;
    }
    
    public void yesSaved(){
        io.print("Order saved!");
    }
    
    public void notSaved(){
        io.print("Order not saved!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void exitMain() {
        io.print("Exiting To Main Menu");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
}
