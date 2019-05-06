package br.com.devchallenge.empresa.controllers;

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

import br.com.devchallenge.empresa.dtos.EmpresaDTO;
import br.com.devchallenge.empresa.entity.Empresa;
import br.com.devchallenge.empresa.exception.BadRequestException;
import br.com.devchallenge.empresa.service.EmpresaService;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * Controller do microservice de Empresa
 * 
 * @author erick.oliveira
 *
 */
@RestController
@RequestMapping("/empresas")
public class EmpresaController {
	
	private final EmpresaService empresaService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public EmpresaController(EmpresaService empresaService) {
		this.empresaService = empresaService;
	}
	
	
	/**
	 * Consultar Empresa por ID
	 * 
	 * @param FilterEmpresa
	 * @return ResponseEntity
	 */
	@GetMapping
	@ApiOperation(notes = "Consultar empresa por nome e/ou CNPJ", value = "Consultar empresa por nome e/ou CNPJ", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<List<Empresa>> getEmpresaPorNome(@RequestParam(required = false) String nome , @RequestParam(required = false) String cnpj) {
		return ResponseEntity.status(HttpStatus.OK).body(empresaService.findEmpresa(nome, cnpj));
	}

	/**
	 * Consultar Empresa por ID
	 * 
	 * @param FilterEmpresa
	 * @return ResponseEntity
	 */
	@GetMapping("/{id}")
	@ApiOperation(notes = "Consultar empresa por ID", value = "Consultar empresa por ID", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<Empresa> getEmpresaPoID(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(empresaService.findEmpresaById(id));
	}
	
	/**
	 * Metodo responsavel por salvar uma Empresa
	 * 
	 * @param Empresa
	 * @return ResponseEntity
	 */
	@PostMapping("/save")
	@ApiOperation(notes = "Salvar Empresa", value = "Salvar Empresa", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<Empresa> save(@Validated @RequestBody EmpresaDTO dto) {
		Empresa empresa =  modelMapper.map(dto, Empresa.class);
		empresa = empresaService.save(empresa);
		return ResponseEntity.status(HttpStatus.CREATED).body(empresa);
	}

	/**
	 * Metodo responsavel por atualizar uma Empresa
	 * 
	 * @param Empresa
	 * @return ResponseEntity
	 */
	@PutMapping("/update/{id}")
	@ApiOperation(notes = "Atualizar Empresa", value = "Atualizar Empresa", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<Empresa> update(@Validated @RequestBody EmpresaDTO dto,@PathVariable String id)
			 {
		if (id == null) {
			throw new BadRequestException("Informe o id");
		}
		Empresa empresa =  modelMapper.map(dto, Empresa.class);
		empresa = empresaService.update(empresa,id);
		return ResponseEntity.ok().body(empresa);
	}
	
	/**
	 * Metodo responsavel por deletar uma Empresa
	 * 
	 * @param Empresa
	 * @return ResponseEntity
	 */
	@DeleteMapping("{id}")
	@ApiOperation(notes = "Deletar Empresa", value = "Deletar Empresa", response = ResponseEntity.class)
    @Secured("ROLE_ADMIN")
	public ResponseEntity<?> update(@PathVariable String id){
		if (id == null) {
			throw new BadRequestException("Informe o id");
		}
		empresaService.delete(id);
		return ResponseEntity.ok().build();
	}

	
}
