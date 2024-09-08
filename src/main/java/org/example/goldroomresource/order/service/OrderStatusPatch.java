package org.example.goldroomresource.order.service;

import org.example.goldroomresource.exception.ConflictException;
import org.example.goldroomresource.exception.ErrorCode;
import org.example.goldroomresource.order.domain.Order;
import org.example.goldroomresource.order.domain.OrderStatus;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusPatch {
    // 판매자
    public void updateSellerStatus(Order order, OrderStatus newStatus) {
        if (isValidSellerStatusTransition(order.getSellerStatus(), newStatus)) {
            order.updateSellerStatus(newStatus);
        }else {
            throw new ConflictException(ErrorCode.ORDER_STATUS_PATCH_CONFLICT);
        }
    }
    // 구매자
    public void updateBuyerStatus(Order order, OrderStatus newStatus) {
        if (isValidBuyerStatusTransition(order.getBuyerStatus(), newStatus)) {
            order.updateBuyerStatus(newStatus);
        } else {
            throw new ConflictException(ErrorCode.ORDER_STATUS_PATCH_CONFLICT);
        }
    }
    // 판매자 상태변경
    private boolean isValidSellerStatusTransition(OrderStatus currentStatus,OrderStatus newStatus) {
        switch (currentStatus){
            case SELL_ORDER_COMPLETED:
                return newStatus == OrderStatus.DEPOSIT_COMPLETED; // 입금 완료로 변경
            case DEPOSIT_COMPLETED:
                return newStatus == OrderStatus.SHIPMENT_COMPLETED; // 발송 완료로 변경
            default:
                return false;
        }
    }
    // 구매자 상태변경
    private boolean isValidBuyerStatusTransition(OrderStatus currentStatus,OrderStatus newStatus) {
        switch (currentStatus){
            case BUY_ORDER_COMPLETED:
                return newStatus == OrderStatus.TRANSFER_COMPLETED; //송금 완료로 변경
            case TRANSFER_COMPLETED:
                return newStatus == OrderStatus.RECEIPT_COMPLETED; //수령 완료로 변경
            default:
                return false;
        }
    }

}
