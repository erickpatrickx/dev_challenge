package br.com.devchallenge.reclamacao;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.devchallenge.reclamacao.security.TokenAuthenticationService;
import lombok.Getter;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:application_test.yml")
@Getter
public class ReclamacaoApplicationTests {

	@Test
	public void contextLoads() {
	}


	ModelMapper modelMapper = new ModelMapper();

	ObjectMapper objectMapper = new ObjectMapper();
	
	protected String getToken(String role) throws IOException {
		return 	TokenAuthenticationService.generateToken("user", role);
	}

}