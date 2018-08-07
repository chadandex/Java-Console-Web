/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Inventory;
import com.sg.vendingmachine.service.InsufficientFundException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Chad
 */
public class VendingMachineController {
    VendingMachineView view;
    VendingMachineServiceLayer service;

    //Implement a constructor that initializes these members.
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws InsufficientFundException, NoItemInventoryException, VendingMachinePersistenceException {
        boolean keepGoing = true;
        int menuSelection = 0;
        int adminSelection = 0;
        BigDecimal userMoney;
        double money;

        view.beginMsg();
        view.displayItemList(service.getAllItems()); //display item list
        view.fillerLine();

        money = view.insertMoney(); //user insert money

        while (keepGoing) {
            userMoney = new BigDecimal(money);
            BigDecimal displayVal = userMoney.setScale(2, RoundingMode.HALF_UP);

            view.menuOptions(displayVal);
    
            menuSelection = view.optionSelect();

            OUTER:
            switch (menuSelection) {
                case 1: // picking item *works
                    String item = view.itemSelected();
                    String result;
                    result = service.buyItem(item, userMoney, money);
                    view.displaySrvMsg(result);
                    money = service.getAvailable(item, money);
                    break;
                case 2: // list items *works
                    view.displayItemList(service.getAllItems());
                    break;
                case 3: // insert more money *works
                    double temp;
                    temp = view.insertMoney();
                    money += temp;
                    view.moneyAdded();
                    break;
                case 4: // 'admin' controls to add/remove products *works
                    view.adminControls();
                    adminSelection = view.adminOptions();
                    switch (adminSelection) {
                        case 1: //add item
                            createItem();
                            break;
                        case 2: //remove item
                            removeItem();
                            break;
                        default:
                            view.exitControls();
                            break OUTER;
                    }
                    break;
                case 5: // exit with change *works
                    String changeMsg;
                    view.remainingChgMsg();
                    changeMsg = service.getChange(money);
                    view.displaySrvMsg(changeMsg);
                    
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
        exitMessage();
    }
    
    // admin create (add) items
    private void createItem() throws InsufficientFundException, NoItemInventoryException, VendingMachinePersistenceException {
        Inventory newItem = view.getNewItemInfo();
        service.addItem(newItem.getItemName(), newItem);
    }
    
    // admin remove items
    public void removeItem() throws VendingMachinePersistenceException {
        String title = "";
        title = view.removeItemMsg();
        Inventory inv = service.getItem(title);
        if (inv != null) {
            service.removeItem(title);
            view.itemRemovedMsg();
        } else {
            view.itemNtFndMsg();
        }
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }
}
