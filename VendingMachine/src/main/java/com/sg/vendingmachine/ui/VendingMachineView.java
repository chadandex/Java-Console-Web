/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Inventory;
import com.sg.vendingmachine.service.InsufficientFundException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.service.VendingMachineServiceLayerImpl;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Chad
 */
public class VendingMachineView {
    UserIO io;
    VendingMachineServiceLayer service;
    
    //implement a constructor that initializes the io member field.
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public Inventory getNewItemInfo() {
        String itemName = io.readString("Enter item name: ");
        int itemLeft = io.readInt("Enter how many of item: ");
        double itemCost = io.readDouble("Enter item's cost: ");
        Inventory currentItem = new Inventory(itemName, itemLeft, itemCost);
        currentItem.setItemName(itemName);
        currentItem.setItemCost(itemCost);
        currentItem.setItemLeft(itemLeft);
        return currentItem;
    }
    
    public void adminControls() {
        io.print("Add or Remove Items");
        io.print("1. Add");
        io.print("2. Remove");
        io.print("3. Exit");
    }

    public void displayItemList(List<Inventory> itemList) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        for (Inventory currentItem : itemList) {
            io.print("Name: "
                    + currentItem.getItemName() + " | Price: "
                    + currencyFormatter.format(currentItem.getItemCost()) + " | Remaining: "
                    + currentItem.getItemLeft()+ "\n");
        }
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void fillerLine(){
        io.print("-=-=-=-");
    }
    
    public void beginMsg(){
        io.print("Vending Machine");
        io.print("-=-=-=-\n");
        
    }
    public double insertMoney(){
        double money;
        money = io.readDouble("Insert Money: ", 0.01, 100);
        return money;
    }
    
    public void menuOptions(BigDecimal displayVal) {
        io.print("Choose Below");
        io.print("1. Choose An Item");
        io.print("2. List All items");
        io.print("3. Insert More Money");
        io.print("4. Admin Controls");
        io.print("5. Exit with change");
        io.print("-- Your current money: $" + displayVal + " --\n");
    }
    
    public int optionSelect(){
        int menuSelection;
        menuSelection = io.readInt("Please select from the"
                    + " above choices: ", 1, 5);
        io.print("\n");
        return menuSelection;
    }
    
    public String removeItemMsg() throws VendingMachinePersistenceException {
        String title = io.readString("Enter name of product to remove: ");
        
        return title;
    }
    
    public String itemSelected(){
        String item = io.readString("Item Name: ");
        
        return item;
    }
    
    public int adminOptions(){
        int adminSelection;
        adminSelection = io.readInt("Enter option: ", 0, 3);
        
        return adminSelection;
    }
    
    public void moneyAdded(){
        io.print("Money added.\n");
    }
    
    public void exitControls(){
        io.print("Exiting Controls");
    }
    
    public void displaySrvMsg(String msg){
        io.print(msg);
    }
    
    public void remainingChgMsg(){
        io.print("[ Remaining Change ]");
    }
    
    public void itemRemovedMsg(){
        io.print("Item Removed.\n");
    }
        
    public void itemNtFndMsg(){
        io.print("Item Not Found");
        io.print("Please try again.\n");
    }
}
