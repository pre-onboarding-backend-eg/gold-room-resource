package org.example.goldroomresource.order.repository;

import org.example.goldroomresource.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 주문번호로 찾기
    Optional<Order> findByOrderNumber(String orderNumber);
    // 사용자의 전체 주문목록 가져오기 - paaination
}
