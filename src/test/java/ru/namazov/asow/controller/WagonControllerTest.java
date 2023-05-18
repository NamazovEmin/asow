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

import ru.namazov.asow.dto.WagonDTO;
import ru.namazov.asow.dto.WagonPassportDTO;
import ru.namazov.asow.entity.Wagon;
import ru.namazov.asow.entity.WagonPassport;
import ru.namazov.asow.enums.WagonType;
import ru.namazov.asow.mapper.WagonMapper;
import ru.namazov.asow.security.SecurityConfig;
import ru.namazov.asow.service.WagonService;

@WebMvcTest(WagonController.class)
@TestMethodOrder(value = MethodOrderer.MethodName.class)
@Import(SecurityConfig.class)
@WithMockUser(roles = {"PRE_VERIFICATION_USER"})
class WagonControllerTest {

    private @MockBean WagonMapper wagonMapper;
    private @MockBean WagonService wagonService;

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;

    @Test
    void save() throws Exception {
        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);

        Wagon wagon = new Wagon();
        wagon.setId(null);
        wagon.setWagonPassport(wagonPassport);
        wagon.setPositionNumber(2L);
        wagon.setCargosWeight(200L);

        Wagon wagonFromDB = new Wagon();
        wagon.setId(1L);
        wagon.setWagonPassport(wagonPassport);
        wagon.setPositionNumber(2L);
        wagon.setCargosWeight(200L);

        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(1L, 200L, WagonType.BIG,200L, 200L);
        WagonDTO wagonDTO = new WagonDTO(null, wagonPassportDTO, 2L, 200L);
        WagonDTO expectedWagon =  new WagonDTO(1L, wagonPassportDTO, 2L, 200L);

        Mockito.when(wagonMapper.toEntity(wagonDTO)).thenReturn(wagon);
        Mockito.when(wagonService.save(Mockito.any())).thenReturn(wagonFromDB);
        Mockito.when(wagonMapper.toDTO(wagonFromDB)).thenReturn(expectedWagon);

        String toSaveJson = mapper.writeValueAsString(wagonDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/wagon")
                .contentType(MediaType.APPLICATION_JSON).content(toSaveJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedWagon);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void put() throws Exception {
        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);

        Wagon wagon = new Wagon();
        wagon.setId(1L);
        wagon.setWagonPassport(wagonPassport);
        wagon.setPositionNumber(2L);
        wagon.setCargosWeight(200L);

        Wagon wagonFromDB = new Wagon();
        wagon.setId(1L);
        wagon.setWagonPassport(wagonPassport);
        wagon.setPositionNumber(2L);
        wagon.setCargosWeight(200L);

        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(1L, 200L, WagonType.BIG,200L, 200L);
        WagonDTO wagonDTO = new WagonDTO(1L, wagonPassportDTO, 2L, 200L);
        WagonDTO expectedWagon =  new WagonDTO(1L, wagonPassportDTO, 2L, 200L);

        Mockito.when(wagonMapper.toEntity(wagonDTO)).thenReturn(wagon);
        Mockito.when(wagonService.put(Mockito.any())).thenReturn(wagonFromDB);
        Mockito.when(wagonMapper.toDTO(wagonFromDB)).thenReturn(expectedWagon);

        String toSaveJson = mapper.writeValueAsString(wagonDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/wagon")
                .contentType(MediaType.APPLICATION_JSON).content(toSaveJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedWagon);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;
        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);

        Wagon wagonFromDB = new Wagon();
        wagonFromDB.setId(1L);
        wagonFromDB.setWagonPassport(wagonPassport);
        wagonFromDB.setPositionNumber(2L);
        wagonFromDB.setCargosWeight(200L);

        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(1L, 200L, WagonType.BIG,200L, 200L);
        WagonDTO expectedWagon =  new WagonDTO(1L, wagonPassportDTO, 2L, 200L);

        Mockito.when(wagonService.findById(id)).thenReturn(wagonFromDB);
        Mockito.when(wagonMapper.toDTO(wagonFromDB)).thenReturn(expectedWagon);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/wagon/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedWagon);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void deleteById() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(wagonService).deleteById(id);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/wagon/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Mockito.verify(wagonService, Mockito.times(1)).deleteById(id);
    }
}