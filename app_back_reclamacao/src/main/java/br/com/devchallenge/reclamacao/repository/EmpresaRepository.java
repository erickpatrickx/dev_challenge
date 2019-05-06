package br.com.devchallenge.reclamacao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.devchallenge.reclamacao.entity.Empresa;


/**
 * Repositorio da Empresa
 * @author erick.oliveira
 *
 */
@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, String> {

    
}
