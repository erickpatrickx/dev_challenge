package br.com.devchallenge.reclamacao.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.devchallenge.reclamacao.dtos.ReclamacaoDTO;
import br.com.devchallenge.reclamacao.entity.Localidade;
import br.com.devchallenge.reclamacao.entity.Reclamacao;
import br.com.devchallenge.reclamacao.exception.BadRequestException;
import br.com.devchallenge.reclamacao.service.ReclamacaoService;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * Controller do microservice de Reclamacao
 * 
 * @author erick.oliveira
 *
 */
@RestController
@RequestMapping("/reclamacoes")
public class ReclamacaoController {

	private final ReclamacaoService reclamacaoService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public ReclamacaoController(ReclamacaoService reclamacaoService) {
		this.reclamacaoService = reclamacaoService;
	}

	/**
	 * Listar todas reclamações
	 * 
	 * @param FilterReclamacao
	 * @return ResponseEntity
	 */
	@GetMapping
	@ApiOperation(notes = "Listar todas as reclamações ", value = "Listar todas as reclamações", response = ResponseEntity.class)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<List<Reclamacao>> getReclamacoes() {
		return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.findReclamacoesAll());
	}

	/**
	 * Consultar quantidade de Reclamacaoes por Localidade
	 * 
	 * @param FilterReclamacao
	 * @return ResponseEntity
	 */
	@GetMapping("/quantidade/localidade")
	@ApiOperation(notes = "Consultar quantidade de reclamações por localidade", value = "Consultar quantidade de reclamações por localidade", response = ResponseEntity.class)
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<Long> getReclamacaoQuantidadePorLocalidade(Localidade localidade) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(reclamacaoService.findQuantidadeReclamacaoPorLocalidade(localidade));
	}

	/**
	 * Consultar Quantidade de Reclamacao por Localidade
	 * 
	 * @param Localidade
	 * @return ResponseEntity
	 */
	@GetMapping("/localidade")
	@ApiOperation(notes = "Consultar reclamações por localidade", value = "Consultar reclamações por localidade", response = ResponseEntity.class)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<?> getReclamacaoPorLocalidade(Localidade localidade) {
		return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.findReclamacaoPorLocalidade(localidade));
	}

	/**
	 * Consultar Quantidade Reclamacao por Empresa
	 * 
	 * @param id empresa
	 * @return ResponseEntity
	 */
	@GetMapping("/empresa")
	@ApiOperation(notes = "Consultar reclamações por empresa", value = "Consultar reclamações por empresa", response = ResponseEntity.class)
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity<List<Reclamacao>> getReclamacaoPorEmpresa(@RequestParam String id) {
		return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.findReclamacaoPorEmpresa(id));
	}

	/**
	 * Consultar Reclamacao por Empresa
	 * 
	 * @param ID
	 * @return ResponseEntity
	 */
	@GetMapping("/quantidade/empresa")
	@ApiOperation(notes = "Quantidade de reclamações por empresa", value = "Quantidade de reclamações por empresa", response = ResponseEntity.class)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<Long> getReclamacaoQuantidadePorEmpresa(@RequestParam String id) {
		return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.findQuantidadeReclamacaoPorEmpresa(id));
	}

	/**
	 * Consultar Reclamacao por parametros
	 * 
	 * @param FilterReclamacao
	 * @return ResponseEntity
	 */
	@GetMapping("/{id}")
	@ApiOperation(notes = "Consultar reclamação por ID", value = "Consultar reclamação por ID", response = ResponseEntity.class)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Reclamacao> getReclamacaoPorID(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.findReclamacaoById(id));
	}

	/**
	 * Metodo responsavel por salvar uma Reclamacao
	 * 
	 * @param Reclamacao
	 * @return ResponseEntity
	 */
	@PostMapping("/save")
	@ApiOperation(notes = "Salvar Reclamação", value = "Salvar Reclamação", response = ResponseEntity.class)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<Reclamacao> save(@Validated @RequestBody ReclamacaoDTO dto) {
		Reclamacao reclamacao = modelMapper.map(dto, Reclamacao.class);
		reclamacao = reclamacaoService.save(reclamacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(reclamacao);
	}

	/**
	 * Metodo responsavel por atualizar uma Reclamacao
	 * 
	 * @param rteclamacao
	 * @return ResponseEntity
	 */
	@PutMapping("/update/{id}")
	@ApiOperation(notes = "Atualizar Reclamação", value = "Atualizar Reclamação", response = ResponseEntity.class)
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<Reclamacao> update(@Validated @RequestBody Reclamacao dto, @PathVariable String id) {
		if (id == null) {
			throw new BadRequestException("Informe o id");
		}
		Reclamacao reclamacao = modelMapper.map(dto, Reclamacao.class);
		reclamacao = reclamacaoService.update(reclamacao, id);
		return ResponseEntity.ok().body(reclamacao);
	}

	/**
	 * Metodo responsavel por deletar uma Reclamacao
	 * 
	 * @param ReclamacaoDTO
	 * @return ResponseEntity
	 */
	@DeleteMapping("{id}")
	@ApiOperation(notes = "Excluir Reclamação", value = "Excluir Reclamação", response = ResponseEntity.class)
	@Secured("ROLE_ADMIN")
	public ResponseEntity<?> update(@PathVariable String id) {
		if (id == null) {
			throw new BadRequestException("Informe o id");
		}
		reclamacaoService.delete(id);
		return ResponseEntity.ok().build();
	}

}
