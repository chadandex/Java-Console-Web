/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.service.ServiceLayerImpl;
import com.sg.flooringmastery.ui.FlooringMasteryView;
import java.util.List;

/**
 *
 * @author Chad
 */
public class Controller {
    FlooringMasteryView view;
    ServiceLayerImpl service;

    public Controller(FlooringMasteryView view, ServiceLayerImpl service) {
        this.view = view;
        this.service = service;
    }
    
    public void run() throws PersistenceException {
        boolean keepGoing = true;
        int menuSelection = 0;

        while (keepGoing) {
        menuSelection = view.displayMenuSelection(); // show menu & get user input
        
            OUTER:
            switch (menuSelection) {
                case 1: // display orders ** working
                    String date = view.getOrderDate();
                    service.loadOrders(date);
                    List<Orders> order = service.displayOrder(date);
                    view.showOrder(order);
                    break;
                case 2: // add an order ** working
                    Orders userOrder = view.makeNewOrder();
                    
                    service.addOrder(userOrder);
                    saveOrder();
                    break;
                case 3: // edit an order ** working
                    editOrder();
                    saveOrder();
                    break;
                case 4: // remove an order ** working
                    removeOrder();
                    break;
                case 5: // save work ** working
                    saveOrder();
                    break;
                case 6: // exit program ** working
                    keepGoing = false;
                    break;
                default:
                    view.displayUnknownCommandBanner(); // unknown cmd msg
            }
        }
        view.displayExitBanner(); // exit message
    }

    // edit order
    private void editOrder() {
        String date = view.getOrderDate();
        
        service.loadOrders(date);
        List<Orders> order = service.displayOrder(date);
        view.showOrder(order);

        int orderNum = view.orderNum();
       
        Orders orderToEdit = service.editOrder(date, orderNum);
        view.newEditedOrder(orderToEdit, date);
    }
    
    private void saveOrder(){
        String add = view.saveOrder();
        String toLowerCase = add.toLowerCase(); // force lower case for switch
        char choice = toLowerCase.charAt(0);
        switch (choice) {
            case 'y':
                service.saveOrder();
                view.yesSaved();
                break;
            case 'n':
                view.notSaved();
                view.exitMain();
                break;
            default:
                view.notSaved();
        }
    }
    
     public void removeOrder(){
        String date = view.getOrderDate();
        int orderNum = view.orderNum();
        service.removeOrder(date, orderNum);
    }
    
}
