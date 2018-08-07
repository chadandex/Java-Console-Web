/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Inventory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chad
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao{
    private Inventory onlyItem;
    private List<Inventory> itemList = new ArrayList<>();
    
    public VendingMachineDaoStubImpl() {
        onlyItem.setItemName("Soda");
        onlyItem.setItemCost(1.99);
        onlyItem.setItemLeft(12);
       
        itemList.add(onlyItem);
    }

    @Override
    public double getItemLeft(String item) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getItemCost(String item) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Inventory addItem(String itemTitle, Inventory inv) throws VendingMachinePersistenceException {
        if (itemTitle.equals(onlyItem.getItemName())){
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public List<Inventory> getAllItems() throws VendingMachinePersistenceException {
        return itemList;
    }

    @Override
    public Inventory getItem(String itemTitle) throws VendingMachinePersistenceException {
        if (itemTitle.equals(onlyItem.getItemName())){
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public Inventory removeItem(String itemTitle) throws VendingMachinePersistenceException {
        if (itemTitle.equals(onlyItem.getItemName())){
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void writeLibrary() throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int takeOne(String item) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
