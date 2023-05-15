package ru.namazov.asow.service;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.entity.Station;
import ru.namazov.asow.repository.StationRepository;

class StationServiceTest {

    private final StationRepository stationRepository = Mockito.mock(StationRepository.class);
    private final StationService stationService = new StationService(stationRepository);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Station station = new Station();
        station.setName("Bolshego");

        Station expectedStation = new Station();
        expectedStation.setName("Bolshego");
        expectedStation.setId(1L);

        Mockito.when(stationRepository.save(station)).thenReturn(expectedStation);

        Station actualStation = stationService.save(station);

        Assertions.assertEquals(expectedStation, actualStation);
    }

    @Test
    void findById() {
        Long id = 1L;

        Station expectedStation = new Station();
        expectedStation.setName("Bolshego");
        expectedStation.setId(1L);

        Mockito.when(stationRepository.findById(id)).thenReturn(Optional.of(expectedStation));

        Station actualStation = stationService.findById(id);

        Assertions.assertEquals(expectedStation, actualStation);
    }
}