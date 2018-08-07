/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Orders;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Chad
 */
public class OrderDaoImpl implements OrderDao {

    ArrayList<Orders> userOrders = new ArrayList<>();
    String programMode;

    public OrderDaoImpl(String ProgramMode) {
        this.programMode = ProgramMode;
    }

    @Override
    public List<Orders> displayOrder(String date) {

        return userOrders.stream()
                .filter(o -> o.getDate().equals(date))
                .collect(Collectors.toList());

    }

    @Override
    public boolean addOrder(Orders order) {

        userOrders.add(order);
        return true;
    }

    @Override
    public Orders editOrder(String date, int orderNum) {
        for (Orders o : userOrders) {
            if (date.equals(o.getDate()) || o.getOrderNumber() == orderNum) {
                return o;
            }

        }
        return null;
    }

    @Override
    public void removeOrder(String date, int orderNum) {
        boolean removed = false;
        boolean notFound = false;
        try {
            loadOrders(date);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Orders theOrd = null;
        for (Orders o : userOrders) {
            if (date.equals(o.getDate()) && o.getOrderNumber() == orderNum) {
                theOrd = o;
            } else {
                notFound = true;
            }

        }
        removed = userOrders.remove(theOrd); 
        if (removed){
            System.out.println("Order has been removed.\n**Remember to save!");
        }
        else {
            System.out.println("Order was not removed.\n");
            if (notFound){
                System.out.println("No order found.");
            }
        }
    }

    @Override
    public boolean saveOrder() {
        userOrders.stream().forEach((Orders o) -> {
            writeOrders(o.getDate());
        });
        return true;
    }

    @Override
    public void loadOrders(String date) throws FileNotFoundException {

        Orders ord = new Orders();
        List<Orders> myOrder = userOrders;
        for (Orders o : myOrder) {
            if (ord != o) {
                System.out.println(o);
                return;
            }
        }

        String ORDERS_FILE = "Orders_" + date + ".txt";
        String DELIMITER = ",";

        Scanner scanner = new Scanner(new BufferedReader(new FileReader(ORDERS_FILE)));

        String currentLine;

        String[] currentTokens;

        while (scanner.hasNext()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);
            Orders order = new Orders();
            order.setDate(date);
            order.setOrderNumber(new Integer(currentTokens[0]));
            order.setCustomerName(currentTokens[1]);
            order.setState(currentTokens[2]);
            order.setTaxRate(new BigDecimal(currentTokens[3]));
            order.setProductType(currentTokens[4]);
            order.setArea(new BigDecimal(currentTokens[5]));
            order.setCostPerSqFoot(new BigDecimal(currentTokens[6]));
            order.setLaborCostPerSqFoot(new BigDecimal(currentTokens[7]));
            order.setMaterialCost(new BigDecimal(currentTokens[8]));
            order.setLaborCost(new BigDecimal(currentTokens[9]));
            order.setTax(new BigDecimal(currentTokens[10]));
            order.setTotal(new BigDecimal(currentTokens[11]));
            userOrders.add(order);

        }
        // close scanner
        scanner.close();
    }

    @Override
    public void writeOrders(String date) {
        String trainingMode = "training";

        if (!programMode.equalsIgnoreCase(trainingMode)) {

            String ORDERS_FILE = "Orders_" + date + ".txt";
            String DELIMITER = ",";

            PrintWriter out = null;

            try {
                out = new PrintWriter(new FileWriter(ORDERS_FILE));
            } catch (IOException e) {
                System.out.println("File not found");
            }

            List<Orders> order = this.userOrders.stream()
                    .filter(o -> o.getDate().equals(date))
                    .collect(Collectors.toList());

            for (Orders o : order) {

                out.println(o.getOrderNumber() + DELIMITER
                        + o.getCustomerName() + DELIMITER
                        + o.getState() + DELIMITER
                        + o.getTaxRate() + DELIMITER
                        + o.getProductType() + DELIMITER
                        + o.getArea() + DELIMITER
                        + o.getCostPerSqFoot() + DELIMITER
                        + o.getLaborCostPerSqFoot() + DELIMITER
                        + o.getMaterialCost() + DELIMITER
                        + o.getLaborCost() + DELIMITER
                        + o.getTax() + DELIMITER
                        + o.getTotal());

            }
//             force PrintWriter to write line to the file
            out.flush();
            out.close();

        }

    }

}
