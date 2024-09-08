package org.example.goldroomresource.order.dto;

import org.example.goldroomresource.order.domain.GoldType;

import java.math.BigDecimal;

public record OrderItemDto(GoldType goldType,
                           BigDecimal orderItemQuantity,
                           BigDecimal orderItemPrice) {
}
