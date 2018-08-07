package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Vending;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chad
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao{
    private Map<Long, Vending> vendingMap = new HashMap<>();
    private static int itemId = 0;
    private double currentAmount = 0.0;
    private int currentItem = 0;
    private double moneyNeeded = 0.0;
    private double currentProdPrice = 0.0;
    private int currentQuantity = 0;
    
    @Override
    public Vending addItem(Vending item) {
        item.setItemId(itemId);
        itemId++;
        vendingMap.put(item.getItemId(), item);
        return item;
    }
    
    @Override
    public List<Vending> getAllVending() {
        Collection<Vending> c = vendingMap.values();
        return new ArrayList(c);
    }
    
    @Override
    public Vending getItemById(long id) {
        return vendingMap.get(id);
    }
    
    @Override
    public int takeOne(Vending vending) {
        return vending.takeOne();
    }
    
    @Override
    public int setQuantity(int currentQuan) {
        currentQuantity = currentQuan;
        return currentQuantity;
    }
    
    @Override
    public int getQuantity() {
        return currentQuantity;
    }

    @Override
    public double addAmount(double amount) {
        return currentAmount += amount;
    }
    
    @Override
    public double getAmount() {
        return currentAmount;
    }
    
    @Override
    public double getAmountDifference(double itemPrice, double currentMoney) {
        moneyNeeded = itemPrice - currentMoney;
        return moneyNeeded;
    }
    
    @Override
    public double getEndAmount(double itemPrice) {
        return currentAmount -= itemPrice;
    }
    
    @Override
    public int setCurrentItem(int currentId) {
        currentItem = currentId;
        return currentItem;
    }
    
    @Override
    public int getCurrentItem() {
        return currentItem;
    }
    
    @Override
    public double setCurrentPrice(double currentPrice) {
        currentProdPrice = currentPrice;
        return currentProdPrice;
    }
    
    @Override
    public double getCurrentPrice() {
        return currentProdPrice;
    }
    
    @Override
    public void clearVending() {
        currentAmount = 0.0;
        currentItem = 0;
    }
}
