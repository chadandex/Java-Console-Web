/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Chad
 */
public class DVDLibraryView {
    UserIO io;
    
    //implement a constructor that initializes the io member field.
    public DVDLibraryView(UserIO io) {
        this.io = io;
    }
    
    public DVD getNewDVDInfo() {
        String dvdTitle = io.readString("Please enter DVD Title");
        String dvdReleaseDate = io.readString("Please enter the Release Date");
        String dvdRating = io.readString("Please enter the DVD Rating");
        String dvdDirector = io.readString("Please enter the Director name");
        String dvdStudio = io.readString("Please enter the Studio");
        String dvdUserNote = io.readString("Please enter any user notes");
        DVD currentDVD = new DVD(dvdTitle, dvdReleaseDate, dvdRating, dvdDirector, dvdStudio, dvdUserNote);
        currentDVD.setDvdTitle(dvdTitle);
        currentDVD.setReleaseDate(dvdReleaseDate);
        currentDVD.setDvdRating(dvdRating);
        currentDVD.setDirectorName(dvdDirector);
        currentDVD.setDvdStudio(dvdStudio);
        currentDVD.setUserNote(dvdUserNote);
        return currentDVD;
    }

    public void displayCreateDVDBanner() {
        io.print("=== Create DVD ===");
    }
    
    public void displayEditDVDBanner() {
        io.print("=== Edit DVD ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created.  Please hit enter to continue");
    }

    public void displayDVDList(List<DVD> dvdList) {
        for (DVD currentDVD : dvdList) {
            io.print("Title: " + currentDVD.getDvdTitle() + "\nRelease Date: "
                    + currentDVD.getReleaseDate() + "\nRating:  "
                    + currentDVD.getDvdRating() + "\nDirector: "
                    + currentDVD.getDirectorName() + "\nStudio: "
                    + currentDVD.getDvdStudio() + "\nUser Note: "
                    + currentDVD.getUserNote() + "\n\n ");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }

    public void displayDisplayDVDBanner() {
        io.print("=== Display DVD ===");
    }

    public String getDVDIdChoice() {
        return io.readString("Please enter the DVD Title.");
    }

    public void displayDVD(DVD dvd) {
        if (dvd != null) {
            io.print("Title: "
                    + dvd.getDvdTitle() + "\nRelease Date: "
                    + dvd.getReleaseDate() + "\nRating:  "
                    + dvd.getDvdRating() + "\nDirector: "
                    + dvd.getDirectorName() + "\nStudio: "
                    + dvd.getDvdStudio() + "\nUser Note: "
                    + dvd.getUserNote() + "\n\n ");
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }

    public void displayRemoveDVDBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("DVD successfully removed. Please hit enter to continue.");
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String errorMsg) {
	    io.print("=== ERROR ===");
	    io.print(errorMsg);
	}
}
