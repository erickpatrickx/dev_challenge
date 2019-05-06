package br.com.devchallenge.reclamacao.dtos;

import javax.validation.constraints.NotEmpty;

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
public class EmpresaDTO {
	
	@NotEmpty( message = "Informe a empresa")
	private String id;
}
