package ru.namazov.asow.service;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.entity.WagonPassport;
import ru.namazov.asow.repository.WagonRepository;

class WagonServiceTest {

    private final WagonRepository wagonRepository = Mockito.mock(WagonRepository.class);
    private final WagonService wagonService = new WagonService(wagonRepository);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Wagon wagon = new Wagon();
        wagon.setWagonPassport(new WagonPassport());
        wagon.setPositionNumber(1L);
        wagon.setCargosList(new ArrayList<>());
        wagon.setCargosWeight(200L);
        wagon.setRailway(new Railway());

        Wagon expectedWagon = new Wagon();
        expectedWagon.setWagonPassport(new WagonPassport());
        expectedWagon.setPositionNumber(1L);
        expectedWagon.setCargosList(new ArrayList<>());
        expectedWagon.setCargosWeight(200L);
        expectedWagon.setRailway(new Railway());
        expectedWagon.setId(1L);




        Mockito.when(wagonRepository.save(wagon)).thenReturn(expectedWagon);

        Wagon actualWagon = wagonService.save(wagon);

        Assertions.assertEquals(expectedWagon, actualWagon);
    }

    @Test
    void findById() {
        Long id = 1L;

        Wagon expectedWagon = new Wagon();
        expectedWagon.setWagonPassport(new WagonPassport());
        expectedWagon.setPositionNumber(1L);
        expectedWagon.setCargosList(new ArrayList<>());
        expectedWagon.setCargosWeight(200L);
        expectedWagon.setRailway(new Railway());
        expectedWagon.setId(1L);

        Mockito.when(wagonRepository.findById(id)).thenReturn(Optional.of(expectedWagon));

        Wagon actualWagon = wagonService.findById(id);

        Assertions.assertEquals(expectedWagon, actualWagon);
    }
}