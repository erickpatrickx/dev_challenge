package br.com.devchallenge.reclamacao;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;



@SpringBootApplication
@EnableAutoConfiguration
@ImportAutoConfiguration(FeignAutoConfiguration.class)
@ComponentScan(value="br.com.devchallenge")
@EnableDiscoveryClient
public class ReclamacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReclamacaoApplication.class, args);
	}
	
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
