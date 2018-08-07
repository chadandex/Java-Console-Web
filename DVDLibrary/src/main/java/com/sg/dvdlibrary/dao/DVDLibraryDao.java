/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.DVD;
import java.util.List;

/**
 *
 * @author Chad
 */
public interface DVDLibraryDao {
    
    //void save();
    /**
     * @throws com.sg.dvdlibrary.dao.DVDLibraryDaoException
     */
    DVD addDVD(String dvdTitle, DVD dvd)
            throws DVDLibraryDaoException;

    /**
     * @throws com.sg.dvdlibrary.dao.DVDLibraryDaoException
     */
    List<DVD> getAllDVD()
            throws DVDLibraryDaoException;

    /**
     * @throws com.sg.dvdlibrary.dao.DVDLibraryDaoException
     */
    DVD getDVD(String dvdTitle)
            throws DVDLibraryDaoException;
    
    /*
     * @throws com.sg.dvdlibrary.dao.DVDLibraryDaoException
     */
    DVD removeDVD(String dvdTitle)
            throws DVDLibraryDaoException;
}
