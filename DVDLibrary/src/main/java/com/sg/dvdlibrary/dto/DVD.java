/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

/**
 *
 * @author Chad
 */
public class DVD {

    private String dvdTitle;
    private String releaseDate;
    private String dvdRating; // pg13, etc
    private String directorName;
    private String dvdStudio;
    private String userNote;

    public DVD(String dvdTitle, String releaseDate, String dvdRating, String directorName, String dvdStudio, String userNote) {
        this.dvdTitle = dvdTitle;
        this.releaseDate = releaseDate;
        this.dvdRating = dvdRating;
        this.directorName = directorName;
        this.dvdStudio = dvdStudio;
        this.userNote = userNote;
    }
    
    public String getDvdTitle() {
        return dvdTitle;
    }

    public void setDvdTitle(String dvdTitle) {
        this.dvdTitle = dvdTitle;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDvdRating() {
        return dvdRating;
    }

    public void setDvdRating(String dvdRating) {
        this.dvdRating = dvdRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getDvdStudio() {
        return dvdStudio;
    }

    public void setDvdStudio(String dvdStudio) {
        this.dvdStudio = dvdStudio;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
    
}
