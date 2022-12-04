package com.br.projetoaplicado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
public class ProjetoaplicadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoaplicadoApplication.class, args);

	}

}
