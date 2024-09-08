package org.example.goldroomresource.order.domain;

public enum OrderStatus {
    // 구매자 상태 (주문완료, 입금완료, 배송완료)
    ORDER_COMPLETED,PAYMENT_COMPLETED,SHIPPING_COMPLETED,
    // 판매자 상태 (주문완료, 송금완료, 수령완료)
    ORDER_RECEIVED,REMITTANCE_COMPLETED,RECEIPT_COMPLETED
}
