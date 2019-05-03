package br.com.devchallenge.reclamacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EnableAutoConfiguration
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@ComponentScan(value="br.com.devchallenge")
public class ReclamacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReclamacaoApplication.class, args);
	}

}
