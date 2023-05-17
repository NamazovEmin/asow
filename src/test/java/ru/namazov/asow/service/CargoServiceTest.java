package ru.namazov.asow.service;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.entity.Cargo;
import ru.namazov.asow.repository.CargoRepository;

class CargoServiceTest {

    private final CargoRepository cargoRepository = Mockito.mock(CargoRepository.class);
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
    void put() {
        Cargo cargo = new Cargo();
        cargo.setCode(10L);
        cargo.setName("sand");
        cargo.setId(1L);

        Cargo expectedCargo = new Cargo();
        expectedCargo.setCode(200L);
        expectedCargo.setName("sand");
        expectedCargo.setId(1L);

        Mockito.when(cargoRepository.findById(expectedCargo.getId())).thenReturn(Optional.of(cargo));
        Mockito.when(cargoRepository.save(expectedCargo)).thenReturn(expectedCargo);

        Cargo actualCargo = cargoService.put(expectedCargo);

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

    @Test
    void deleteById() {
        Long id = 1L;

        Cargo expectedCargo = new Cargo();
        expectedCargo.setCode(200L);
        expectedCargo.setName("sand");
        expectedCargo.setId(1L);
        expectedCargo.setId(1L);

        Mockito.when(cargoRepository.findById(id)).thenReturn(Optional.of(expectedCargo));
        Mockito.doNothing().when(cargoRepository).deleteById(id);

        cargoService.deleteById(id);

        Mockito.verify(cargoRepository, Mockito.times(1)).findById(id);
        Mockito.verify(cargoRepository, Mockito.times(1)).deleteById(id);
    }
}