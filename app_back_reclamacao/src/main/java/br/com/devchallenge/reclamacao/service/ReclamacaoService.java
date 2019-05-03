package br.com.devchallenge.reclamacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devchallenge.reclamacao.entity.Localidade;
import br.com.devchallenge.reclamacao.entity.Reclamacao;
import br.com.devchallenge.reclamacao.exception.BadRequestException;
import br.com.devchallenge.reclamacao.exception.BusinessException;
import br.com.devchallenge.reclamacao.repository.ReclamacaoRepository;

/**
 * Servicos da Reclamacao
 * 
 * @author erick.oliveira
 */
@Service
public class ReclamacaoService {

	@Autowired
	ReclamacaoRepository repository;
	
	
	/**
	 * Metodo de listar reclamações
	 * 
	 * @param filter
	 * @return List<Reclamacao>
	 */
	public List<Reclamacao> findReclamacoesAll() {
		return repository.findAll();
	}
	

	/**
	 * Metodo de buscar uma Reclamacao por localidade
	 * 
	 * @param Localidade localidade
	 * @return List<Reclamacao>
	 */
	public List<Reclamacao> findReclamacao(Localidade localidade) {
		return repository.findReclamacoesPorLocalidade(localidade);
	}
	
	/**
	 * Metodo de buscar uma Reclamacao por Empresa
	 * 
	 * @param String cnpj
	 * @return List<Reclamacao>
	 */
	public List<Reclamacao> findReclamacaoPorEmpresa(String cnpj) {
		return repository.findReclamacoesPorEmpresa(cnpj);
	}

	/**
	 * Metodo de buscar uma Reclamacao por id
	 * 
	 * @param filter
	 * @return List<Reclamacao>
	 */
	public Reclamacao findReclamacaoById(String id) {
		return repository.findById(id).orElseThrow(() -> new BadRequestException("Entidade não encontrada"));
	}

	/**
	 * Metodo de salvar uma Reclamacao
	 * 
	 * @param store
	 * @return Reclamacao
	 */
	public Reclamacao save(Reclamacao reclamacao) {
		if (reclamacao.getId() != null) {
			throw new BusinessException("Entidade não deve possuir ID");
		}
		repository.save(reclamacao);
		return reclamacao;
	}

	/**
	 * Metodo de atualizar uma Reclamacao
	 * 
	 * @param reclamacao
	 * @param id
	 * @return Reclamacao
	 */
	public Reclamacao update(Reclamacao reclamacao, String id) {
		if (!repository.findById(id).isPresent()) {
			throw new BadRequestException("Entidade não encontrada");
		}
		reclamacao.setId(id);
		repository.save(reclamacao);
		return reclamacao;
	}

	/**
	 * Metodo de atualizar uma Reclamacao
	 * 
	 * @param store
	 * @param id
	 * @return Reclamacao
	 */
	public void delete(String id) {
		if (!repository.findById(id).isPresent()) {
			throw new BadRequestException("Entidade  não encontrada");
		}
		repository.deleteById(id);
	}

}
