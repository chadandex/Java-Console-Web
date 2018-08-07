/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import static com.sg.flooringmastery.dao.ProductDaoImpl.PRODUCT_FILE;
import com.sg.flooringmastery.dto.Taxes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chad
 */
public class TaxesDaoImpl implements TaxesDao {
    public static final String TAXES_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";

    HashMap<String, Taxes> stateTax = new HashMap<>();

    public TaxesDaoImpl() {
        stateTax = new HashMap<>();
    }
    
    @Override
    public Taxes getTax(String stateName) {
        try {
            loadTax();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
        Taxes sTax = stateTax.get(stateName);

        return sTax;
    }

    public void loadTax() throws FileNotFoundException {
        Scanner scanner = null;
        File f = new File(TAXES_FILE);
        try {
            scanner = new Scanner(new BufferedReader(new FileReader(TAXES_FILE)));
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println("File not found -- Created empty");
            }
            System.out.println("File not found");
        }

        String currentLine;

        String[] currentTokens;
        
        while (scanner.hasNext()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Taxes tax = new Taxes();

            tax.setState(currentTokens[0]);
            tax.setTaxRate(new BigDecimal(currentTokens[1]));

            stateTax.put(tax.getState(), tax);
        }   

        // close scanner
        scanner.close();
    }
}
