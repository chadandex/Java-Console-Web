/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.util.Objects;

/**
 *
 * @author Chad
 */
public class Inventory {
    private String itemName;
    private double itemCost;
    private int itemLeft; 

    public Inventory(String itemName, int itemLeft, double itemCost) {
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.itemLeft = itemLeft;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
    }

    public int getItemLeft() {
        return itemLeft;
    }

    public void setItemLeft(int itemLeft) {
        this.itemLeft = itemLeft;
    }

    public int takeOne() {
        return --itemLeft;
    }
    
    @Override
    public String toString() {
        return "Item Name: " + itemName;
    }
}
