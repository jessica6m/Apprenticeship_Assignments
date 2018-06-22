/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m2_vm;

import java.math.BigDecimal;

/**
 *
 * @author jesse
 */
public class Change {

    Item item;

    private int dollars, quarters, dimes, nickels, pennies;

    
    public void makeChange(Item item, BigDecimal deposit) {

        BigDecimal one = new BigDecimal("1");
        BigDecimal five = new BigDecimal("5");
        BigDecimal ten = new BigDecimal("10");
        BigDecimal twentyFive = new BigDecimal("25");
        BigDecimal oneHundread = new BigDecimal("100");
        
        BigDecimal price = item.getItemPrice();
        
     
        BigDecimal myChange = deposit.subtract(price);

        myChange = myChange.multiply(oneHundread);

        dollars = 0;
        quarters = 0;
        dimes = 0;
        nickels = 0;
        pennies = 0;
        
        while (myChange.floatValue() > 0) {
            //System.out.println(myChange);
            { 
                if (myChange.floatValue() >= 100) {
                  myChange =  myChange.subtract(oneHundread);
                    dollars++;
                } else if (myChange.floatValue() >= 25) {
                    myChange =  myChange.subtract(twentyFive);
                    quarters++;
                } else if (myChange.floatValue() >= 10) {
                    myChange =  myChange.subtract(ten);
                    dimes++;
                } else if (myChange.floatValue() >= 5) {
                    myChange =  myChange.subtract(five);
                    nickels++;
                } else if (myChange.floatValue() >= 1) {
                    myChange =  myChange.subtract(one);
                    pennies++;
                }
             
            }
        }
      

    }

    public int getDollars() {
        return dollars;
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }



}

