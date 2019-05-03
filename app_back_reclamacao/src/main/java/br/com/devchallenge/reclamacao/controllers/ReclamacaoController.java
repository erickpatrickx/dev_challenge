package br.com.devchallenge.reclamacao.controllers;

import java.util.List;

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
	@ApiOperation(notes = "Recuperar  Reclamacoes ", value = "", response = ResponseEntity.class)
    @Secured({"ROLE_ADMIN","ROLE_USER"})
	public ResponseEntity<List<Reclamacao>> getReclamacoes(Localidade localidade) {
		return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.findReclamacoesAll());
	}
	
	/**
	 * Consultar Reclamacao por Localidade
	 * 
	 * @param FilterReclamacao
	 * @return ResponseEntity
	 */
	@GetMapping("/localidade")
	@ApiOperation(notes = "Recuperar Reclamacoes por Localidade", value = "Filter", response = ResponseEntity.class)
    @Secured({"ROLE_ADMIN"})
	public ResponseEntity<List<Reclamacao>> getReclamacaoPorLocalidade(Localidade localidade) {
		return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.findReclamacao(localidade));
	}
	
	/**
	 * Consultar Reclamacao por Empresa
	 * 
	 * @param FilterReclamacao
	 * @return ResponseEntity
	 */
	@GetMapping("/empresa")
	@ApiOperation(notes = "Recuperar Reclamacoes por Empresa", value = "Filter", response = ResponseEntity.class)
    @Secured({"ROLE_ADMIN"})
	public ResponseEntity<List<Reclamacao>> getReclamacaoPorEmpresa(@RequestParam String cnpj) {
		return ResponseEntity.status(HttpStatus.OK).body(reclamacaoService.findReclamacaoPorEmpresa(cnpj));
	}
	

	/**
	 * Consultar Reclamacao por parametros
	 * 
	 * @param FilterReclamacao
	 * @return ResponseEntity
	 */
	@GetMapping("/{id}")
	@ApiOperation(notes = "Recuperar uma Reclamacao por id", value = "Filter", response = ResponseEntity.class)
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
	@ApiOperation(notes = "Salvar os dados de uma Reclamacao", value = "Reclamacao", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<Reclamacao> save(@Validated @RequestBody Reclamacao Reclamacao) {
		Reclamacao = reclamacaoService.save(Reclamacao);
		return ResponseEntity.status(HttpStatus.CREATED).body(Reclamacao);
	}

	/**
	 * Metodo responsavel por atualizar uma Reclamacao
	 * 
	 * @param Reclamacao
	 * @return ResponseEntity
	 */
	@PutMapping("/update/{id}")
	@ApiOperation(notes = "Atualiar os dados de uma Reclamacao", value = "Reclamacao", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<Reclamacao> update(@Validated @RequestBody Reclamacao Reclamacao,@PathVariable String id)
			 {
		if (id == null) {
			throw new BadRequestException("Informe o id");
		}
		Reclamacao = reclamacaoService.update(Reclamacao,id);
		return ResponseEntity.ok().body(Reclamacao);
	}
	
	/**
	 * Metodo responsavel por deletar uma Reclamacao
	 * 
	 * @param Reclamacao
	 * @return ResponseEntity
	 */
	@DeleteMapping("{id}")
	@ApiOperation(notes = "Deletar os dados de uma Reclamacao", value = "Reclamacao", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<?> update(@PathVariable String id){
		if (id == null) {
			throw new BadRequestException("Informe o id");
		}
		reclamacaoService.delete(id);
		return ResponseEntity.ok().build();
	}

	
}
