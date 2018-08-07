/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Products;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * @author Chad
 */
public class ProductDaoStubFileImpl implements ProductDao {
    Products p;
    
    HashMap<String, Products> prod = new HashMap<>();
    
    public ProductDaoStubFileImpl(){
        p = new Products();
        p.setProductType("Wood");
        p.setCostPerSquareFoot(new BigDecimal(5.15));
        p.setLaborCostPerSquareFoot(new BigDecimal(4.75));
        
        prod.put("Wood", p);
        
    }

    @Override
    public Products getProduct(String productName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadProducts() throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
