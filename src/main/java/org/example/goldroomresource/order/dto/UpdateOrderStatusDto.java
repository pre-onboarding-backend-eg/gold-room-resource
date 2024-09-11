package org.example.goldroomresource.order.dto;

import org.example.goldroomresource.order.domain.OrderStatus;

public record UpdateOrderStatusDto(String userName, OrderStatus newStatus) {
}
