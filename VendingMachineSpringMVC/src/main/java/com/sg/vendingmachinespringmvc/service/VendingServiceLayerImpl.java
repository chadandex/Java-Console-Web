/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Vending;
import java.util.List;

/**
 *
 * @author Chad
 */
public class VendingServiceLayerImpl implements VendingServiceLayer {
    VendingMachineDao dao;
    Change change = new Change(0.0,0.0,0.0,0.0);
    private boolean load = false;
    
    public VendingServiceLayerImpl(VendingMachineDao dao){
        this.dao = dao;
    }
    
    @Override
    public void populateVendingMachine() {
        dao.addItem(new Vending("Snickers", 1.85, 9));
        dao.addItem(new Vending("M&Ms", 1.50, 2));
        dao.addItem(new Vending("Pringles", 2.10, 5));
        dao.addItem(new Vending("Reese's", 1.85, 4));
        dao.addItem(new Vending("Pretzels", 1.25, 9));
        dao.addItem(new Vending("Twinkies", 1.95, 3));
        dao.addItem(new Vending("Doritos", 1.75, 11));
        dao.addItem(new Vending("Almond Joy", 1.85, 0));
        dao.addItem(new Vending("Trident", 1.95, 6));
        load = true;
    }
    
    @Override
    public void loadOnce() {
        if (load == false){
            populateVendingMachine();
        }
    }
    
    @Override
    public void clearVending() {
        dao.clearVending();
    }
    
    @Override
    public int takeOne(Vending vending) {
        return dao.takeOne(vending);
    }
    
    @Override
    public List<Vending> getAllVending() {
        return dao.getAllVending();
    }
    
    @Override
    public Vending getItemById(long id) { 
        return dao.getItemById(id);
    }
    
    @Override
    public String getChange(double money) {
        return change.getChange(money);
    }
    
    @Override
    public double getAmountDifference(double itemPrice, double currentMoney) {
        return dao.getAmountDifference(itemPrice, currentMoney);
    }
    
    @Override
    public double getEndAmount(double itemPrice) {
        return dao.getEndAmount(itemPrice);
    }
    
    @Override
    public int setQuantity(int currentQuan) {
        return dao.setQuantity(currentQuan);
    }
    
    @Override
    public int getQuantity() {
        return dao.getQuantity();
    }
    
    @Override
    public double setCurrentPrice(double currentPrice) {
        return dao.setCurrentPrice(currentPrice);
    }
    
    @Override
    public double getCurrentPrice() {
        return dao.getCurrentPrice();
    }
    
    @Override
    public double getAmount() {
        return dao.getAmount();
    }
    
    @Override
    public double addDollar() {
        return dao.addAmount(1);
    }
    
    @Override
    public double addQuarter() {
        return dao.addAmount(.25);
    }
    
    @Override
    public double addDime() {
        return dao.addAmount(.10);
    }
    
    @Override
    public double addNickel() {
        return dao.addAmount(.05);
    }
    
    @Override
    public int getCurrentItem() {
        return dao.getCurrentItem();
    }
    
    @Override
    public int setCurrentItem(int id) {
        return dao.setCurrentItem(id);
    }
}
