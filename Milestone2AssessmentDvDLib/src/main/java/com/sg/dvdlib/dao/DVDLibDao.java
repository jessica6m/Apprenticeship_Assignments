/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlib.dao;

import java.util.List;
import com.sg.dvdlib.dto.DVDLib;

/**
 *
 * @author jesse
 */
public interface DVDLibDao {

    DVDLib addDVD(String title, DVDLib dvd)
            throws DVDLibDaoException;

    List<DVDLib> getAllDVDs()
            throws DVDLibDaoException;

    DVDLib getDVD(String title)
            throws DVDLibDaoException;

    DVDLib removeDVD(String title)
            throws DVDLibDaoException;

    DVDLib editDVD(String title, DVDLib dvd)
            throws DVDLibDaoException;
}

