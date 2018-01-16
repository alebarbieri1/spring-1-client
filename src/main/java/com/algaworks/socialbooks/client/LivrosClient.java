package com.algaworks.socialbooks.client;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.socialbooks.client.domain.Livro;

public class LivrosClient {

	public List<Livro> listar(){
		// Objeto utilizado para realizar as requisições HTTP
		RestTemplate restTemplate = new RestTemplate();
		
		// GET na URI http://localhost:8080/livros passando no header o campo Authorization
		RequestEntity<Void> request = RequestEntity
				.get(URI.create("http://localhost:8787/livros"))
				.header("Authorization", "Basic YWxnYXdvcmtzOnNlbmhh")
				.build();
		
		// Realiza a requisição
		// Para retornar um JSON, fazer conforme abaixo:
		// ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	public String salvar(Livro livro) {
		RestTemplate restTemplate = new RestTemplate();
		
		RequestEntity<Livro> request = RequestEntity
				.post(URI.create("http://localhost:8787/livros"))
				.header("Authorization", "Basic YWxnYXdvcmtzOnNlbmhh")
				.body(livro);
		
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
	}
}
