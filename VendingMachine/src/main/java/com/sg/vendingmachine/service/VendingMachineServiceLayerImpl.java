/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineFileImpl;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Inventory;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chad
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private Map<String, Inventory> inventory = new HashMap<>();
    private VendingMachineDao dao;
    Change usersChange = new Change(0.0,0.0,0.0,0.0);
    private VendingMachineAuditDao auditDao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    private void validateInventoryData(Inventory inv) throws
        NoItemInventoryException {

    if (inv.getItemName()== null
            || inv.getItemName().trim().length() == 0
            || inv.getItemCost() == 0.0
            || inv.getItemLeft() == 0) {

        throw new NoItemInventoryException(
                "ERROR: All fields [Name, Cost, Items Available] are required.");
    }
}
   
    @Override
    public Inventory addItem(String itemId, Inventory inv) throws InsufficientFundException, NoItemInventoryException, VendingMachinePersistenceException {
        /*if (dao.addItem(inv.getItemName(itemId)) != null) {
            throw new VendingMachineDuplicateIdException(
                "ERROR: Could not create student.  Student Id "
                + inv.getItemName()
                + " already exists");
        }
        validateInventoryData(inv);*/
        
        return dao.addItem(itemId, inv);
    }

    //list out dvds
    @Override
    public List<Inventory> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

    //give one dvd
    @Override
    public Inventory getItem(String itemId) throws VendingMachinePersistenceException {
        return dao.getItem(itemId);
    }

    @Override
    public Inventory removeItem(String itemId) throws VendingMachinePersistenceException {
        return dao.removeItem(itemId);
    }
    
    @Override
    public double getItemLeft(String item) throws VendingMachinePersistenceException {
        return dao.getItemLeft(item);
    }
    
    @Override
    public double getItemCost(String item) throws VendingMachinePersistenceException {
        return dao.getItemCost(item);
    }
    
    @Override
    public String buyItem(String item, BigDecimal userMoney, double money) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundException {
        BigDecimal displayVal = userMoney.setScale(2, RoundingMode.HALF_UP);
        String result = "";
        
        try {
            if (dao.getItemLeft(item) > 0) {
                if (dao.getItemCost(item) <= money) {
                    result = "You bought: " + item;
                } else { // not enough money
                    result = result + "\nMoney Left: " + displayVal + "\n";
                    throw new InsufficientFundException("Insufficient Funds!");
                }
            } else {
                throw new NoItemInventoryException("Out of Stock");
            }
        } catch (VendingMachinePersistenceException ex) {
            Logger.getLogger(VendingMachineController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public String getChange(double money) throws InsufficientFundException {
        return usersChange.getChange(money);
    }
    
    @Override
    public double getAvailable(String name, double money) throws VendingMachinePersistenceException {
        double cost = dao.getItemCost(name);
        if (money >= cost && dao.getItemLeft(name) > 0) {
            dao.takeOne(name);
            dao.writeLibrary();
            
            return money -= cost;
        } else {
            return -1;
        }
    }
}
