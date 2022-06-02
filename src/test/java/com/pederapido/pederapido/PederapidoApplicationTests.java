package com.pederapido.pederapido;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pederapido.pederapido.data.ItemPedidoDTO;
import com.pederapido.pederapido.data.PedidoDTO;
import com.pederapido.pederapido.data.RestauranteDTO;
import com.pederapido.pederapido.service.CardapioService;
import com.pederapido.pederapido.service.RestauranteService;

@SpringBootTest(classes = {TestSecurityConfiguration.class})
@WebAppConfiguration
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PederapidoApplicationTests {
	
	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestauranteService restauranteService;
    
    private HttpHeaders headers;
    
//    @BeforeEach
//    public void init() throws Exception {
//        headers = new HttpHeaders();
//        headers.add("Authorization", "Bearer eyJraWQiOiJQeHI4NUxxcEM0K0dtcjNzTk5qWFAwaXpoRjBcL1BxSUt2U1VoNnJuZ3VkND0iLCJhbGciOiJSUzI1NiJ9.eyfJzdWIiOiI0ZDQxZWE5Ni0yYzVkLTRkYWItYmM2MC02OTM4OGZjYzkwOGYiLCJjb2duaXRvOmdyb3VwcyI6WyJhZG0iXSwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJjdXN0b206dGlwb1VzdWFyaW8iOiI0IiwiaXNzIjoiaHR0cHM6XC9cL2NvZ25pdG8taWRwLnVzLWVhc3QtMS5hbWF6b25hd3MuY29tXC91cy1lYXN0LTFfMHNWZGxyU01rIiwiY29nbml0bzp1c2VybmFtZSI6InRlc3RlYWRtIiwib3JpZ2luX2p0aSI6IjE5ODlkMTZlLWNmYWQtNDcyZi1hNDQ0LTcxZDkyNTExYTg5NiIsImF1ZCI6IjF0cDJ0MjFhdXRjdjdidWtqcjU1MGkzNDExIiwiZXZlbnRfaWQiOiJjZTk0ZDJjZC03MDQ5LTQ3ODQtYWRjNC0yZmViZTQ5NTdkYjQiLCJ0b2tlbl91c2UiOiJpZCIsImF1dGhfdGltZSI6MTY1MTE3ODEwMSwiZXhwIjoxNjUxMTgxNzAxLCJpYXQiOjE2NTExNzgxMDEsImp0aSI6IjhhYzJiNGE4LTg3MTItNDM1Ni1hMThmLTJmNTllZDA3NzlkYSIsImVtYWlsIjoiZmFieWFuYS5lY29sb3ZlQGdtYWlsLmNvbSJ9.oUwvymSO-bomRlBm4mO7i9zlc-8r1KnNjka1M8WJyFPMTCzqeXFno2-ptCrzvRlU85X0-RVysxZxmkYf326Nl6O1Y-1m0Nq8yYFkqo0r7nGKH8XFUumZ6X9nnqAIlLavjUy_86d9cbnfgk1mKsys3EBdzS7GuZhgMqMPfHWASaZxziaT2l2fyC_dngIpT5ByM5xrJO0WFBwSfojjvbarT5B5hsUc7X8kfCNq-aH45Xjtzp5sNrC28oBZaQr8RgTLMUFdiPV8-45l4QXBjbI7Jwu-1uQjusXcprGRcOFDcED6SjSmKaPL3sZMr_CA9bOeo8vaIZrpN1-JEokwtSYddw");
//    }
//    
	@Test
	@Order(1)
	void criarPedido() throws Exception {
		List<ItemPedidoDTO> itens = new ArrayList<ItemPedidoDTO>();
		itens.add(new ItemPedidoDTO(1l, null, null, null, 1));
		PedidoDTO pedido = new PedidoDTO(itens, 1l, null, null, 1l);
		
		mockMvc.perform(post("/pedido")
	            .contentType("application/json")
	            .content(objectMapper.writeValueAsString(pedido)))
	            .andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	void buscaCardapioRestaurante() throws Exception {
		mockMvc.perform(get("/cardapio/1"))
	            .andExpect(status().isOk());
	}
	
	@Test
	@Order(3)
	void buscaCardapioRestauranteNaoExistente() throws Exception {
		mockMvc.perform(get("/cardapio/1000"))
	            .andExpect(status().isNotFound());
	}
	
	@Test
	@Order(4)
	void buscaPedidoEmAberto() throws Exception {
		mockMvc.perform(get("/pedido/emAberto"))
	            .andExpect(status().isOk());
	}
	
	@Test
	@Order(5)
	void buscaPedidoEmPreparacao() throws Exception {
		mockMvc.perform(get("/pedido/emPreparacao"))
	            .andExpect(status().isOk());
	}
	
	@Test
	@Order(6)
	void buscaPedidoContaSolicitada() throws Exception {
		mockMvc.perform(get("/pedido/contaSolicitada"))
	            .andExpect(status().isOk());
	}
	
	@Test
	@Order(7)
	void solicitarConta() throws Exception {
		mockMvc.perform(put("/pedido/solicitarConta?mesaId=1"))
	            .andExpect(status().isOk());
	}
	
	@Test
	@Order(8)
	void atualizarStatusConta() throws Exception {
		mockMvc.perform(put("/pedido?pedidoId=1&statusId=3"))
	            .andExpect(status().isOk());
	}
	
	@Test
	@Order(9)
	void buscaPedidosFinalizadosPorMesa() throws Exception {
		mockMvc.perform(get("/pedido/porMesa?mesaId=1"))
	            .andExpect(status().isOk());
	}
	
	@Test
	@Order(10)
	void buscarRestaurantes() throws Exception {
		mockMvc.perform(get("/restaurante/restaurantes"))
	            .andExpect(status().isOk());
		
		List<RestauranteDTO> restaurantes = restauranteService.getRestaurantes();
		Assertions.assertThat(!restaurantes.isEmpty());
	}
	
	@Test
	@Order(11)
	void buscarRestaurantesDaMesa() throws Exception {
		mockMvc.perform(get("/restaurante/restauranteMesa/1"))
	            .andExpect(status().isOk());
		
		RestauranteDTO restaurante = restauranteService.getRestauranteMesa(1l);
		Assertions.assertThat(restaurante != null);
	}

}
