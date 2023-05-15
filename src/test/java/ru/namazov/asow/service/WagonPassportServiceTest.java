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
}