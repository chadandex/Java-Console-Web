/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Inventory;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chad
 */
public class VendingMachineFileImpl implements VendingMachineDao {
    private Map<String, Inventory> inventory = new HashMap<>();
    
    public static final String INV_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
   
    @Override
    public Inventory addItem(String itemId, Inventory inv) throws VendingMachinePersistenceException {
        Inventory newItem = inventory.put(itemId, inv);
        writeLibrary();
        return newItem;
    }

    //list out dvds
    @Override
    public List<Inventory> getAllItems() throws VendingMachinePersistenceException {
        try {
            loadLibrary();
        } catch (IOException ex) {
            Logger.getLogger(VendingMachineFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<Inventory>(inventory.values());
    }

    //give one dvd
    @Override
    public Inventory getItem(String itemId) throws VendingMachinePersistenceException {
        try {
            loadLibrary();
        } catch (IOException ex) {
            Logger.getLogger(VendingMachineFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventory.get(itemId);
    }

    @Override
    public Inventory removeItem(String itemId) throws VendingMachinePersistenceException {
        Inventory removedItem = inventory.remove(itemId);
        writeLibrary();
        return removedItem;
    }
    
    @Override
    public double getItemLeft(String item) throws VendingMachinePersistenceException {
        try {
            loadLibrary();
        } catch (IOException ex) {
            Logger.getLogger(VendingMachineFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (inventory.containsKey(item)) {
            return inventory.get(item).getItemLeft();
        } else {
            return 0;
        }
    }

    @Override
    public int takeOne(String item) throws VendingMachinePersistenceException {
        return inventory.get(item).takeOne();
    }

    
    @Override
    public double getItemCost(String item) throws VendingMachinePersistenceException {
        try {
            loadLibrary();
        } catch (IOException ex) {
            Logger.getLogger(VendingMachineFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inventory.get(item).getItemCost();
    }
    
    // load file
    private void loadLibrary() throws VendingMachinePersistenceException, IOException {
        Scanner scanner;
        File file = new File(INV_FILE);

        if (!file.exists()) {
            file.createNewFile();
        }
                   
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(INV_FILE)));

            // currentLine holds the most recent line read from the file
            String currentLine;
            // currentTokens holds each of the parts of the currentLine after it has
            // been split on our DELIMITER
            String[] currentTokens;
            // Go through LIBRARY_FILE line by line, decoding each line into a 
            // DVD object.
            // Process while we have more lines in the file
            while (scanner.hasNextLine()) {
                // get the next line in the file
                currentLine = scanner.nextLine();
                // break up the line into tokens
                currentTokens = currentLine.split(DELIMITER);
                // Create a new DVD object and put it into the map of DVDs
                inventory.put(currentTokens[0], new Inventory(currentTokens[0], Integer.parseInt(currentTokens[1]), Double.parseDouble(currentTokens[2])));
               
            }
            // close scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load inventory data into memory.", e);
        }
    }

    /**
     * Writes all DVDs in the roster out to a LIBRARY_FILE. See loadRoster
     * for file format.
     *
     * @throws VendingMachinePersistenceException if an error occurs writing to the file
     */
    public void writeLibrary() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INV_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save inventory data.", e);
        }

        // Write out the DVD objects to the roster file.
        List<Inventory> itemList = this.getAllItems();
        for (Inventory currentItem : itemList) {
            // write the  object to the file
            out.println(currentItem.getItemName() + DELIMITER
                    + currentItem.getItemLeft() + DELIMITER
                    + currentItem.getItemCost());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
