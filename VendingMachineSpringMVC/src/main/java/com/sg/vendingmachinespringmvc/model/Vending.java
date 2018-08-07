package com.sg.vendingmachinespringmvc.model;

import java.util.Objects;

/**
 *
 * @author Chad
 */
public class Vending {
    
    private long itemId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private double userMoney;

    public Vending(String productName, double productPrice, int productQuantity){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }
 
    public int takeOne() {
        return --productQuantity;
    }
    
    public long getItemId() {
        return itemId+1;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(double userMoney) {
        this.userMoney = userMoney;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (int) (this.itemId ^ (this.itemId >>> 32));
        hash = 31 * hash + Objects.hashCode(this.productName);
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.productPrice) ^ (Double.doubleToLongBits(this.productPrice) >>> 32));
        hash = 31 * hash + (int) (this.productQuantity ^ (this.productQuantity >>> 32));
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.userMoney) ^ (Double.doubleToLongBits(this.userMoney) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vending other = (Vending) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (Double.doubleToLongBits(this.productPrice) != Double.doubleToLongBits(other.productPrice)) {
            return false;
        }
        if (this.productQuantity != other.productQuantity) {
            return false;
        }
        if (Double.doubleToLongBits(this.userMoney) != Double.doubleToLongBits(other.userMoney)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        return true;
    }

}
