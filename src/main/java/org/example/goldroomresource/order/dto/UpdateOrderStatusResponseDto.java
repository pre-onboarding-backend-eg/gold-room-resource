package org.example.goldroomresource.order.dto;

import org.example.goldroomresource.order.domain.OrderStatus;

public record UpdateOrderStatusResponseDto(String OrderNumber, OrderStatus status, String message) {
}
