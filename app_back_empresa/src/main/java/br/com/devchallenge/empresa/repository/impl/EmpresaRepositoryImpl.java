package br.com.devchallenge.empresa.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import br.com.devchallenge.empresa.entity.Empresa;
import br.com.devchallenge.empresa.repository.EmpresaRepositoryCustom;

/**
 * Implementação do repositorio de empresa
 * @author erick.oliveira
 *
 */
public class EmpresaRepositoryImpl implements EmpresaRepositoryCustom {

	private final MongoTemplate mongoTemplate;
	 
	@Autowired
	public EmpresaRepositoryImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
 
	@Override
	public List<Empresa> findByNomeLikeOrCnpjLike(String nome, String cnpj) {
		Query query = new Query();
		
		if(nome != null)
		query.addCriteria(Criteria.where("nome").regex("^"+ nome));
		
		if(cnpj != null)
		query.addCriteria(Criteria.where("cnpj").regex("^"+cnpj));

		List<Empresa> empresas = mongoTemplate.find(query,Empresa.class);
		return empresas;
	}	
 
}
