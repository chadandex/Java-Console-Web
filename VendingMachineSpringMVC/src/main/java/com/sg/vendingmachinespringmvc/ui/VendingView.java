/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.ui;

import com.sg.vendingmachinespringmvc.service.VendingServiceLayer;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Chad
 */
public class VendingView {
    private String systemMessage = "";
    private String changeMessage = "";
   
    public String systemMessage() {
        return systemMessage;
    }
    
    public String changeMessage() {
        return changeMessage;
    }
    
    public void clearSystemMessage() {
        systemMessage = "";
    }
    
    public void clearChangeMessage() {
        changeMessage = "";
    }
    
    public void displayChange(String msg) {
        changeMessage=msg;
    }
    
    public void insertChangeMsg(double moneyNeeded) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        
        systemMessage="Please insert: " + currencyFormatter.format(moneyNeeded);
    }
    
    public void thankYouMsg() {
        systemMessage="Thank you!!!";
    }
    
    public void soldOut() {
        systemMessage="SOLD OUT!!!";
    }
    
    public void productUnavailable() {
        systemMessage="Product Unavailable";
    }
}
