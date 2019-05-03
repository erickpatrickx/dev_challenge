package br.com.devchallenge.reclamacao.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Localidade {


	@NotEmpty
    @Size(min=3,max=100,message="Campo Logradouro: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String logradouro;
	
	@NotEmpty
    @Size(min=3,max=100,message="Campo Bairro: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String bairro;
	
	@NotEmpty
    @Size(min=3,max=100,message="Campo Cidade: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String cidade;
	
	@NotEmpty
    @Size(min=2,max=2,message="Campo UF: Limite de caracteres excedido - minimo 2 e maximo 2")
	private String UF;
	
	@NotEmpty
    @Size(min=3,max=100,message="Campo Pais: Limite de caracteres excedido - minimo 3 e maximo 250")
	private String pais;
	
	
}
