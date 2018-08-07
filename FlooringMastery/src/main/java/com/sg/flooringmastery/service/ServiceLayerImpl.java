/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringMasteryAuditDao;
import com.sg.flooringmastery.dao.OrderDao;
import com.sg.flooringmastery.dao.PersistenceException;
import com.sg.flooringmastery.dao.ProductDao;
import com.sg.flooringmastery.dao.TaxesDao;
import com.sg.flooringmastery.dto.Orders;
import com.sg.flooringmastery.dto.Products;
import com.sg.flooringmastery.dto.Taxes;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Chad
 */
public class ServiceLayerImpl implements ServiceLayer {
    ArrayList<Orders> orders = new ArrayList<>();

    OrderDao orderDao;
    ProductDao prodDao;
    TaxesDao taxDao;
    FlooringMasteryAuditDao auditDao;

    public ServiceLayerImpl(OrderDao orderDao, ProductDao prodDao, TaxesDao taxDao, FlooringMasteryAuditDao auditDao) {
        this.orderDao = orderDao;
        this.prodDao = prodDao;
        this.taxDao = taxDao;
        this.auditDao = auditDao;
    }
    
    // make sure program is finding file
    @Override
    public boolean fileExist(String orderDate) throws PersistenceException {
        boolean fileExist = false;
        File file = new File("E:\\Documents\\Guild\\chadandexler-apprentice-individual-work\\FlooringMastery0");

        List<String> filesContainingSubstring = new ArrayList<String>();

        if (file.exists() && file.isDirectory()) {
            String[] files = file.list();
            for (String fileName : files) {
                if (fileName.contains(orderDate)) {
                    filesContainingSubstring.add(fileName);
                }
            }
        }
        for (String fileName : filesContainingSubstring) {
            System.out.println("- Order Found -");
            fileExist = true;
        }

        return fileExist;
    }

    private Orders calculateOrder(Orders order) {

        Products newProd = prodDao.getProduct(order.getProductType());

        Taxes newTax = taxDao.getTax(order.getState());

        order.setTaxRate(newTax.getTaxRate());
        order.setCostPerSqFoot(newProd.getCostPerSquareFoot());
        order.setLaborCostPerSqFoot(newProd.getLaborCostPerSquareFoot());

        String date = order.getDate();
        order.setOrderNumber(newOrderNum(date));

        order.getOrderNumber();
        BigDecimal area = order.getArea();
        BigDecimal taxRate = order.getTaxRate();
        BigDecimal CostPerSquareFoot = order.getCostPerSqFoot();
        BigDecimal LabCostPerSquareFoot = order.getLaborCostPerSqFoot();
        BigDecimal materialCost = CostPerSquareFoot.multiply(area);
        order.setMaterialCost(materialCost);

        BigDecimal theTax = taxRate.divide(new BigDecimal(100));

        BigDecimal laborCost = LabCostPerSquareFoot.multiply(area);
        order.setLaborCost(laborCost);
        BigDecimal productCost = laborCost.add(materialCost);

        BigDecimal tax = productCost.multiply(theTax);
        order.setTax(tax);

        BigDecimal total = productCost.add(tax);
        order.setTotal(total);
        return order;

    }
    
    @Override
    public int newOrderNum(String date) {
        loadOrders(date);

        List<Orders> myOrders = orderDao.displayOrder(date);

        int maxOrderNum = 0;
        for (Orders o : myOrders) {
            int orderNum = o.getOrderNumber();
            if (orderNum >= maxOrderNum) {
                maxOrderNum = orderNum;
            }
        }
        maxOrderNum++;

        return maxOrderNum;
    }

    @Override
    public List<Orders> displayOrder(String date) {
        List<Orders> order = orderDao.displayOrder(date);

        return order;
    }

    public boolean updateOrder(Orders updateOrders) {
        calculateOrder(updateOrders);
        orderDao.addOrder(updateOrders);

        return true;
    }

    @Override
    public boolean addOrder(Orders order) {

        return orderDao.addOrder(calculateOrder(order));
    }

    @Override
    public Orders editOrder(String date, int orderNum) {

        return orderDao.editOrder(date, orderNum);
    }

    @Override
    public void removeOrder(String date, int orderNum) {

        orderDao.removeOrder(date, orderNum);
    }

    @Override
    public boolean saveOrder() {

        orderDao.saveOrder();
        return true;
    }

    @Override
    public void loadOrders(String date) {
        try {
            orderDao.loadOrders(date);
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file.\nCreating file...");
            // only used here for doing orders, telling user file is getting created
        }
    }

    @Override
    public void writeOrders(String date) {

        orderDao.writeOrders(date);
    }
}
