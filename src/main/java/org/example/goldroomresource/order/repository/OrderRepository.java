package org.example.goldroomresource.order.repository;

import org.example.goldroomresource.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
