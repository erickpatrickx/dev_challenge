package br.com.devchallenge.reclamacao.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
public class ReclamacaoDTO {


	@NotEmpty
    @Size(min=3,max=100,message="Campo Titulo: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String titulo;
	
	@NotEmpty
    @Size(min=3,max=100,message="Campo Titulo: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String descricao;
	
	private LocalidadeDTO localidade;
	
	private EmpresaDTO empresa;
	
	
}
