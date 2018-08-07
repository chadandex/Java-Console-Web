/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author Chad
 */
public class Orders {
    private int orderNumber;
    private String customerName;
    private String stateLoc;
    private String productType;
    private BigDecimal area;
    private String date;
    private BigDecimal laborCost;
    private BigDecimal materialCost;
    private BigDecimal costPerSqFoot;
    private BigDecimal laborCostPerSqFoot;
    private BigDecimal total;
    private BigDecimal taxRate;
    private BigDecimal tax;
    

    /**
     * @return the orderNumber
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * @param orderNumber the orderNumber to set
     */
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the laborCost
     */
    public BigDecimal getLaborCost() {
        return laborCost;
    }

    /**
     * @param laborCost the laborCost to set
     */
    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    /**
     * @return the materialCost
     */
    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    /**
     * @param materialCost the materialCost to set
     */
    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the tax
     */
    
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the costPerSqFoot
     */
    public BigDecimal getCostPerSqFoot() {
        return costPerSqFoot;
    }

    /**
     * @param costPerSqFoot the costPerSqFoot to set
     */
    public void setCostPerSqFoot(BigDecimal costPerSqFoot) {
        this.costPerSqFoot = costPerSqFoot;
    }

    /**
     * @return the laborCostPerSqFoot
     */
    public BigDecimal getLaborCostPerSqFoot() {
        return laborCostPerSqFoot;
    }

    /**
     * @param laborCostPerSqFoot the laborCostPerSqFoot to set
     */
    public void setLaborCostPerSqFoot(BigDecimal laborCostPerSqFoot) {
        this.laborCostPerSqFoot = laborCostPerSqFoot;
    }

    /**
     * @return the state
     */
    public String getState() {
        return stateLoc;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.stateLoc = state;
    }

    /**
     * @return the taxRate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * @param taxRate the taxRate to set
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }
}
