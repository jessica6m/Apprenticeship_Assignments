/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram;

import com.sg.flooringprogram.dao.FlooringProgramPersistenceException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author jesse
 */
public class SettingsReader {
    private Scanner scanner;
    
    public String readSettingsFile() throws FlooringProgramPersistenceException {
        try {
	        // Create Scanner for reading the file
	        scanner = new Scanner(
	                new BufferedReader(
	                        new FileReader("settings.txt")));
	    } catch (FileNotFoundException e) {
	        throw new FlooringProgramPersistenceException(
	                "-_- Could not load product data into memory.", e);
	    }
        
        String currentLine = "";
        
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
        }
        
        scanner.close();
        
        return currentLine;
        
    }
	    
	    
}
