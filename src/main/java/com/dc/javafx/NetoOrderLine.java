package com.dc.javafx;

public class NetoOrderLine {

    private String SKU;
    private int Quantity;

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    @Override
    public String toString() {
        return "NetoOrderLine{" +
                "SKU='" + SKU + '\'' +
                ", Quantity=" + Quantity +
                '}';
    }
}
