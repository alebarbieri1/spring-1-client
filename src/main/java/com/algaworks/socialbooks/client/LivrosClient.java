package com.algaworks.socialbooks.client;

import java.net.URI;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.algaworks.socialbooks.client.domain.Livro;

public class LivrosClient {
	
	// Objeto utilizado para realizar as requisições HTTP
	private RestTemplate restTemplate;
	
	private String BASE_URI;
	
	private String URN_BASE = "/livros";
	
	private String credencial;
	
	public LivrosClient(String url, String usuario, String senha) {
		this.restTemplate = new RestTemplate();
		this.BASE_URI = url.concat(URN_BASE);
		
		String credencialAux = usuario + ":" + senha;
		
		this.credencial = "Basic " + Base64.getEncoder()
					.encodeToString(credencialAux.getBytes());
		
	}

	public List<Livro> listar(){
		// GET na URI http://localhost:8080/livros passando no header o campo Authorization
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(BASE_URI))
				.header("Authorization", credencial)
				.build();
		
		// Realiza a requisição
		// Para retornar um JSON, fazer conforme abaixo:
		// ResponseEntity<String> response = restTemplate.exchange(request, String.class);
		ResponseEntity<Livro[]> response = restTemplate.exchange(request, Livro[].class);
		
		return Arrays.asList(response.getBody());
	}
	
	public String salvar(Livro livro) {
		RequestEntity<Livro> request = RequestEntity
				.post(URI.create(BASE_URI))
				.header("Authorization", credencial)
				.body(livro);
		
		ResponseEntity<Void> response = restTemplate.exchange(request, Void.class);
		
		return response.getHeaders().getLocation().toString();
	}
	
	public Livro buscar(String uri) {
		RequestEntity<Void> request = RequestEntity
				.get(URI.create(uri))
				.header("Authorization", credencial)
				.build();
		
		ResponseEntity<Livro> response = restTemplate.exchange(request, Livro.class);
		
		return response.getBody();
	}
}
