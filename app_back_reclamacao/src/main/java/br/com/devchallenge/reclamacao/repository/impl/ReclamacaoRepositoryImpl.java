package br.com.devchallenge.reclamacao.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.devchallenge.reclamacao.entity.Localidade;
import br.com.devchallenge.reclamacao.entity.Reclamacao;
import br.com.devchallenge.reclamacao.repository.ReclamacaoRepositoryCustom;

/**
 * Implementação do repositorio de Reclamação
 * @author erick.oliveira
 *
 */
public class ReclamacaoRepositoryImpl implements ReclamacaoRepositoryCustom {

	private final MongoTemplate mongoTemplate;
	 
	@Autowired
	public ReclamacaoRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
 
	
	@Override
	public List<Reclamacao> findReclamacoesPorEmpresa(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("empresa.id").is(id));
		return 	mongoTemplate.find(query,Reclamacao.class);
	}	
	
	@Override
	public List<Reclamacao> findReclamacoesPorLocalidade(Localidade localidade) {
		Query query = new Query();
		
		if(localidade.getCidade() != null)
		query.addCriteria(Criteria.where("localidade.cidade").regex("^"+ localidade.getCidade()));
		
		if(localidade.getBairro() != null)
		query.addCriteria(Criteria.where("localidade.bairro").regex("^"+ localidade.getBairro()));
		
		if(localidade.getUF() != null)
		query.addCriteria(Criteria.where("localidade.UF").regex("^"+ localidade.getUF()));
		
		if(localidade.getPais() != null)
		query.addCriteria(Criteria.where("localidade.pais").regex("^"+ localidade.getPais()));
			
		return 	mongoTemplate.find(query,Reclamacao.class);
	}	
	
	
	@Override
	public Long findQuantidadeReclamacoesPorEmpresa(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("empresa.id").is(id));
		return 	mongoTemplate.count(query,Reclamacao.class);
	}	
	
	@Override
	public Long findQuantidadeReclamacoesPorLocalidade(Localidade localidade) {
		Query query = new Query();
		
		if(localidade.getCidade() != null)
		query.addCriteria(Criteria.where("localidade.cidade").regex("^"+ localidade.getCidade()));
		
		if(localidade.getBairro() != null)
		query.addCriteria(Criteria.where("localidade.bairro").regex("^"+ localidade.getBairro()));
		
		if(localidade.getUF() != null)
		query.addCriteria(Criteria.where("localidade.UF").regex("^"+ localidade.getUF()));
		
		if(localidade.getPais() != null)
		query.addCriteria(Criteria.where("localidade.pais").regex("^"+ localidade.getPais()));
			
		return 	mongoTemplate.count(query,Reclamacao.class);
	}	
	
 
}
