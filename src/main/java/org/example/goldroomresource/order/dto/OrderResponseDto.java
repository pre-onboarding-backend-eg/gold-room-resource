package org.example.goldroomresource.order.dto;

import org.example.goldroomresource.order.domain.Order;

public record OrderResponseDto(String message, Order order) {
}
