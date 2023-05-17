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

//    private @Autowired WebApplicationContext context;
    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;


    @BeforeEach
    void setUp() {
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
//    @WithUserDetails(value="test", userDetailsServiceBeanName="localUserDetailService")
    void save() throws Exception {
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
        Mockito.when(wagonPassportService.save(wagonPassport)).thenReturn(wagonPassportFromDB);
        Mockito.when(wagonPassportMapper.toDTO(wagonPassportFromDB)).thenReturn(wagonPassportDTO);

        String expectedJson = mapper.writeValueAsString(wagonPassportDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/wagonpassport")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(wagonPassportDTO))).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void put() {
    }

    @Test
    void findById() {
    }

    @Test
    void deleteById() {
    }
}