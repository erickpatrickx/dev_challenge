package br.com.devchallenge.empresa.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.devchallenge.empresa.entity.Empresa;


/**
 * Repositorio da  Empresa Custom
 * @author erick.oliveira
 *
 */
@Repository
public interface EmpresaRepositoryCustom {

    List<Empresa> findByNomeLikeOrCnpjLike(String nome, 
            String cnpj);
    
}
