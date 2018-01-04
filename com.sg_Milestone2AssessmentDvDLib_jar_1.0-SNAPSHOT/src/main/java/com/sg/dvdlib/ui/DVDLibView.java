/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlib.ui; 

import java.util.List;
import com.sg.dvdlib.dto.DVDLib;
/**
 *
 * @author jesse
 */
public class DVDLibView {
    
     private UserIO io;
    
    public DVDLibView(UserIO io) {
        this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVD Titles");
        io.print("2. Create New DVD");
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit a DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);

    }
    
     public void displayDisplayAllBanner() {
	    io.print("=== Display All DVD's ==="); //Menu 1.
	}
    
    public void displayDVDList(List<DVDLib> dvdList) { //Menu 1.
	    for (DVDLib currentDVD : dvdList) {
	        io.print(currentDVD.getTitle() + " "
	                + currentDVD.getGenre() + " "
	                + currentDVD.getYear() + " "
                        + currentDVD.getStudio() + " "
                        + currentDVD.getDirectorsName() + " "
                        + currentDVD.getUserRating() + " "
                        + currentDVD.getMpaaRating());
	    }
	    io.readString("Please hit enter to continue.");
	}
    
    public void displayCreateDVDBanner() { //Menu 2.
        io.print("=== Create DVD ===");
    }

    public void displayCreateSuccessBanner() { //Menu 2.
        io.readString(
                "DVD successfully created.  Please hit enter to continue");
    }
  
    public DVDLib getNewDVDInfo() {//Menu 2.
        String title = io.readString("Please enter the DVD Title"); 
        String genre = io.readString("Please enter the Genre");
        String year = io.readString("Please enter the Year");
        String studio = io.readString("Please enter the Studio");
        String directorsName = io.readString("Please enter the Directors name");
        String userRating = io.readString("Please enter a rating 1 - 5 stars");
        String mpaaRating = io.readString("Please enter the MPAA Rating");
        DVDLib currentDVD = new DVDLib(title);
        currentDVD.setGenre(genre);
        currentDVD.setYear(year);
        currentDVD.setStudio(studio);
        currentDVD.setDirectorsName(directorsName);
        currentDVD.setUserRating(userRating);
        currentDVD.setMpaaRating(mpaaRating);
        return currentDVD;
    }

    public DVDLib editDVD(DVDLib dvd){
        if (dvd != null) {
              return getNewDVDInfo();
	    } else {
	        io.print("No such DVD.");
	    }
	    return dvd;
	}
    
   
    public String getDVDTitleChoice() { //Menu 3.
	    return io.readString("Please enter the DVD Title.");
	}
    
      
    public void displayDisplayDVDBanner () { //Menu 3.
	    io.print("=== Display DVD ===");
	}

    
    public void displayDVD(DVDLib dvd) { //Menu 3.
	    if (dvd != null) {
	        io.print(dvd.getTitle());
	        io.print(dvd.getGenre() + " " + dvd.getYear());
	        io.print("");
	    } else {
	        io.print("No such DVD.");
	    }
	    io.readString("Please hit enter to continue.");
	}
    
    public void displayRemoveDVDBanner() { //Menu 4.
        io.print("=== Remove DVD ===");
    }

    public void displayRemoveSuccessBanner() { //Menu 4.
        io.readString("DVD successfully removed. Please hit enter to continue.");
    }
    
     public void displayExitBanner() {
    io.print("Good Bye!!!");
}
    public void displayEditDVDBanner() { 
        io.print("=== Edit DVD ===");
    }
    
    public void displayEditSuccessBanner() { //Menu 4.
        io.readString("DVD successfully Edited. Please hit enter to continue.");
    }
    
    public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}
}



    
