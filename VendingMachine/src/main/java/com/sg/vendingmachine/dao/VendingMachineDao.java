/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Inventory;
import java.util.List;

/**
 *
 * @author Chad
 */
public interface VendingMachineDao {
    
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

    
    Inventory addItem(String itemTitle, Inventory inv)
            throws VendingMachinePersistenceException;

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

    public void writeLibrary()
            throws VendingMachinePersistenceException;
    
     int takeOne(String item)
             throws VendingMachinePersistenceException;
}
