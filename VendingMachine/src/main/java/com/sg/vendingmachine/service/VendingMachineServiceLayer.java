/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Inventory;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Chad
 */
public interface VendingMachineServiceLayer {
    double getItemLeft(String item)
            throws VendingMachinePersistenceException;

    /**
     *
     * @param item
     * @return
     * @throws VendingMachinePersistenceException
     */
    double getItemCost(String item)
            throws VendingMachinePersistenceException;

    double getAvailable(String name, double money)
            throws VendingMachinePersistenceException;

    
    Inventory addItem(String itemTitle, Inventory inv)
            throws InsufficientFundException, NoItemInventoryException, VendingMachinePersistenceException;

    /**
     * @return 
     * @throws com.sg.vendingmachine.dao.VendingMachinePersistenceException
     */
    List<Inventory> getAllItems()
            throws VendingMachinePersistenceException;

    /**
     * @param itemTitle
     * @return 
     * @throws com.sg.vendingmachine.dao.VendingMachinePersistenceException
     */
    Inventory getItem(String itemTitle)
            throws VendingMachinePersistenceException;
    
    /*
     * @throws com.sg.vendingmachine.dao.VendingMachinePersistenceException
     */
    Inventory removeItem(String itemTitle)
            throws VendingMachinePersistenceException;
    
    String getChange(double money)
            throws InsufficientFundException;
    
    String buyItem(String item, BigDecimal userMoney, double money)
            throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundException;

}
