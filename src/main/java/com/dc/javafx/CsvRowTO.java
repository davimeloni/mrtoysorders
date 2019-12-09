package com.dc.javafx;

public class CsvRowTO {

    private String branch;
    private String sku;
    private String quantity;
    private String poNumber;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    @Override
    public String toString() {
        return "CsvRowTO{" +
                "branch='" + branch + '\'' +
                ", sku='" + sku + '\'' +
                ", quantity='" + quantity + '\'' +
                ", poNumber='" + poNumber + '\'' +
                '}';
    }
}
