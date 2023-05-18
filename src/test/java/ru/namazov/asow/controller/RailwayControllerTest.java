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

import ru.namazov.asow.dto.RailwayDTO;
import ru.namazov.asow.dto.StationDTO;
import ru.namazov.asow.entity.Railway;
import ru.namazov.asow.entity.Station;
import ru.namazov.asow.mapper.RailwayMapper;
import ru.namazov.asow.security.SecurityConfig;
import ru.namazov.asow.service.RailwayService;

@WebMvcTest(RailwayController.class)
@TestMethodOrder(value = MethodOrderer.MethodName.class)
@Import(SecurityConfig.class)
@WithMockUser(roles = {"PRE_VERIFICATION_USER"})
class RailwayControllerTest {

    private @MockBean RailwayMapper railwayMapper;
    private @MockBean RailwayService railwayService;

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;

    @Test
    void save() throws Exception {
        Station station = new Station();
        station.setId(1L);
        Railway railway = new Railway();
        railway.setId(0L);
        railway.setNumber(200L);
        railway.setStation(station);

        Railway railwayFromDB = new Railway();
        railwayFromDB.setId(1L);
        railwayFromDB.setNumber(200L);
        railwayFromDB.setStation(station);

        StationDTO stationDTO = new StationDTO(1L, "bolshego");
        RailwayDTO RailwayDTO = new RailwayDTO(0L, 200L, stationDTO);
        RailwayDTO expectedRailwayDTO = new RailwayDTO(1L, 200L, stationDTO);

        Mockito.when(railwayMapper.toEntity(RailwayDTO)).thenReturn(railway);
        Mockito.when(railwayService.save(railway)).thenReturn(railwayFromDB);
        // TODO: 17.05.2023 в toDTO приходит Null
        Mockito.when(railwayMapper.toDTO(railwayFromDB)).thenReturn(expectedRailwayDTO);

        String expectedJson = mapper.writeValueAsString(expectedRailwayDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/railway")
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
        Railway railway = new Railway();
        railway.setId(1L);
        railway.setNumber(200L);
        railway.setStation(station);

        Railway railwayFromDB = new Railway();
        railwayFromDB.setId(1L);
        railwayFromDB.setNumber(200L);
        railwayFromDB.setStation(station);

        StationDTO stationDTO = new StationDTO(1L, "bolshego");
        RailwayDTO RailwayDTO = new RailwayDTO(1L, 200L, stationDTO);
        RailwayDTO expectedRailwayDTO = new RailwayDTO(1L, 200L, stationDTO);

        Mockito.when(railwayMapper.toEntity(RailwayDTO)).thenReturn(railway);
        Mockito.when(railwayService.save(railway)).thenReturn(railwayFromDB);
        // TODO: 17.05.2023 в toDTO приходит Null
        Mockito.when(railwayMapper.toDTO(railwayFromDB)).thenReturn(expectedRailwayDTO);

        String expectedJson = mapper.writeValueAsString(expectedRailwayDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/railway")
                .contentType(MediaType.APPLICATION_JSON).content(expectedJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;

        Station station = new Station();
        station.setId(1L);
        station.setName("bolshego");
        Railway railwayFromDB = new Railway();
        railwayFromDB.setId(1L);
        railwayFromDB.setNumber(200L);
        railwayFromDB.setStation(station);

        StationDTO stationDTO = new StationDTO(1L, "bolshego");
        RailwayDTO expectedRailwayDTO = new RailwayDTO(1L, 200L, stationDTO);

        Mockito.when(railwayService.findById(id)).thenReturn(railwayFromDB);
        Mockito.when(railwayMapper.toDTO(railwayFromDB)).thenReturn(expectedRailwayDTO);

        String expectedJson = mapper.writeValueAsString(expectedRailwayDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/railway/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void deleteById() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(railwayService).deleteById(id);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/railway/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Mockito.verify(railwayService, Mockito.times(1)).deleteById(id);
    }
}