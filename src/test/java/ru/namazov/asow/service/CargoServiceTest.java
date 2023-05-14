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
import ru.namazov.asow.repository.CargoRepository;

@ExtendWith(MockitoExtension.class)
class CargoServiceTest {

    @Mock
    private CargoRepository cargoRepository;
    private final CargoService cargoService = new CargoService(cargoRepository);

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
        Cargo cargo = new Cargo();
        cargo.setCode(200L);
        cargo.setName("sand");

        Cargo expectedCargo = new Cargo();
        expectedCargo.setCode(200L);
        expectedCargo.setName("sand");
        expectedCargo.setId(1L);

        Mockito.when(cargoRepository.save(cargo)).thenReturn(expectedCargo);

        Cargo actualCargo = cargoService.save(cargo);

        Assertions.assertEquals(expectedCargo, actualCargo);
    }

    @Test
    void findById() {
        Long id = 1L;

        Cargo expectedCargo = new Cargo();
        expectedCargo.setCode(200L);
        expectedCargo.setName("sand");
        expectedCargo.setId(1L);

        Mockito.when(cargoRepository.findById(id)).thenReturn(Optional.of(expectedCargo));

        Cargo actualCargo = cargoService.findById(id);

        Assertions.assertEquals(expectedCargo, actualCargo);
    }
}