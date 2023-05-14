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

import ru.namazov.asow.entity.Station;
import ru.namazov.asow.repository.StationRepository;

@ExtendWith(MockitoExtension.class)
class StationServiceTest {

    @Mock
    private StationRepository stationRepository;
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