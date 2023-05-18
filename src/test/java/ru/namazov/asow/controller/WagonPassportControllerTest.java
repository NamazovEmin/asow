package ru.namazov.asow.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

import ru.namazov.asow.dto.WagonPassportDTO;
import ru.namazov.asow.entity.WagonPassport;
import ru.namazov.asow.enums.WagonType;
import ru.namazov.asow.mapper.WagonPassportMapper;
import ru.namazov.asow.security.SecurityConfig;
import ru.namazov.asow.service.WagonPassportService;

@WebMvcTest(WagonPassportController.class)
@TestMethodOrder(value = MethodOrderer.MethodName.class)
@Import(SecurityConfig.class)
@WithMockUser(roles = {"PRE_VERIFICATION_USER"})
class WagonPassportControllerTest {

    private @MockBean WagonPassportMapper wagonPassportMapper;
    private @MockBean WagonPassportService wagonPassportService;

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() throws Exception {
        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(null);
        wagonPassport.setWagonType(WagonType.BIG);
        wagonPassport.setCarryingCapacity(200L);
        wagonPassport.setSerialNumber(200L);

        WagonPassport wagonPassportFromDB = new WagonPassport();
        wagonPassportFromDB.setId(1L);
        wagonPassportFromDB.setWagonType(WagonType.BIG);
        wagonPassportFromDB.setCarryingCapacity(200L);
        wagonPassportFromDB.setSerialNumber(200L);
        wagonPassportFromDB.setContainerWeight(200L);
        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(null, 200L, WagonType.BIG,200L, 200L);
        WagonPassportDTO expectedWagonPassportDTO = new WagonPassportDTO(1L, 200L, WagonType.BIG,200L, 200L);

        Mockito.when(wagonPassportMapper.toEntity(wagonPassportDTO)).thenReturn(wagonPassport);
        Mockito.when(wagonPassportService.save(Mockito.any())).thenReturn(wagonPassportFromDB);
        Mockito.when(wagonPassportMapper.toDTO(wagonPassportFromDB)).thenReturn(expectedWagonPassportDTO);

        String toSaveJson = mapper.writeValueAsString(wagonPassportDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/wagonpassport")
                .contentType(MediaType.APPLICATION_JSON).content(toSaveJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedWagonPassportDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void put() throws Exception {
        WagonPassport wagonPassport = new WagonPassport();
        wagonPassport.setId(1L);
        wagonPassport.setWagonType(WagonType.BIG);
        wagonPassport.setCarryingCapacity(200L);
        wagonPassport.setSerialNumber(200L);

        WagonPassport wagonPassportFromDB = new WagonPassport();
        wagonPassportFromDB.setId(1L);
        wagonPassportFromDB.setWagonType(WagonType.BIG);
        wagonPassportFromDB.setCarryingCapacity(200L);
        wagonPassportFromDB.setSerialNumber(200L);
        wagonPassportFromDB.setContainerWeight(200L);
        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(1L, 200L, WagonType.BIG,200L, 200L);

        Mockito.when(wagonPassportMapper.toEntity(wagonPassportDTO)).thenReturn(wagonPassport);
        Mockito.when(wagonPassportService.put(Mockito.any())).thenReturn(wagonPassportFromDB);
        Mockito.when(wagonPassportMapper.toDTO(wagonPassportFromDB)).thenReturn(wagonPassportDTO);

        String toPutJson = mapper.writeValueAsString(wagonPassportDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/wagonpassport")
                .contentType(MediaType.APPLICATION_JSON).content(toPutJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(wagonPassportDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;

        WagonPassport wagonPassportFromDB = new WagonPassport();
        wagonPassportFromDB.setId(1L);
        wagonPassportFromDB.setWagonType(WagonType.BIG);
        wagonPassportFromDB.setCarryingCapacity(200L);
        wagonPassportFromDB.setSerialNumber(200L);
        wagonPassportFromDB.setContainerWeight(200L);
        WagonPassportDTO wagonPassportDTO = new WagonPassportDTO(1L, 200L, WagonType.BIG,200L, 200L);

        Mockito.when(wagonPassportService.findById(id)).thenReturn(wagonPassportFromDB);
        Mockito.when(wagonPassportMapper.toDTO(wagonPassportFromDB)).thenReturn(wagonPassportDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/wagonpassport/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(wagonPassportDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void deleteById() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(wagonPassportService).deleteById(id);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/wagonpassport/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Mockito.verify(wagonPassportService, Mockito.times(1)).deleteById(id);

    }
}