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

import ru.namazov.asow.dto.CargoDTO;
import ru.namazov.asow.entity.Cargo;
import ru.namazov.asow.mapper.CargoMapper;
import ru.namazov.asow.security.SecurityConfig;
import ru.namazov.asow.service.CargoService;

@WebMvcTest(CargoController.class)
@TestMethodOrder(value = MethodOrderer.MethodName.class)
@Import(SecurityConfig.class)
@WithMockUser(roles = {"PRE_VERIFICATION_USER"})
class CargoControllerTest {

    private @MockBean CargoMapper cargoMapper;
    private @MockBean CargoService cargoService;

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;

    @Test
    void save() throws Exception {
        Cargo cargo = new Cargo();
        cargo.setId(null);
        cargo.setCode(200L);
        cargo.setName("sand");

        Cargo cargoFromDB = new Cargo();
        cargoFromDB.setId(1L);
        cargoFromDB.setCode(200L);
        cargoFromDB.setName("sand");

        CargoDTO cargoDTO = new CargoDTO(null, 200L, "sand");
        CargoDTO expectedCargoDTO = new CargoDTO(1L, 200L, "sand");

        Mockito.when(cargoMapper.toEntity(cargoDTO)).thenReturn(cargo);
        Mockito.when(cargoService.save(Mockito.any())).thenReturn(cargoFromDB);
        Mockito.when(cargoMapper.toDTO(cargoFromDB)).thenReturn(expectedCargoDTO);

        String toSaveJson = mapper.writeValueAsString(cargoDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cargo")
                .contentType(MediaType.APPLICATION_JSON).content(toSaveJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedCargoDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void put() throws Exception {
        Cargo cargo = new Cargo();
        cargo.setId(1L);
        cargo.setCode(200L);
        cargo.setName("sand");

        Cargo cargoFromDB = new Cargo();
        cargoFromDB.setId(1L);
        cargoFromDB.setCode(200L);
        cargoFromDB.setName("sand");

        CargoDTO cargoDTO = new CargoDTO(1L, 200L, "sand");
        CargoDTO expectedCargoDTO = new CargoDTO(1L, 200L, "sand");

        Mockito.when(cargoMapper.toEntity(cargoDTO)).thenReturn(cargo);
        Mockito.when(cargoService.put(Mockito.any())).thenReturn(cargoFromDB);
        Mockito.when(cargoMapper.toDTO(cargoFromDB)).thenReturn(expectedCargoDTO);

        String toSaveJson = mapper.writeValueAsString(cargoDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/cargo")
                .contentType(MediaType.APPLICATION_JSON).content(toSaveJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedCargoDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;

        Cargo cargoFromDB = new Cargo();
        cargoFromDB.setId(1L);
        cargoFromDB.setCode(200L);
        cargoFromDB.setName("sand");
        CargoDTO cargoDTO = new CargoDTO(1L, 200L, "sand");

        Mockito.when(cargoService.findById(id)).thenReturn(cargoFromDB);
        Mockito.when(cargoMapper.toDTO(cargoFromDB)).thenReturn(cargoDTO);

        String expectedJson = mapper.writeValueAsString(cargoDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/cargo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void deleteById() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(cargoService).deleteById(id);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/cargo/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Mockito.verify(cargoService, Mockito.times(1)).deleteById(id);
    }
}