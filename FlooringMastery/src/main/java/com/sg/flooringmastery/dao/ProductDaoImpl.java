/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Products;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chad
 */
public class ProductDaoImpl implements ProductDao {
     public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";

    Map<String, Products> product = new HashMap<>();

    public ProductDaoImpl() {
        product = new HashMap<>();
    }
    
    @Override
    public Products getProduct(String productType) {
         try {
             loadProducts();
         } catch (NumberFormatException ex) {
             System.out.println("Number input to file messed up");
         } catch (FileNotFoundException ex) {
             Logger.getLogger(ProductDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        Products prod = product.get(productType);

        return prod;
    }

    public void loadProducts() throws FileNotFoundException, NumberFormatException {
        Scanner scanner = null;
        File f = new File(PRODUCT_FILE);

        try {
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));
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

            Products prod = new Products();

            prod.setProductType(currentTokens[0]);
            prod.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            prod.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            product.put(prod.getProductType(), prod);
        }
        // close scanner
        scanner.close();

    }
}
