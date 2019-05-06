package br.com.devchallenge.reclamacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devchallenge.reclamacao.entity.Empresa;
import br.com.devchallenge.reclamacao.exception.BadRequestException;
import br.com.devchallenge.reclamacao.exception.BusinessException;
import br.com.devchallenge.reclamacao.repository.EmpresaRepository;

/**
 * Servicos da Empresa
 * 
 * @author erick.oliveira
 *
 */
/**
 * @author erick.oliveira
 *
 */
@Service
public class EmpresaService {

	@Autowired
	EmpresaRepository repository;
	
	/**
	 * Metodo de buscar uma Empresa por id
	 * 
	 * @param filter
	 * @return List<Empresa>
	 */
	public Empresa findEmpresaById(String id) {
		return repository.findById(id).orElseThrow(() -> new BadRequestException("Entidade não encontrada")) ;
	}
	
	/**
	 * Metodo de salvar uma Empresa
	 * 
	 * @param store
	 * @return Empresa
	 */
	public Empresa save(Empresa empresa) {
		if(empresa.getId() != null) {
			throw new BusinessException("Entidade não deve possuir ID");
		}
		repository.save(empresa);
		return empresa;
	}

	/**
	 * Metodo de atualizar uma Empresa
	 * 
	 * @param empresa
	 * @param id
	 * @return Empresa
	 */
	public Empresa update(Empresa empresa,String id) {
		if(!repository.findById(id).isPresent()) {
			throw new BadRequestException("Entidade não encontrada");
		}
		empresa.setId(id);
		repository.save(empresa);
		return empresa;
	}
	
	

	/**
	 * Metodo de atualizar uma Empresa
	 * 
	 * @param store
	 * @param id
	 * @return Empresa
	 */
	public void delete(String id) {
		if(!repository.findById(id).isPresent()) {
			throw new BadRequestException("Entidade  não encontrada");
		}
		repository.deleteById(id);
	}

	
	/**
	 * Metodo para remover todoas as empresas
	 * 
	 * @param store
	 * @param id
	 * @return Empresa
	 */
	public void deleteAll() {
		repository.deleteAll();
	}
	

}
