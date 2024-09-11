package org.example.goldroomresource.order.service;

import org.example.goldroomresource.exception.BadRequestException;
import org.example.goldroomresource.exception.ErrorCode;
import org.example.goldroomresource.order.domain.GoldType;
import org.example.goldroomresource.order.dto.OrderDto;
import org.example.goldroomresource.order.dto.OrderItemDto;
import org.springframework.stereotype.Component;

@Component
public class ValidateOrder {
    // 주문 상품 리스트 검증
    public void validateOrderItemInput(OrderItemDto orderItemDto) {
       // 주문 상품 리스트 null 확인
        if (orderItemDto == null){
            throw new BadRequestException(ErrorCode.INVALID_ORDER_ITEM_REQUEST_BODY);
        }
        // goldType 검증
        if (orderItemDto.goldType() == null) {
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_GOLD_TYPE);
        }
        // goldType 값이 유효한지 확인
        try {
            GoldType.valueOf(orderItemDto.goldType().name());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_GOLD_TYPE);
        }
        if (orderItemDto.orderItemPrice() == null || orderItemDto.orderItemPrice().doubleValue() <= 0){
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_ITEM_PRICE);
        }
        if (orderItemDto.orderItemQuantity() == null || orderItemDto.orderItemQuantity().doubleValue() <= 0){
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_ITEM_QUANTITY);
        }
    }
    // 주문서 작성 검증
    public void validateOrderInput(OrderDto orderDto) {
        // 주문 상품 리스트 null 확인
        if (orderDto == null){
            throw new BadRequestException(ErrorCode.INVALID_ORDER_REQUEST_BODY);
        }
        if (orderDto.orderItems() == null || orderDto.orderItems().isEmpty()) {
            throw new BadRequestException(ErrorCode.INVALID_ORDER_ITEM_REQUEST_BODY);
        }
        // 각 주문 항목 검증
        for (OrderItemDto itemDto : orderDto.orderItems()) {
            validateOrderItemInput(itemDto);
        }
        if (orderDto.sellerUserName() == null || orderDto.sellerUserName().isEmpty()) {
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_SELLER_NAME);
        }
        if (orderDto.goldRoomName() == null || orderDto.goldRoomName().isEmpty()) {
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_GOLD_ROOM_NAME);
        }
        if (orderDto.buyerUserName() == null || orderDto.buyerUserName().isEmpty()) {
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_BUYER_NAME);
        }
        if (orderDto.orderZipCode() == null || orderDto.orderZipCode().isEmpty()) {
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_ZIP_CODE);
        }
        if (orderDto.orderAddress() == null || orderDto.orderAddress().isEmpty()) {
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_ADDRESS);
        }
        if (orderDto.orderDetailAddress() == null || orderDto.orderDetailAddress().isEmpty()) {
            throw new BadRequestException(ErrorCode.INVALID_PARAMETER_DETAIL_ADDRESS);
        }
    }
}
