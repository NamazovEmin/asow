package ru.namazov.asow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.namazov.asow.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
