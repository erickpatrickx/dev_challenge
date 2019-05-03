package br.com.devchallenge.reclamacao.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Empresa {
	
	
	@Id
	private String id;

	@NotEmpty(message = "Nome não informado")
    @Size(min=3,max=100,message="Campo Nome: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String nome;
	
	@NotEmpty(message = "CNPJ não informado")
	@Pattern(regexp="([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})",message="CNPJ Invalido")
	@Indexed
	private String cnpj;
	
}
