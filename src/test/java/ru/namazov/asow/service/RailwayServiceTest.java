package ru.namazov.asow.service;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Station;
import ru.namazov.asow.repository.RailwayRepository;

@ExtendWith(MockitoExtension.class)
class RailwayServiceTest {

    @Mock
    private RailwayRepository railwayRepository;
    private final RailwayService railwayService = new RailwayService(railwayRepository);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Railway railway = new Railway();
        railway.setNumber(1L);
        railway.setStation(new Station());

        Railway expectedRailway = new Railway();
        expectedRailway.setNumber(1L);
        expectedRailway.setStation(new Station());
        expectedRailway.setId(1L);

        Mockito.when(railwayRepository.save(railway)).thenReturn(expectedRailway);

        Railway actualRailway = railwayService.save(railway);

        Assertions.assertEquals(expectedRailway, actualRailway);
    }

    @Test
    void findById() {
        Long id = 1L;

        Railway expectedRailway = new Railway();
        expectedRailway.setNumber(1L);
        expectedRailway.setStation(new Station());
        expectedRailway.setId(1L);

        Mockito.when(railwayRepository.findById(id)).thenReturn(Optional.of(expectedRailway));

        Railway actualRailway = railwayService.findById(id);

        Assertions.assertEquals(expectedRailway, actualRailway);
    }
}