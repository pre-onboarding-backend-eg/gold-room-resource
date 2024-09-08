package org.example.goldroomresource.order.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.goldroomresource.order.dto.OrderDto;
import org.example.goldroomresource.order.dto.OrderResponseDto;
import org.example.goldroomresource.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name="Order",description = "주문에 관련된 Api입니다.")
public class OrderController {
    private final OrderService orderService;

    // 1. 주문하기
    @Operation(
            summary = "주문하기",
            description = "주문을 생성합니다."
    )
    @ApiResponse(
            responseCode = "201",
            description = "주문에 성공하였습니다."
    )
    @PostMapping("/create")
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderResponseDto orderResponse = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    // 2. 주문목록 가져오기
    // 3. 주문 상태 업데이트
    // 4. 주문 페이지네이션
}
