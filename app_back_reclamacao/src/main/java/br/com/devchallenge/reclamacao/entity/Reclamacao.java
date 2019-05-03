package br.com.devchallenge.reclamacao.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
public class Reclamacao {


	@Id
	private String id;

	@NotEmpty
    @Size(min=3,max=100,message="Campo Titulo: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String titulo;
	
	@NotEmpty
    @Size(min=3,max=100,message="Campo Titulo: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String descricao;
	
	private Localidade localidade;
	
	@DBRef
	private Empresa empresa;
	
	
}
