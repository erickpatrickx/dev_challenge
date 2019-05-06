package br.com.devchallenge.reclamacao.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.devchallenge.reclamacao.ReclamacaoApplicationTests;
import br.com.devchallenge.reclamacao.dtos.LocalidadeDTO;
import br.com.devchallenge.reclamacao.dtos.ReclamacaoDTO;
import br.com.devchallenge.reclamacao.entity.Empresa;
import br.com.devchallenge.reclamacao.entity.Localidade;
import br.com.devchallenge.reclamacao.entity.Reclamacao;
import br.com.devchallenge.reclamacao.service.EmpresaService;
import br.com.devchallenge.reclamacao.service.ReclamacaoService;

/**
 * Teste para controllers de reclamacao
 * 
 * @author erick.oliveira
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReclamacaoControllerTest extends ReclamacaoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	Reclamacao reclamacao = Reclamacao.builder().titulo("Reclamacao 1").descricao("Descrição Reclamação 1")
			.localidade(Localidade.builder().logradouro("Rua A").bairro("Bairro A").cidade("Brasilia").UF("DF")
					.pais("Brasil").build())
			.build();

	Empresa empresa = Empresa.builder().nome("Empresa").cnpj("13.749.831/0001-02").build();

	Reclamacao reclamacao2 = Reclamacao.builder().titulo("Reclamacao 2").descricao("Descrição Reclamação 2")
			.localidade(Localidade.builder().logradouro("Rua B").bairro("Bairro B").cidade("Brasilia").UF("GO")
					.pais("Brasil").build())
			.build();

	@Autowired
	private ReclamacaoService service;

	@Autowired
	private EmpresaService empresaService;

	@Before
	public void setUp() {
		empresa = empresaService.save(empresa);
		reclamacao.setEmpresa(empresa);
		service.save(reclamacao);

		reclamacao2.setEmpresa(empresa);

	}

	@After
	public void setUpBefore() {
		service.deleteAll();
	}

	@Test
	public void giveReclamacao_whenGetReclamacao_thenReturnList() throws Exception {
		mockMvc.perform(get("/reclamacoes").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization",
				getToken("ROLE_ADMIN"))).andExpect(status().isOk()).andExpect(jsonPath("$.*").isNotEmpty())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveReclamacao_whenGetReclamacaoPorLocalidade_thenReturnList() throws Exception {
		Localidade localidade = Localidade.builder().cidade("Brasilia").UF("DF").build();
		mockMvc.perform(get("/reclamacoes/localidade").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(getObjectMapper().writeValueAsString(localidade))
				.header("Authorization", getToken("ROLE_ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$.*").isNotEmpty())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveReclamacao_whenGetReclamacaoQuantidadePorLocalidade_thenReturnList() throws Exception {
		Localidade localidade = Localidade.builder().cidade("Brasilia").UF("DF").build();
		mockMvc.perform(get("/reclamacoes/quantidade/localidade").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.content(getObjectMapper().writeValueAsString(localidade))
				.header("Authorization", getToken("ROLE_ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$").value(1))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveReclamacao_whenGetReclamacaoPorEmpresa_thenReturnList() throws Exception {
		mockMvc.perform(get("/reclamacoes/empresa").param("id", reclamacao.getEmpresa().getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization", getToken("ROLE_ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$.*").isNotEmpty())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveReclamacao_whenGetReclamacaoQuantidadePorEmpresa_thenReturnList() throws Exception {
		mockMvc.perform(get("/reclamacoes/quantidade/empresa").param("id", reclamacao.getEmpresa().getId())
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).header("Authorization", getToken("ROLE_ADMIN")))
				.andExpect(status().isOk()).andExpect(jsonPath("$").value(1))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveReclamacao_whenGetReclamacaoById_thenReturnReclamacao() throws Exception {
		mockMvc.perform(get("/reclamacoes/" + reclamacao.getId()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("Authorization", getToken("ROLE_ADMIN"))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(reclamacao.getId()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveReclamacao_whenPostReclamacao_thenReturnStatusCreate() throws Exception {
		ReclamacaoDTO dto = getModelMapper().map(reclamacao2, ReclamacaoDTO.class);
		mockMvc.perform(post("/reclamacoes/save").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("Authorization", getToken("ROLE_ADMIN")).content(getObjectMapper().writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveReclamacao_whenPostReclamacaoRoleUser_thenReturnStatusCreate() throws Exception {
		ReclamacaoDTO dto = getModelMapper().map(reclamacao2, ReclamacaoDTO.class);
		mockMvc.perform(post("/reclamacoes/save").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("Authorization", getToken("ROLE_USER")).content(getObjectMapper().writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveReclamacao_whenPostReclamacaoFail_NullLocadidade_thenReturnStatusInternalError() throws Exception {
		ReclamacaoDTO dto = getModelMapper().map(reclamacao2, ReclamacaoDTO.class);
		dto.setLocalidade(null);
		mockMvc.perform(post("/reclamacoes/save").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("Authorization", getToken("ROLE_ADMIN")).content(getObjectMapper().writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveReclamacao_whenPostReclamacaoFail_NullEmpresa_thenReturnStatusInternalError() throws Exception {
		ReclamacaoDTO dto = getModelMapper().map(reclamacao2, ReclamacaoDTO.class);
		dto.setEmpresa(null);
		mockMvc.perform(post("/reclamacoes/save").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("Authorization", getToken("ROLE_ADMIN")).content(getObjectMapper().writeValueAsString(dto)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveStore_whenPutReclamacao_thenReturnStatusOk() throws Exception {
		String id = reclamacao.getId();
		reclamacao.setDescricao("Reclamacao 3 - Modify decrição");
		mockMvc.perform(put("/reclamacoes/update/" + id).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("Authorization", getToken("ROLE_ADMIN"))
				.content(getObjectMapper().writeValueAsString(reclamacao))).andExpect(status().isOk())
				.andExpect(jsonPath("$.descricao").value(reclamacao.getDescricao()))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void giveStore_whenDeleteReclamacao_thenReturnStatusOk() throws Exception {
		mockMvc.perform(delete("/reclamacoes/" + reclamacao.getId()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("Authorization", getToken("ROLE_ADMIN"))).andExpect(status().isOk());
	}

	@Test
	public void giveStore_whenDeleteReclamacaoFail_thenReturnStatusInternalError() throws Exception {
		mockMvc.perform(delete("/reclamacoes/" + reclamacao.getId()).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
				.header("Authorization", getToken("ROLE_FAIL"))).andExpect(status().isInternalServerError());
	}

}