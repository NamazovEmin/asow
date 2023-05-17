package ru.namazov.asow.service;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.entity.WagonPassport;
import ru.namazov.asow.enums.WagonType;
import ru.namazov.asow.repository.WagonPassportRepository;

class WagonPassportServiceTest {

    private final WagonPassportRepository wagonPassportRepository = Mockito.mock(WagonPassportRepository.class);
    private final WagonPassportService wagonPassportService = new WagonPassportService(wagonPassportRepository);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setWagonType(WagonType.BIG);
        wagonPassport.setContainerWeight(200L);
        wagonPassport.setCarryingCapacity(100L);

        WagonPassport expectedWagonPassport = new WagonPassport();
        expectedWagonPassport.setWagonType(WagonType.BIG);
        expectedWagonPassport.setContainerWeight(200L);
        expectedWagonPassport.setCarryingCapacity(100L);

        Mockito.when(wagonPassportRepository.save(wagonPassport)).thenReturn(expectedWagonPassport);

        WagonPassport actualWagonPassport = wagonPassportService.save(wagonPassport);

        Assertions.assertEquals(expectedWagonPassport, actualWagonPassport);
    }

    @Test
    void put() {
        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setWagonType(WagonType.BIG);
        wagonPassport.setContainerWeight(200L);
        wagonPassport.setCarryingCapacity(100L);
        wagonPassport.setId(1L);

        WagonPassport expectedWagonPassport = new WagonPassport();
        expectedWagonPassport.setWagonType(WagonType.BIG);
        expectedWagonPassport.setContainerWeight(2L);
        expectedWagonPassport.setCarryingCapacity(1L);
        expectedWagonPassport.setId(1L);

        Mockito.when(wagonPassportRepository.findById(expectedWagonPassport.getId())).thenReturn(Optional.of(wagonPassport));
        Mockito.when(wagonPassportRepository.save(expectedWagonPassport)).thenReturn(expectedWagonPassport);

        WagonPassport actualWagonPassport = wagonPassportService.put(expectedWagonPassport);

        Assertions.assertEquals(expectedWagonPassport, actualWagonPassport);
    }

    @Test
    void findById() {
        Long id = 1L;

        WagonPassport expectedWagonPassport = new WagonPassport();
        expectedWagonPassport.setWagonType(WagonType.BIG);
        expectedWagonPassport.setContainerWeight(200L);
        expectedWagonPassport.setCarryingCapacity(100L);

        Mockito.when(wagonPassportRepository.findById(id)).thenReturn(Optional.of(expectedWagonPassport));

        WagonPassport actualWagonPassport = wagonPassportService.findById(id);

        Assertions.assertEquals(expectedWagonPassport, actualWagonPassport);
    }

    @Test
    void deleteById() {
        Long id = 1L;

        WagonPassport expectedWagonPassport = new WagonPassport();
        expectedWagonPassport.setWagonType(WagonType.BIG);
        expectedWagonPassport.setContainerWeight(2L);
        expectedWagonPassport.setCarryingCapacity(1L);
        expectedWagonPassport.setId(1L);


        Mockito.when(wagonPassportRepository.findById(id)).thenReturn(Optional.of(expectedWagonPassport));
        Mockito.doNothing().when(wagonPassportRepository).deleteById(id);

        wagonPassportService.deleteById(id);

        Mockito.verify(wagonPassportRepository, Mockito.times(1)).findById(id);
        Mockito.verify(wagonPassportRepository, Mockito.times(1)).deleteById(id);
    }
}