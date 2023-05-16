package ru.namazov.asow.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ru.namazov.asow.mapper.WagonPassportMapper;
import ru.namazov.asow.service.WagonPassportService;

class WagonPassportControllerTest {

    private final WagonPassportService wagonPassportService = Mockito.mock(WagonPassportService.class);
    private final WagonPassportMapper wagonPassportMapper = Mockito.mock(WagonPassportMapper.class);

    private final WagonPassportController wagonPassportController = new WagonPassportController(wagonPassportMapper, wagonPassportService);

    @Test
    void save() {
//        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(100L, WagonType.BIG, 200L, 1000L);
//        WagonPassport wagonPassport = new WagonPassport();
//        wagonPassport.setNumber(100L);
//        wagonPassport.setWagonType(WagonType.BIG);
//        wagonPassport.setContainerWeight(200L);
//        wagonPassport.setCarryingCapacity(1000L);
//
//        WagonPassport wagonPassportFromDB = new WagonPassport();
//        wagonPassport.setNumber(100L);
//        wagonPassport.setWagonType(WagonType.BIG);
//        wagonPassport.setContainerWeight(200L);
//        wagonPassport.setCarryingCapacity(1000L);
//        wagonPassport.setId(1L);
//
//        Mockito.when(wagonPassportMapper.toEntity(wagonPassportDTO)).thenReturn(wagonPassport);
//        Mockito.when(wagonPassportService.save(wagonPassport)).thenReturn(wagonPassportFromDB);
//        Mockito.when(wagonPassportMapper.toDTO(wagonPassportFromDB)).thenReturn(wagonPassportDTO);
//
//        ResponseEntity<WagonPassportDTO> expectedResponse = ResponseEntity.ok(wagonPassportDTO);
//        ResponseEntity<WagonPassportDTO> actualResponse = wagonPassportController.save(wagonPassportDTO);
//
//        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void put() {
//        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(1L, WagonType.BIG, 2L, 10L);
//        WagonPassport wagonPassportByID = new WagonPassport();
//        wagonPassportByID.setNumber(100L);
//        wagonPassportByID.setWagonType(WagonType.BIG);
//        wagonPassportByID.setContainerWeight(200L);
//        wagonPassportByID.setCarryingCapacity(1000L);
//        wagonPassportByID.setId(1L);
//        Long id = 1L;
//
//        WagonPassport wagonPassport = new WagonPassport();
//        wagonPassport.setNumber(wagonPassportDTO.getNumber());
//        wagonPassport.setWagonType(wagonPassportDTO.getWagonType());
//        wagonPassport.setContainerWeight(wagonPassportDTO.getContainerWeight());
//        wagonPassport.setCarryingCapacity(wagonPassportDTO.getCarryingCapacity());
//        wagonPassport.setId(1L);
//
//        Mockito.when(wagonPassportService.findById(id)).thenReturn(wagonPassportByID);
//        Mockito.when(wagonPassportService.save(wagonPassport)).thenReturn(wagonPassport);
//        Mockito.when(wagonPassportMapper.toDTO(wagonPassport)).thenReturn(wagonPassportDTO);
//
//        ResponseEntity<WagonPassportDTO> expectedResponse = ResponseEntity.ok(wagonPassportDTO);
//        ResponseEntity<WagonPassportDTO> actualResponse = wagonPassportController.put(wagonPassportDTO, id);
//
//        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void findById() {
//        Long id = 1L;
//        WagonPassport wagonPassportByID = new WagonPassport();
//        wagonPassportByID.setNumber(100L);
//        wagonPassportByID.setWagonType(WagonType.BIG);
//        wagonPassportByID.setContainerWeight(200L);
//        wagonPassportByID.setCarryingCapacity(1000L);
//        wagonPassportByID.setId(1L);
//        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(100L, WagonType.BIG, 200L, 1000L);
//
//        Mockito.when(wagonPassportService.findById(id)).thenReturn(wagonPassportByID);
//        Mockito.when(wagonPassportMapper.toDTO(wagonPassportByID)).thenReturn(wagonPassportDTO);
//
//        ResponseEntity<WagonPassportDTO> expectedResponse = ResponseEntity.ok(wagonPassportDTO);
//        ResponseEntity<WagonPassportDTO> actualResponse = wagonPassportController.findById(id);
//
//        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void delete() {
    }
}