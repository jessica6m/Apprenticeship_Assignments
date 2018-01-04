/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlib.dao;

import com.sg.dvdlib.dto.DVDLib;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jesse
 */
public abstract class DVDLibDaoFileImpl implements DVDLibDao{
    
    private Map<String, DVDLib> dvds = new HashMap<>();
    
    @Override
    public DVDLib addDVD(String title, DVDLib dvd) {
    DVDLib newDVD = dvds.put(title, dvd);
    return newDVD;
	  }
    
    @Override
    public List<DVDLib> getAllDVDs() {
    return new ArrayList<DVDLib>(dvds.values());
	}
    
    @Override
    public DVDLib getDVD(String title) {
    return dvds.get(title);
	}
    
    
    @Override
    public DVDLib removeDVD(String title) {
    DVDLib removedDVD = dvds.remove(title);
    return removedDVD;
	}
    
    
    @Override
    public DVDLib editDVD(String title) {
    DVDLib editedDVD = dvds.get(title);
    return editedDVD;
    }
  
}

	