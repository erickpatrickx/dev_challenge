package br.com.devchallenge.reclamacao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.devchallenge.reclamacao.entity.Reclamacao;


/**
 * Repositorio do Store
 * @author erick.oliveira
 *
 */
@Repository
public interface ReclamacaoRepository extends MongoRepository<Reclamacao, String>,ReclamacaoRepositoryCustom {

    
}
