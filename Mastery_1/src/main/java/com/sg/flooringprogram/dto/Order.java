/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringprogram.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author jesse
 */
public class Order {
    private int orderNumber;
    private String customerName;
    private BigDecimal area, materialCost, laborCost, tax, total;
    private LocalDate date;
    private StateTax stateTax;
    private Product product;
    
    
    
    public Order(String customerName, StateTax stateTax, Product product, BigDecimal area, LocalDate date) {
        //this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.area = area;
        this.stateTax = stateTax;
        this.product = product;
        this.date = date; 
        // Material Cost = Product Cost per sq ft Multiplied by Area 
        this.materialCost = this.product.getCostPerSqFt().multiply(area).setScale(2, RoundingMode.HALF_UP);
        // Labor Cost = Product Cost per sq ft Multiplied by Area 
        this.laborCost = this.product.getLaborCostPerSqFt().multiply(area);
        // Tax = State Tax Rate Divided by 100 Multiplied by (Material Cost + Labor Cost)
        this.tax = this.stateTax.getTaxRate().divide(new BigDecimal(100)).multiply(this.materialCost.add(this.laborCost)).setScale(2, RoundingMode.HALF_UP);
        // Total = Material Cost + Labor Cost + Tax
        this.total = this.materialCost.add(this.laborCost).add(this.tax);
        
        
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost = this.product.getCostPerSqFt().multiply(area).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getLaborCost() {
        return laborCost = this.product.getLaborCostPerSqFt().multiply(area);
    }

    public BigDecimal getTax() {
        return tax = this.stateTax.getTaxRate().divide(new BigDecimal(100)).multiply(this.materialCost.add(this.laborCost)).setScale(2, RoundingMode.HALF_UP);
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        this.getMaterialCost();
        this.getLaborCost();
        this.getTax();
        return total = this.materialCost.add(this.laborCost).add(this.tax);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public StateTax getStateTax() {
        return stateTax;
    }

    public void setStateTax(StateTax stateTax) {
        this.stateTax = stateTax;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.orderNumber;
        hash = 53 * hash + Objects.hashCode(this.customerName);
        hash = 53 * hash + Objects.hashCode(this.area);
        hash = 53 * hash + Objects.hashCode(this.materialCost);
        hash = 53 * hash + Objects.hashCode(this.laborCost);
        hash = 53 * hash + Objects.hashCode(this.tax);
        hash = 53 * hash + Objects.hashCode(this.total);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.stateTax);
        hash = 53 * hash + Objects.hashCode(this.product);
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
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.stateTax, other.stateTax)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }
    
    @Override
       public String toString() {
    return "|Order NUmber: " + orderNumber + " |Customer Name: " + customerName + " |State: "
            + this.stateTax.getStateName() + " |Material: " + this.product.getProductType()
            + " |Area: " + this.area + " |Area: " + this.area + " |Date: " + this.date + " |Total: "
            + this.total;
          
   }
}
