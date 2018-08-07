/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DVDLibraryDao;
import com.sg.dvdlibrary.dao.DVDLibraryDaoException;
import com.sg.dvdlibrary.dto.DVD;
import com.sg.dvdlibrary.ui.DVDLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author Chad
 */
public class DVDLibraryController {
    private UserIO io = new UserIOConsoleImpl();
    DVDLibraryView view;
    DVDLibraryDao dao;

    public DVDLibraryController(DVDLibraryDao dao, DVDLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    public void run() throws DVDLibraryDaoException {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            io.print("Main Menu");
            io.print("1. Add a DVD");
            io.print("2. Remove a DVD");
            io.print("3. Edit a DVD");
            io.print("4. List all DVDs");
            io.print("5. Search for DVD");
            io.print("6. Exit");

            menuSelection = io.readInt("Please select from the above choices.\n-", 1, 6);

            switch (menuSelection) {
                case 1: // add dvd *working
                    createDVD();
                    break;
                case 2: // remove dvd *working
                    removeDVD();
                    break;
                case 3: // edit info *working
                    editDVD();
                    break;
                case 4: // list all dvds *working
                    view.displayDVDList(dao.getAllDVD());
                    break;
                case 5: // search dvd *working
                    findDVD();
                    break;
                case 6: // end program
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    }
    
    private void createDVD() throws DVDLibraryDaoException {
        view.displayCreateDVDBanner();
        DVD newDVD = view.getNewDVDInfo();
        dao.addDVD(newDVD.getDvdTitle(), newDVD);
        view.displayCreateSuccessBanner();
    }
    
    // primitive way to edit; remove found dvd and have them enter info again with their changes
    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();
        String title = io.readString("Search for Title: ");
        DVD dvd = dao.getDVD(title);
        if (dvd != null) {
            view.displayDVD(dvd); // show user the title info they selected
            dao.removeDVD(title); // remove the title info for editing
            io.print("=Edit this Title below="); // let them edit it (edit all of it for ease in this program)
            DVD newDVD = view.getNewDVDInfo();
            dao.addDVD(newDVD.getDvdTitle(), newDVD);
        } else {
            io.print("DVD Not Found");
            io.print("Please try again.\n");
        }
        view.displayCreateSuccessBanner();
    }
    
    private void findDVD() throws DVDLibraryDaoException {
        String title = io.readString("Search for Title: ");
        DVD dvd = dao.getDVD(title);
        if (dvd != null) {
            view.displayDVD(dvd);
        } else {
            io.print("DVD Not Found");
            io.print("Please try again.\n");
        }
    }
    
    private void removeDVD() throws DVDLibraryDaoException {
         view.displayRemoveDVDBanner();
        String title = io.readString("Which Title to Remove: ");
        DVD dvd = dao.getDVD(title);
        if (dvd != null) {
            dao.removeDVD(title);
            view.displayRemoveSuccessBanner();
        } else {
            io.print("DVD Not Found");
            io.print("Please try again.\n");
        }
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
