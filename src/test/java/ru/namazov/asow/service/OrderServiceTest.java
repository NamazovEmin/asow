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
        order.setCode(100L);
        order.setId(1L);

        Order expectedOrder = new Order();
        order.setCode(100L);
        expectedOrder.setId(1L);

        Mockito.when(orderRepository.save(order)).thenReturn(expectedOrder);

        Order actualOrder = orderService.save(order);

        Assertions.assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void put() {
        Order order = new Order();
        order.setCode(100L);
        order.setId(1L);

        Order expectedOrder = new Order();
        order.setCode(1L);
        expectedOrder.setId(1L);

        Mockito.when(orderRepository.findById(expectedOrder.getId())).thenReturn(Optional.of(order));
        Mockito.when(orderRepository.save(expectedOrder)).thenReturn(expectedOrder);

        Order actualOrder = orderService.put(expectedOrder);

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

    @Test
    void deleteById() {
        Long id = 1L;

        Order expectedOrder = new Order();
        expectedOrder.setWagonList(new ArrayList<>());
        expectedOrder.setId(1L);

        Mockito.when(orderRepository.findById(id)).thenReturn(Optional.of(expectedOrder));
        Mockito.doNothing().when(orderRepository).deleteById(id);

        orderService.deleteById(id);

        Mockito.verify(orderRepository, Mockito.times(1)).findById(id);
        Mockito.verify(orderRepository, Mockito.times(1)).deleteById(id);
    }
}