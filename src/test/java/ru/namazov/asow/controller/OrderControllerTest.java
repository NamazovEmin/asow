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

import ru.namazov.asow.dto.OrderDTO;
import ru.namazov.asow.entity.Order;
import ru.namazov.asow.mapper.OrderMapper;
import ru.namazov.asow.security.SecurityConfig;
import ru.namazov.asow.service.OrderService;
@WebMvcTest(OrderController.class)
@TestMethodOrder(value = MethodOrderer.MethodName.class)
@Import(SecurityConfig.class)
@WithMockUser(roles = {"PRE_VERIFICATION_USER"})
class OrderControllerTest {

    private @MockBean OrderMapper orderMapper;
    private @MockBean OrderService orderService;

    private @Autowired MockMvc mockMvc;
    private @Autowired ObjectMapper mapper;

    @Test
    void save() throws Exception {
        Order order = new Order();
        order.setId(null);
        order.setCode(200L);

        Order orderFromDB = new Order();
        orderFromDB.setId(1L);
        orderFromDB.setCode(200L);

        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getCode());
        OrderDTO expectedOrderDTO = new OrderDTO(orderFromDB.getId(), orderFromDB.getCode());

        Mockito.when(orderMapper.toEntity(orderDTO)).thenReturn(order);
        Mockito.when(orderService.save(Mockito.any())).thenReturn(orderFromDB);
        Mockito.when(orderMapper.toDTO(orderFromDB)).thenReturn(expectedOrderDTO);

        String toSaveJson = mapper.writeValueAsString(orderDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/order")
                .contentType(MediaType.APPLICATION_JSON).content(toSaveJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedOrderDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void put() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setCode(200L);

        Order orderFromDB = new Order();
        orderFromDB.setId(1L);
        orderFromDB.setCode(200L);

        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getCode());
        OrderDTO expectedOrderDTO = new OrderDTO(orderFromDB.getId(), orderFromDB.getCode());

        Mockito.when(orderMapper.toEntity(orderDTO)).thenReturn(order);
        Mockito.when(orderService.put(Mockito.any())).thenReturn(orderFromDB);
        Mockito.when(orderMapper.toDTO(orderFromDB)).thenReturn(expectedOrderDTO);

        String toSaveJson = mapper.writeValueAsString(orderDTO);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/order")
                .contentType(MediaType.APPLICATION_JSON).content(toSaveJson)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedOrderDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void findById() throws Exception {
        Long id = 1L;

        Order orderFromDB = new Order();
        orderFromDB.setId(1L);
        orderFromDB.setCode(200L);

        OrderDTO expectedOrderDTO = new OrderDTO(orderFromDB.getId(), orderFromDB.getCode());

        Mockito.when(orderService.findById(id)).thenReturn(orderFromDB);
        Mockito.when(orderMapper.toDTO(orderFromDB)).thenReturn(expectedOrderDTO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/order/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String expectedJson = mapper.writeValueAsString(expectedOrderDTO);
        String actualJson = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals(expectedJson, actualJson);
    }

    @Test
    void deleteById() throws Exception {
        Long id = 1L;

        Mockito.doNothing().when(orderService).deleteById(id);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/order/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Mockito.verify(orderService, Mockito.times(1)).deleteById(id);
    }
}