package ru.namazov.asow.service;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Station;
import ru.namazov.asow.repository.RailwayRepository;

class RailwayServiceTest {

    private final RailwayRepository railwayRepository = Mockito.mock(RailwayRepository.class);
    private final RailwayService railwayService = new RailwayService(railwayRepository);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Station station = new Station();
        station.setId(1L);

        Railway railway = new Railway();
        railway.setNumber(1L);
        railway.setStation(station);

        Railway expectedRailway = new Railway();
        expectedRailway.setNumber(1L);
        expectedRailway.setStation(station);
        expectedRailway.setId(1L);

        Mockito.when(railwayRepository.save(railway)).thenReturn(expectedRailway);

        Railway actualRailway = railwayService.save(railway);

        Assertions.assertEquals(expectedRailway, actualRailway);
    }

    @Test
    void put() {
        Station station = new Station();
        station.setId(1L);

        Railway railway = new Railway();
        railway.setNumber(1L);
        railway.setStation(station);

        Railway expectedRailway = new Railway();
        expectedRailway.setNumber(10L);
        expectedRailway.setStation(station);
        expectedRailway.setId(10L);

        Mockito.when(railwayRepository.findById(expectedRailway.getId())).thenReturn(Optional.of(railway));
        Mockito.when(railwayRepository.save(expectedRailway)).thenReturn(expectedRailway);

        Railway actualRailway = railwayService.put(expectedRailway);

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

    @Test
    void deleteById() {
        Long id = 1L;

        Railway expectedRailway = new Railway();
        expectedRailway.setNumber(1L);
        expectedRailway.setStation(new Station());
        expectedRailway.setId(1L);

        Mockito.when(railwayRepository.findById(id)).thenReturn(Optional.of(expectedRailway));
        Mockito.doNothing().when(railwayRepository).deleteById(id);

        railwayService.deleteById(id);

        Mockito.verify(railwayRepository, Mockito.times(1)).findById(id);
        Mockito.verify(railwayRepository, Mockito.times(1)).deleteById(id);
    }
}