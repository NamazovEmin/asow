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

import ru.namazov.asow.entity.Cargo;
import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.entity.WagonPassport;
import ru.namazov.asow.repository.WagonRepository;

@ExtendWith(MockitoExtension.class)
class WagonServiceTest {

    @Mock
    private WagonRepository wagonRepository;
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
        wagon.setSerialNumber(1L);
        wagon.setCargo(new Cargo());
        wagon.setCargosWeight(200L);
        wagon.setRailway(new Railway());

        Wagon expectedWagon = new Wagon();
        expectedWagon.setWagonPassport(new WagonPassport());
        expectedWagon.setSerialNumber(1L);
        expectedWagon.setCargo(new Cargo());
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
        expectedWagon.setSerialNumber(1L);
        expectedWagon.setCargo(new Cargo());
        expectedWagon.setCargosWeight(200L);
        expectedWagon.setRailway(new Railway());
        expectedWagon.setId(1L);

        Mockito.when(wagonRepository.findById(id)).thenReturn(Optional.of(expectedWagon));

        Wagon actualWagon = wagonService.findById(id);

        Assertions.assertEquals(expectedWagon, actualWagon);
    }
}