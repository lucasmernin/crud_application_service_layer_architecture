package com.sg.floormastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author lukem
 */
public class Products {
    
    String productType;
    BigDecimal costPerSqFt;
    BigDecimal laborCostPerSqFtCarpet;

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborCostPerSqFtCarpet() {
        return laborCostPerSqFtCarpet;
    }

    public void setLaborCostPerSqFtCarpet(BigDecimal laborCostPerSqFtCarpet) {
        this.laborCostPerSqFtCarpet = laborCostPerSqFtCarpet;
    }

}
