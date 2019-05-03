package br.com.devchallenge.empresa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;
import 	org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.devchallenge.empresa.EmpresaApplicationTests;
import br.com.devchallenge.empresa.entity.Empresa;
import br.com.devchallenge.empresa.repository.EmpresaRepository;
import br.com.devchallenge.empresa.service.EmpresaService;


/**
 * Teste para controllers de empresa
 * 
 * @author erick.oliveira
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmpresaControllerTest extends EmpresaApplicationTests{

	@Autowired
	private MockMvc mockMvc;
	
	Empresa empresa = Empresa.builder().nome("Empresa 1").cnpj("35.309.192/0001-05").build();

	Empresa empresa2 = Empresa.builder().nome("Empresa 2").cnpj("73.917.452/0001-06").build();

	Empresa empresa3 = Empresa.builder().nome("Empresa 3").cnpj("69.101.247/0001-09").build();

	
	@Autowired
	private EmpresaService service;
	
	
	@Before
	public void setUp() {
		service.save(empresa2);
		service.save(empresa3);
	}
	
	@After
	public void setUpBefore() {
		service.deleteAll();
	}
	
	
	
	@Test
	public void giveEmpresa_whenGetEmpresa_thenReturnList() throws Exception {
		mockMvc.perform(get("/empresas").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",getToken("ROLE_ADMIN")  )
				.content(getObjectMapper().writeValueAsString(empresa)).header("Content-Type", "application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.*").isNotEmpty())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));	
	}

	@Test
	public void giveEmpresa_whenGetEmpresaById_thenReturnEmpresa() throws Exception {
		mockMvc.perform(get("/empresas/"+empresa2.getId()).contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",getToken("ROLE_ADMIN")  )
				.content(getObjectMapper().writeValueAsString(empresa)).header("Content-Type", "application/json"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(empresa2.getId()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));	
	}

	@Test
	public void giveEmpresa_whenPostEmprea_thenReturnStatusCreate() throws Exception {
		mockMvc.perform(post("/empresas/save").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",getToken("ROLE_ADMIN")  )
				.content(getObjectMapper().writeValueAsString(empresa)).header("Content-Type", "application/json"))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));	
	}
	
	
	@Test
	public void giveEmpresa_whenPostEmpreaFail_thenReturnStatusInternalError() throws Exception {
		empresa.setCnpj(null);
		mockMvc.perform(post("/empresas/save").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization",getToken("ROLE_ADMIN")  )
				.content(getObjectMapper().writeValueAsString(empresa)).header("Content-Type", "application/json"))
				.andExpect(status().isInternalServerError())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));	
	}
	
	
	@Test
	public void giveStore_whenPutEmpresa_thenReturnStatusOk() throws Exception {
		String id = empresa3.getId();
		empresa3.setNome("Empresa 3 - Modify Name");
		mockMvc.perform(put("/empresas/update/" + id)
						.contentType(MediaType.APPLICATION_JSON)
						.header("Authorization",getToken("ROLE_ADMIN")  )
						.content(getObjectMapper().writeValueAsString(empresa3)).header("Content-Type", "application/json"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.nome").value(empresa3.getNome()))
						.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));	
	}
	
	


}