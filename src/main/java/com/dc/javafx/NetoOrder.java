package com.dc.javafx;

import java.util.List;

public class NetoOrder {

    private String PurchaseOrderNumber;

    private String Username;

    private String Email;

    private String ShipAddressLine1;
    private String ShipAddressLine2;
    private String ShipCity;
    private String ShipState;
    private String ShipCountry;
    private String ShipPostCode;

    private List<NetoOrderLine> orderLines;

    public String getPurchaseOrderNumber() {
        return PurchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        PurchaseOrderNumber = purchaseOrderNumber;
    }

    public List<NetoOrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<NetoOrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "NetoOrder{" +
                "PurchaseOrderNumber='" + PurchaseOrderNumber + '\'' +
                '}';
    }
}
