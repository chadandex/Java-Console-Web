/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Taxes;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 *
 * @author Chad
 */
public class TaxesDaoStubFileImpl implements TaxesDao {
    
       
    Taxes t;
    
    HashMap<String, Taxes> tax = new HashMap<>();
 
    public TaxesDaoStubFileImpl(){
        t = new Taxes();
        t.setState("PA");
        t.setTaxRate(new BigDecimal(6.75) );
        
        tax.put("PA", t);
        
    }

    @Override
    public Taxes getTax(String stateName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadTax() throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
