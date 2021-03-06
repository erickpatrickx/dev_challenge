package br.com.devchallenge.reclamacao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author erick.oliveira
 *
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Localidade {

	private String logradouro;
	
	private String bairro;
	
	private String cidade;
	
	private String UF;
	
	private String pais;
	
	
}
