package ru.namazov.asow.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.namazov.asow.dto.OperationDTO;
import ru.namazov.asow.dto.WagonDTO;
import ru.namazov.asow.dto.WagonPassportDTO;
import ru.namazov.asow.entity.Operation;
import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.entity.WagonPassport;
import ru.namazov.asow.enums.OperationType;
import ru.namazov.asow.enums.Position;
import ru.namazov.asow.enums.WagonType;
import ru.namazov.asow.facade.OperationFacade;
import ru.namazov.asow.mapper.OperationMapper;
import ru.namazov.asow.mapper.WagonMapper;
import ru.namazov.asow.security.SecurityConfig;
import ru.namazov.asow.service.OperationService;

@WebMvcTest(OperationController.class)
@TestMethodOrder(value = MethodOrderer.MethodName.class)
@Import(SecurityConfig.class)
@WithMockUser(roles = {"PRE_VERIFICATION_USER"})
class OperationControllerTest {

    @MockBean private OperationFacade operationFacade;
    @MockBean private WagonMapper wagonMapper;
    @MockBean private OperationMapper operationMapper;
    @MockBean private OperationService operationService;

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;

    @Test
    void receiveWagons() throws Exception {
        Long railway = 1L;

        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);
        List<Wagon> wagonList = new ArrayList<>();
        Wagon wagon = new Wagon();
        wagon.setId(1L);
        wagon.setWagonPassport(wagonPassport);
        wagon.setPositionNumber(1L);
        wagon.setCargosWeight(200L);
        wagonList.add(wagon);

        List<Operation> operationListFromDB = new ArrayList<>();
        Operation operation = new Operation();
        operation.setId(1L);
        operation.setType(OperationType.RECEIVE);
        operation.setFromRailwayID(2L);
        operation.setWhereRailwayID(1L);
        operation.setWagon("Wagon number....");
        operationListFromDB.add(operation);

        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(1L, 200L, WagonType.BIG,200L, 200L);
        List<WagonDTO> wagonDTOList = List.of(new WagonDTO(1L, wagonPassportDTO, 2L, 200L));
        List<OperationDTO> expectedOperationList = List.of(new OperationDTO(operation.getId(), operation.getType(), operation.getFromRailwayID(), operation.getWhereRailwayID(), operation.getWagon()));

        Mockito.when(wagonMapper.toEntity(wagonDTOList)).thenReturn(wagonList);
        Mockito.when(operationFacade.receive(Mockito.any(),Mockito.any())).thenReturn(operationListFromDB);
        Mockito.when(operationMapper.toDTO(operationListFromDB)).thenReturn(expectedOperationList);

        String toSaveJson = mapper.writeValueAsString(wagonDTOList);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/operation/receive/{railway}", railway)
                .contentType(MediaType.APPLICATION_JSON).content(toSaveJson))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedOperationList);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void moveWagons() throws Exception {
        Long railway = 1L;
        Position position = Position.HEAD;

        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);
        List<Wagon> wagonList = new ArrayList<>();
        Wagon wagon = new Wagon();
        wagon.setId(1L);
        wagon.setWagonPassport(wagonPassport);
        wagon.setPositionNumber(1L);
        wagon.setCargosWeight(200L);
        wagonList.add(wagon);

        List<Operation> operationListFromDB = new ArrayList<>();
        Operation operation = new Operation();
        operation.setId(1L);
        operation.setType(OperationType.RECEIVE);
        operation.setFromRailwayID(2L);
        operation.setWhereRailwayID(1L);
        operation.setWagon("Wagon number....");
        operationListFromDB.add(operation);

        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(1L, 200L, WagonType.BIG,200L, 200L);
        List<WagonDTO> wagonDTOList = List.of(new WagonDTO(1L, wagonPassportDTO, 2L, 200L));
        List<OperationDTO> expectedOperationList = List.of(new OperationDTO(operation.getId(), operation.getType(), operation.getFromRailwayID(), operation.getWhereRailwayID(), operation.getWagon()));

        Mockito.when(wagonMapper.toEntity(wagonDTOList)).thenReturn(wagonList);
        Mockito.when(operationFacade.move(Mockito.any(),Mockito.any(), Mockito.any())).thenReturn(operationListFromDB);
        Mockito.when(operationMapper.toDTO(operationListFromDB)).thenReturn(expectedOperationList);


        String toSaveJson= mapper.writeValueAsString(expectedOperationList);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/operation/move/{railway}", railway)
                        .param("position", String.valueOf(position))
                        .contentType(MediaType.APPLICATION_JSON).content(toSaveJson))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedOperationList);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void returnWagons() throws Exception {
        Long railway = 1L;

        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);
        List<Wagon> wagonList = new ArrayList<>();
        Wagon wagon = new Wagon();
        wagon.setId(1L);
        wagon.setWagonPassport(wagonPassport);
        wagon.setPositionNumber(1L);
        wagon.setCargosWeight(200L);
        wagonList.add(wagon);

        List<Operation> operationListFromDB = new ArrayList<>();
        Operation operation = new Operation();
        operation.setId(1L);
        operation.setType(OperationType.RECEIVE);
        operation.setFromRailwayID(2L);
        operation.setWhereRailwayID(1L);
        operation.setWagon("Wagon number....");
        operationListFromDB.add(operation);

        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(1L, 200L, WagonType.BIG,200L, 200L);
        List<WagonDTO> wagonDTOList = List.of(new WagonDTO(1L, wagonPassportDTO, 2L, 200L));
        List<OperationDTO> expectedOperationList = List.of(new OperationDTO(operation.getId(), operation.getType(), operation.getFromRailwayID(), operation.getWhereRailwayID(), operation.getWagon()));

        Mockito.when(wagonMapper.toEntity(wagonDTOList)).thenReturn(wagonList);
        Mockito.when(operationFacade.bringBack(Mockito.any(),Mockito.any())).thenReturn(operationListFromDB);
        Mockito.when(operationMapper.toDTO(operationListFromDB)).thenReturn(expectedOperationList);

        String toSaveJson = mapper.writeValueAsString(wagonDTOList);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/operation/return/{railway}", railway)
                        .param("railway", Long.toString(railway))
                        .contentType(MediaType.APPLICATION_JSON).content(toSaveJson))
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedOperationList);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;

        Operation operationFromDB = new Operation();
        operationFromDB.setId(1L);
        operationFromDB.setType(OperationType.RECEIVE);
        operationFromDB.setFromRailwayID(2L);
        operationFromDB.setWhereRailwayID(1L);
        operationFromDB.setWagon("Wagon number....");

        OperationDTO expectedOperationDTO = new OperationDTO(operationFromDB.getId(), operationFromDB.getType(), operationFromDB.getFromRailwayID(), operationFromDB.getWhereRailwayID(), operationFromDB.getWagon());

        Mockito.when(operationService.findById(id)).thenReturn(operationFromDB);
        Mockito.when(operationMapper.toDTO(operationFromDB)).thenReturn(expectedOperationDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/operation/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedOperationDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void put() throws Exception {
        Operation operation = new Operation();
        operation.setId(1L);
        operation.setType(OperationType.RECEIVE);
        operation.setFromRailwayID(2L);
        operation.setWhereRailwayID(1L);
        operation.setWagon("Wagon number....");

        Operation operationFromDB = new Operation();
        operationFromDB.setId(1L);
        operationFromDB.setType(OperationType.RECEIVE);
        operationFromDB.setFromRailwayID(2L);
        operationFromDB.setWhereRailwayID(1L);
        operationFromDB.setWagon("Wagon number....");

        OperationDTO operationDTO = new OperationDTO(operation.getId(), operation.getType(), operation.getFromRailwayID(), operation.getWhereRailwayID(), operation.getWagon());

        OperationDTO expectedOperationDTO = new OperationDTO(operationFromDB.getId(), operationFromDB.getType(), operationFromDB.getFromRailwayID(), operationFromDB.getWhereRailwayID(), operationFromDB.getWagon());

        Mockito.when(operationMapper.toEntity(operationDTO)).thenReturn(operation);
        Mockito.when(operationService.put(Mockito.any())).thenReturn(operationFromDB);
        Mockito.when(operationMapper.toDTO(operationFromDB)).thenReturn(expectedOperationDTO);

        String toSaveJson = mapper.writeValueAsString(operationDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/operation")
                .contentType(MediaType.APPLICATION_JSON).content(toSaveJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedOperationDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void deleteById() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(operationService).deleteById(id);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/operation/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Mockito.verify(operationService, Mockito.times(1)).deleteById(id);
    }
}