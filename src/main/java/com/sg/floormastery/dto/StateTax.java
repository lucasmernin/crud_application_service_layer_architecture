package com.sg.floormastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author lukem
 */
public class StateTax {
    
    String state;
    BigDecimal taxRate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

}
