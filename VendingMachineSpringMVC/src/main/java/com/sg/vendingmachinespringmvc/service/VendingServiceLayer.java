/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Vending;
import java.util.List;

/**
 *
 * @author Chad
 */
public interface VendingServiceLayer {
    
    public void populateVendingMachine();
    
    public void loadOnce();
    
    public void clearVending();
    
    public int takeOne(Vending vending);
    
    public List<Vending> getAllVending();
    
    public Vending getItemById(long id);
    
    public String getChange(double money);
    
    public double getAmountDifference(double currentMoney, double itemPrice);
    
    public double getEndAmount(double itemPrice);
    
    public int setQuantity(int currentQuan);
    
    public int getQuantity();
    
    public double setCurrentPrice(double currentPrice);
    
    public double getCurrentPrice();
    
    public double getAmount();
    
    public double addDollar();
    
    public double addQuarter();
    
    public double addDime();
    
    public double addNickel();
            
    public int getCurrentItem();
    
    public int setCurrentItem(int id);
}
