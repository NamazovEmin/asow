package ru.namazov.asow.service;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.entity.Order;
import ru.namazov.asow.repository.TrainRepository;

class OrderServiceTest {

    private final TrainRepository trainRepository = Mockito.mock(TrainRepository.class);
    private final TrainService trainService = new TrainService(trainRepository);

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

        Mockito.when(trainRepository.save(order)).thenReturn(expectedOrder);

        Order actualOrder = trainService.save(order);

        Assertions.assertEquals(expectedOrder, actualOrder);
    }

    @Test
    void findById() {
        Long id = 1L;

        Order expectedOrder = new Order();
        expectedOrder.setWagonList(new ArrayList<>());
        expectedOrder.setId(1L);

        Mockito.when(trainRepository.findById(id)).thenReturn(Optional.of(expectedOrder));

        Order actualOrder = trainService.findById(id);

        Assertions.assertEquals(expectedOrder, actualOrder);
    }
}