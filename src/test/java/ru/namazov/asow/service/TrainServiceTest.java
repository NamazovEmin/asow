package ru.namazov.asow.service;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.entity.Train;
import ru.namazov.asow.repository.TrainRepository;

class TrainServiceTest {

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
        Train train = new Train();
        train.setWagonList(new ArrayList<>());

        Train expectedTrain = new Train();
        expectedTrain.setWagonList(new ArrayList<>());
        expectedTrain.setId(1L);

        Mockito.when(trainRepository.save(train)).thenReturn(expectedTrain);

        Train actualTrain = trainService.save(train);

        Assertions.assertEquals(expectedTrain, actualTrain);
    }

    @Test
    void findById() {
        Long id = 1L;

        Train expectedTrain = new Train();
        expectedTrain.setWagonList(new ArrayList<>());
        expectedTrain.setId(1L);

        Mockito.when(trainRepository.findById(id)).thenReturn(Optional.of(expectedTrain));

        Train actualTrain = trainService.findById(id);

        Assertions.assertEquals(expectedTrain, actualTrain);
    }
}