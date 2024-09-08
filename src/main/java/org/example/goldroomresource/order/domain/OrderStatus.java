package org.example.goldroomresource.order.domain;

public enum OrderStatus {
    BUY_ORDER_COMPLETED("주문 완료"),
    DEPOSIT_COMPLETED("입금 완료"),
    SHIPMENT_COMPLETED("발송 완료"),
    SELL_ORDER_COMPLETED("주문 완료"),
    TRANSFER_COMPLETED("송금 완료"),
    RECEIPT_COMPLETED("수령 완료");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
