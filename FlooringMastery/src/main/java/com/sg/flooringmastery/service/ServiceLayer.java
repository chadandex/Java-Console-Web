/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dto.Orders;
import java.util.List;

/**
 *
 * @author Chad
 */
public interface ServiceLayer {
    public boolean fileExist(String orderDate) 
            throws PersistenceException;
            
    List<Orders> displayOrder(String date);

    boolean addOrder(Orders order);

    Orders editOrder(String date, int orderNum);

    void removeOrder(String date, int orderNum);

    int newOrderNum(String date);

    boolean saveOrder();

    void loadOrders(String date);

    void writeOrders(String date);

}
