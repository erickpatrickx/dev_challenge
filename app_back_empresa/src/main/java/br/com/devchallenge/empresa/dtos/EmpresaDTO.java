package br.com.devchallenge.empresa.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;

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
	
	@NotEmpty(message = "Nome não informado")
    @Size(min=3,max=100,message="Campo Nome: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String nome;
	
	@NotEmpty(message = "CNPJ não informado")
	@Pattern(regexp="([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})",message="CNPJ Invalido")
	@Indexed
	private String cnpj;
	
}
