/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m2_vm;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author jesse
 */
public class Item {
    private String itemNumber;
    private String itemName;
    private BigDecimal itemPrice;
    private int itemQuantity;

    public Item(String itemNumber) {
        this.itemNumber = itemNumber;
    } 

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.itemNumber);
        hash = 61 * hash + Objects.hashCode(this.itemName);
        hash = 61 * hash + Objects.hashCode(this.itemPrice);
        hash = 61 * hash + this.itemQuantity;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemQuantity != other.itemQuantity) {
            return false;
        }
        if (!Objects.equals(this.itemNumber, other.itemNumber)) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        return true;
    }
    
}
