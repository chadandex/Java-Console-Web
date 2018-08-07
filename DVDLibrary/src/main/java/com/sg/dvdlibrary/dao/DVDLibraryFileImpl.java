/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
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
import java.io.*;

/**
 *
 * @author Chad
 */
public class DVDLibraryFileImpl implements DVDLibraryDao {
    private Map<String, DVD> dvds = new HashMap<>();
    
    public static final String LIBRARY_FILE = "dvdlibrary.txt";
    public static final String DELIMITER = "::";
   
    @Override
    public DVD addDVD(String dvdId, DVD dvd) throws DVDLibraryDaoException {
        DVD newDVD = dvds.put(dvdId, dvd);
        writeLibrary();
        return newDVD;
    }

    /*This code gets all of the DVD objects out of the DVDs map as a collection by 
    calling the values() method. Pass that returned collection into the 
    constructor for a new ArrayList.*/
    
    //list out dvds
    @Override
    public List<DVD> getAllDVD() throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList<DVD>(dvds.values());
    }

    //give one dvd
    @Override
    public DVD getDVD(String dvdId) throws DVDLibraryDaoException {
        loadLibrary();
        return dvds.get(dvdId);
    }

    @Override
    public DVD removeDVD(String dvdId) throws DVDLibraryDaoException {
        DVD removedDVD = dvds.remove(dvdId);
        writeLibrary();
        return removedDVD;
    }
    
    // load file
    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));

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
                dvds.put(currentTokens[0], new DVD(currentTokens[0], currentTokens[1], currentTokens[2], currentTokens[3], 
                        currentTokens[4], currentTokens[5]));
               
                // old code
                /*DVD currentDVD = new DVD(currentTokens[0]);
                // Set the remaining values on currentDVD manually
                currentDVD.setDvdTitle(currentTokens[0]);
                currentDVD.setReleaseDate(currentTokens[1]);
                currentDVD.setDvdRating(currentTokens[2]);
                currentDVD.setDirectorName(currentTokens[3]);
                currentDVD.setDvdStudio(currentTokens[4]);
                currentDVD.setUserNote(currentTokens[5]);*/

                // old code
                // Put DVD into the map using DVD Title as the key
                //dvds.put(currentDVD.getDvdTitle(), currentDVD);
            }
            // close scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException(
                    "-_- Could not load library data into memory.", e);
        }
    }

    /**
     * Writes all DVDs in the roster out to a LIBRARY_FILE. See loadRoster
     * for file format.
     *
     * @throws DVDLibraryDaoException if an error occurs writing to the file
     */
    private void writeLibrary() throws DVDLibraryDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("Could not save DVD data.", e);
        }

        // Write out the DVD objects to the roster file.
        List<DVD> dvdList = this.getAllDVD();
        for (DVD currentDVD : dvdList) {
            // write the DVD object to the file
            out.println(currentDVD.getDvdTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getDvdRating() + DELIMITER
                    + currentDVD.getDirectorName() + DELIMITER
                    + currentDVD.getDvdStudio() + DELIMITER
                    + currentDVD.getUserNote());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
