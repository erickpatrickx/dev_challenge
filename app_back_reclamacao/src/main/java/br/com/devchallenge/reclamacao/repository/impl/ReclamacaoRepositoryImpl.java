package br.com.devchallenge.reclamacao.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.devchallenge.reclamacao.entity.Localidade;
import br.com.devchallenge.reclamacao.entity.Reclamacao;
import br.com.devchallenge.reclamacao.repository.ReclamacaoRepositoryCustom;

public class ReclamacaoRepositoryImpl implements ReclamacaoRepositoryCustom {

	private final MongoTemplate mongoTemplate;
	 
	@Autowired
	public ReclamacaoRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
 
	
	@Override
	public List<Reclamacao> findReclamacoesPorEmpresa(String cnpj) {
		Query query = new Query();
		query.addCriteria(Criteria.where("empresa.cnpj").is(cnpj));
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
		query.addCriteria(Criteria.where("localidade.uf").regex("^"+ localidade.getUF()));
		
		if(localidade.getPais() != null)
		query.addCriteria(Criteria.where("localidade.pais").regex("^"+ localidade.getUF()));
			
		return 	mongoTemplate.find(query,Reclamacao.class);
	}	
	
	
	@Override
	public Long findQuantidadeReclamacoesPorEmpresa(String cnpj) {
		Query query = new Query();
		query.addCriteria(Criteria.where("empresa.cnpj").is(cnpj));
		return 	mongoTemplate.count(query,Long.class);
	}	
	
	@Override
	public Long findQuantidadeReclamacoesPorLocalidade(Localidade localidade) {
		Query query = new Query();
		
		if(localidade.getCidade() != null)
		query.addCriteria(Criteria.where("localidade.cidade").regex("^"+ localidade.getCidade()));
		
		if(localidade.getBairro() != null)
		query.addCriteria(Criteria.where("localidade.bairro").regex("^"+ localidade.getBairro()));
		
		if(localidade.getUF() != null)
		query.addCriteria(Criteria.where("localidade.uf").regex("^"+ localidade.getUF()));
		
		if(localidade.getPais() != null)
		query.addCriteria(Criteria.where("localidade.pais").regex("^"+ localidade.getUF()));
			
		return 	mongoTemplate.count(query,Long.class);
	}	
	
 
}
