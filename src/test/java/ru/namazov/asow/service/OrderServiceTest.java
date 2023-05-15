package ru.namazov.asow.service;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.entity.Order;
import ru.namazov.asow.repository.OrderRepository;

class OrderServiceTest {

    private final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
    private final OrderService orderService = new OrderService(orderRepository);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Order order = new Order();
        order.setWagonList(new ArrayList<>());

        Order expectedOrder = new Order();
        expectedOrder.setWagonList(new ArrayList<>());
        expectedOrder.setId(1L);

        Mockito.when(orderRepository.save(order)).thenReturn(expectedOrder);

        Order actualOrder = orderService.save(order);

        Assertions.assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void findById() {
        Long id = 1L;

        Order expectedOrder = new Order();
        expectedOrder.setWagonList(new ArrayList<>());
        expectedOrder.setId(1L);

        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(expectedOrder));

        Order actualOrder = orderService.findById(id);

        Assertions.assertEquals(expectedOrder, actualOrder);
    }
}