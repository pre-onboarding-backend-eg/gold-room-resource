package org.example.goldroomresource.order.service;

import lombok.RequiredArgsConstructor;
import org.example.goldroomresource.order.domain.Order;
import org.example.goldroomresource.order.domain.OrderItem;
import org.example.goldroomresource.order.domain.OrderStatus;
import org.example.goldroomresource.order.dto.OrderDto;
import org.example.goldroomresource.order.dto.OrderItemDto;
import org.example.goldroomresource.order.dto.OrderResponseDto;
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

    // 1. 주문 생성하기
    @Transactional
    public OrderResponseDto createOrder(OrderDto orderDto) {
        // 1. OrderItem 총 수량과 총 금액 계산
        BigDecimal totalOrderQuantity = BigDecimal.ZERO; // 초기화
        BigDecimal totalOrderAmount = BigDecimal.ZERO; // 초기화
        for(OrderItemDto itemDto : orderDto.orderItems()){
            totalOrderQuantity = totalOrderQuantity.add(itemDto.orderItemQuantity());
            totalOrderAmount = totalOrderAmount.add(itemDto.orderItemPrice());
        }
        // 2. OrderItemDto 리스트 -> OrderItem 객체 생성
        List<OrderItem> orderItems = orderDto.orderItems().stream()
                .map(itemDto ->OrderItem.builder()
                        .goldType(itemDto.goldType())
                        .orderItemQuantity(itemDto.orderItemQuantity())
                        .orderItemPrice(itemDto.orderItemPrice())
                        .build())
                .collect(Collectors.toList());
        // 3. Order 객체 생성
        Order order = Order.builder()
                .orderNumber(generateOrderNumber.generateOrderNumber(orderDto.goldRoomName())) //주문번호 생성
                .orderDate(LocalDate.now())
                .buyerUserName(orderDto.buyerUserName())
                .sellerUserName(orderDto.sellerUserName())
                .goldRoomName(orderDto.goldRoomName())
                .buyerStatus(OrderStatus.ORDER_COMPLETED)
                .sellerStatus(OrderStatus.ORDER_RECEIVED)
                .orderItems(orderItems)
                .totalOrderQuantity(totalOrderQuantity)
                .totalOrderAmount(totalOrderAmount)
                .orderZipCode(orderDto.orderZipCode())
                .orderAddress(orderDto.orderAddress())
                .orderDetailAddress(orderDto.orderDetailAddress())
                .build();
        // 4. Order 저장하고 반환
        orderRepository.save(order);
        // 5. OrderResponse 생성 및 반환
        return new OrderResponseDto("주문이 성공적으로 접수됐습니다.",order);
    }
    // 2. 주문번호로 주문 가져오기
    // 3. 주문상태 업데이트하기
    // 4. 페이징 처리
    // 5. 주문번호 생성기
}
