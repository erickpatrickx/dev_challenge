package br.com.devchallenge.reclamacao.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.devchallenge.reclamacao.entity.Localidade;
import br.com.devchallenge.reclamacao.entity.Reclamacao;


/**
 * Repositorio do Store
 * @author erick.oliveira
 *
 */
@Repository
public interface ReclamacaoRepositoryCustom {


	Long findQuantidadeReclamacoesPorLocalidade(Localidade localidade);

	Long findQuantidadeReclamacoesPorEmpresa(String cnpj);

	List<Reclamacao> findReclamacoesPorEmpresa(String cnpj);

	List<Reclamacao> findReclamacoesPorLocalidade(Localidade localidade);
    
}
