package br.com.devchallenge.empresa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.devchallenge.empresa.entity.Empresa;


/**
 * Repositorio da Empresa
 * @author erick.oliveira
 *
 */
@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String>,EmpresaRepositoryCustom {

    
}
