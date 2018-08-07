/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Orders;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 * @author Chad
 */
public interface OrderDao {
    List<Orders> displayOrder(String date);

    boolean addOrder(Orders order);

    Orders editOrder(String date, int orderNum);
            
    void removeOrder(String date, int orderNum);

    boolean saveOrder();

    void writeOrders(String date);

    void loadOrders(String date) 
            throws FileNotFoundException;
}
