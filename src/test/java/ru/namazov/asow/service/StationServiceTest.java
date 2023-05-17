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
    void put() {
        Station station = new Station();
        station.setName("Bolshego");
        station.setId(1L);

        Station expectedStation = new Station();
        expectedStation.setName("Moskva");
        expectedStation.setId(1L);

        Mockito.when(stationRepository.findById(expectedStation.getId())).thenReturn(Optional.of(station));
        Mockito.when(stationRepository.save(expectedStation)).thenReturn(expectedStation);

        Station actualStation = stationService.put(expectedStation);

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

    @Test
    void deleteById() {
        Long id = 1L;

        Station expectedStation = new Station();
        expectedStation.setName("Bolshego");
        expectedStation.setId(1L);

        Mockito.when(stationRepository.findById(id)).thenReturn(Optional.of(expectedStation));
        Mockito.doNothing().when(stationRepository).deleteById(id);

        stationService.deleteById(id);

        Mockito.verify(stationRepository, Mockito.times(1)).findById(id);
        Mockito.verify(stationRepository, Mockito.times(1)).deleteById(id);
    }
}