/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlib;

/**
 *
 * @author jesse
 */
import com.sg.dvdlib.controller.DVDLibController;
import com.sg.dvdlib.dao.DVDLibDao;
import com.sg.dvdlib.dao.DVDLibDaoFileImpl;
import com.sg.dvdlib.ui.DVDLibView;
import com.sg.dvdlib.ui.UserIO;
import com.sg.dvdlib.ui.UserIOConsoleImpl;

/**
 *
 * @author jesse
 */
public class App {
	   
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibView myView = new DVDLibView(myIo);
        DVDLibDao myDao = new DVDLibDaoFileImpl();
        DVDLibController controller
                = new DVDLibController(myDao, myView);
        controller.run();
    }
}
