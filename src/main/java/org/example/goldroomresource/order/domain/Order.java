package org.example.goldroomresource.order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false, unique = true)
    private String orderNumber; // 주문번호 (주문날짜+금은방이름변환+숫자)

    @Column(nullable = false)
    private LocalDate orderDate; // 주문날짜

    @Column(nullable = false)
    private String buyerUserName; // 주문자 이름

    @Column(nullable = false)
    private String sellerUserName; // 판매자 이름

    @Column(nullable = false)
    private String goldRoomName; // 판매자의 금은방 이름

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus buyerStatus; // 구매자 주문 상태

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus sellerStatus; // 판매자 주문 상태

    //주문품목 리스트 (상품 품목, 수량, 가격)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal totalOrderQuantity; // 총 수량

    @Column(nullable = false,precision = 10, scale = 2)
    private BigDecimal totalOrderAmount; // 총 금액

    @Column(nullable = false)
    private String orderZipCode; // 우편번호

    @Column(nullable = false)
    private String orderAddress; // 주소

    @Column(nullable = false)
    private String orderDetailAddress; // 상세주소
}
