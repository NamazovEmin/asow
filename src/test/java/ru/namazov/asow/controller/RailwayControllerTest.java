package ru.namazov.asow.controller;

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

import ru.namazov.asow.dto.StationDTO;
import ru.namazov.asow.entity.Station;
import ru.namazov.asow.mapper.StationMapper;
import ru.namazov.asow.security.SecurityConfig;
import ru.namazov.asow.service.StationService;

@WebMvcTest(StationController.class)
@TestMethodOrder(value = MethodOrderer.MethodName.class)
@Import(SecurityConfig.class)
@WithMockUser(roles = {"PRE_VERIFICATION_USER"})
class RailwayControllerTest {

    private @MockBean StationMapper stationMapper;
    private @MockBean StationService stationService;

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;

    @Test
    void save() throws Exception {
        Station station = new Station();
        station.setId(0L);
        station.setName("Bolshego");

        Station stationFromDB = new Station();
        stationFromDB.setId(1L);
        stationFromDB.setName("Bolshego");

        StationDTO stationDTO = new StationDTO(0L, "Bolshego");
        StationDTO expectedStationDTO = new StationDTO(1L, "Bolshego");

        Mockito.when(stationMapper.toEntity(stationDTO)).thenReturn(station);
        Mockito.when(stationService.save(station)).thenReturn(stationFromDB);
        // TODO: 17.05.2023 в toDTO приходит Null
        Mockito.when(stationMapper.toDTO(stationFromDB)).thenReturn(expectedStationDTO);

        String expectedJson = mapper.writeValueAsString(expectedStationDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/station")
                .contentType(MediaType.APPLICATION_JSON).content(expectedJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void put() throws Exception {
        Station station = new Station();
        station.setId(1L);
        station.setName("Bolshego");

        Station stationFromDB = new Station();
        stationFromDB.setId(1L);
        stationFromDB.setName("Bolshego");

        StationDTO stationDTO = new StationDTO(1L, "Bolshego");
        StationDTO expectedStationDTO = new StationDTO(1L, "Bolshego");

        Mockito.when(stationMapper.toEntity(stationDTO)).thenReturn(station);
        Mockito.when(stationService.save(station)).thenReturn(stationFromDB);
        // TODO: 17.05.2023 в toDTO приходит Null
        Mockito.when(stationMapper.toDTO(stationFromDB)).thenReturn(expectedStationDTO);

        String expectedJson = mapper.writeValueAsString(expectedStationDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/station")
                .contentType(MediaType.APPLICATION_JSON).content(expectedJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;

        Station stationFromDB = new Station();
        stationFromDB.setId(1L);
        stationFromDB.setName("Bolshego");
        StationDTO stationDTO = new StationDTO(1L, "Bolshego");

        Mockito.when(stationService.findById(id)).thenReturn(stationFromDB);
        Mockito.when(stationMapper.toDTO(stationFromDB)).thenReturn(stationDTO);

        String expectedJson = mapper.writeValueAsString(stationDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/station/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void deleteById() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(stationService).deleteById(id);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/station/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Mockito.verify(stationService, Mockito.times(1)).deleteById(id);
    }
}