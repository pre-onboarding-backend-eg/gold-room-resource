package org.example.goldroomresource.order.dto;

import java.util.List;

public record OrderDto(String sellerUserName, String goldRoomName,
                       String buyerUserName,
                       List<OrderItemDto> orderItems,
                       String orderZipCode,String orderAddress,
                       String orderDetailAddress) {
}
