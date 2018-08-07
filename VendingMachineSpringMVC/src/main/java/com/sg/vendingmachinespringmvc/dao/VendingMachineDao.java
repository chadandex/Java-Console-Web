package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Vending;
import java.util.List;

/**
 *
 * @author Chad
 */
public interface VendingMachineDao {
    
    public Vending addItem(Vending item);
    
    public List<Vending> getAllVending();
    
    public Vending getItemById(long id);
    
    public int takeOne(Vending vending);
    
    public int setQuantity(int currentQuan);
    
    public int getQuantity();
    
    public double addAmount(double amount);
    
    public double getAmount();
    
    public double getAmountDifference(double itemPrice, double currentMoney);
    
    public double getEndAmount(double itemPrice);
    
    public int setCurrentItem(int currentId);
    
    public int getCurrentItem();
    
    public double setCurrentPrice(double currentPrice);
    
    public double getCurrentPrice();
    
    public void clearVending();
}
