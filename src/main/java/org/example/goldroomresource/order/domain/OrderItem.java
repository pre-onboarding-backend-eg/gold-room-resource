package org.example.goldroomresource.order.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @Enumerated(EnumType.STRING)
    private GoldType goldType;

    @Column(precision = 10, scale = 2)
    private BigDecimal orderItemQuantity; // 수량

    @Column(precision = 10, scale = 2)
    private BigDecimal orderItemPrice; // 가격
}
