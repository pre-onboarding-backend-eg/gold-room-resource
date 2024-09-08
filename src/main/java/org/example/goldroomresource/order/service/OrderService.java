package org.example.goldroomresource.order.service;

import lombok.RequiredArgsConstructor;
import org.example.goldroomresource.exception.*;
import org.example.goldroomresource.order.domain.Order;
import org.example.goldroomresource.order.domain.OrderItem;
import org.example.goldroomresource.order.domain.OrderStatus;
import org.example.goldroomresource.order.dto.*;
import org.example.goldroomresource.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final GenerateOrderNumber generateOrderNumber;
    private final ValidateOrder validateOrder;
    private final OrderStatusPatch orderStatusPatch;

    // 1. 주문 생성하기
    @Transactional
    public OrderResponseDto createOrder(OrderDto orderDto) {
        // 1. 전체 입력 유효성 검사
        validateOrder.validateOrderInput(orderDto);
        // 2. OrderItem 총 수량과 총 금액 계산
        BigDecimal totalOrderQuantity = BigDecimal.ZERO; // 초기화
        BigDecimal totalOrderAmount = BigDecimal.ZERO; // 초기화
        for(OrderItemDto itemDto : orderDto.orderItems()){
            totalOrderQuantity = totalOrderQuantity.add(itemDto.orderItemQuantity());
            totalOrderAmount = totalOrderAmount.add(itemDto.orderItemPrice());
        }
        // 3. OrderItemDto 리스트 -> OrderItem 객체 생성
        List<OrderItem> orderItems = orderDto.orderItems().stream()
                .map(itemDto ->OrderItem.builder()
                        .goldType(itemDto.goldType())
                        .orderItemQuantity(itemDto.orderItemQuantity())
                        .orderItemPrice(itemDto.orderItemPrice())
                        .build())
                .collect(Collectors.toList());
        // 4. Order 객체 생성
        Order order = Order.builder()
                .orderNumber(generateOrderNumber.generateOrderNumber(orderDto.goldRoomName())) //주문번호 생성
                .orderDate(LocalDate.now())
                .buyerUserName(orderDto.buyerUserName())
                .sellerUserName(orderDto.sellerUserName())
                .goldRoomName(orderDto.goldRoomName())
                .buyerStatus(OrderStatus.BUY_ORDER_COMPLETED)
                .sellerStatus(OrderStatus.SELL_ORDER_COMPLETED)
                .orderItems(orderItems)
                .totalOrderQuantity(totalOrderQuantity)
                .totalOrderAmount(totalOrderAmount)
                .orderZipCode(orderDto.orderZipCode())
                .orderAddress(orderDto.orderAddress())
                .orderDetailAddress(orderDto.orderDetailAddress())
                .build();
        // 5. Order 저장하고 반환
        orderRepository.save(order);
        // 6. OrderResponse 생성 및 반환
        return new OrderResponseDto("주문이 성공적으로 접수됐습니다.",order);
    }

    // 2. 주문번호로 주문 가져와서 상태 업데이트하기
    /*
    * 1. jwt 토큰에서 userName 정보를 받아오기
    * 2. 주문 테이블의 buyerName이나 sellerName 중 하나와 일치하는지 확인
    * 3. 주문 상태 업데이트 하기
    * */
    @Transactional
    public UpdateOrderStatusResponseDto updateOrderStatus(String orderNumber,UpdateOrderStatusDto updateOrderStatusDto) {
        // 1. 주문번호로 주문 찾기
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(()-> new NotFoundException(ErrorCode.ORDER_NUMBER_RESOURCE_NOT_FOUND));
        // 2. 사용자 확인 후 주문 상태 업데이트
        // 구매자인 경우
        if (updateOrderStatusDto.userName().equals(order.getBuyerUserName())) {
            orderStatusPatch.updateBuyerStatus(order, updateOrderStatusDto.newStatus());
        }
        // 판매자인 경우
        else if (updateOrderStatusDto.userName().equals(order.getSellerUserName())) {
            orderStatusPatch.updateSellerStatus(order, updateOrderStatusDto.newStatus());
        } else {
            throw new ForbiddenException(ErrorCode.USER_NOT_AUTHORIZED_FORBIDDEN);
        }
        // 3. 변경된 주문 저장
        Order updatedOrder = orderRepository.save(order);
        // 4. OrderStatusResponse 생성 및 반환
        return new UpdateOrderStatusResponseDto(updatedOrder.getOrderNumber(),updateOrderStatusDto.newStatus(),"주문 상태가 성공적으로 변경됐습니다.");
    }
    // 3. 사용자별 전체 주문 페이징 처리
    // 4. 주문 삭제 - jwt 토큰 사용자 검증
}
