package com.algaworks.socialbooks.aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.algaworks.socialbooks.client.LivrosClient;
import com.algaworks.socialbooks.client.domain.Livro;

public class Aplicacao {
	public static void main(String[] args) throws ParseException {
		LivrosClient cliente = new LivrosClient();
		
		List<Livro> livros = cliente.listar();
		
		for (Livro livro : livros) {
			System.out.println("Livro: " + livro.getNome());
		}
		
		Livro livro = new Livro();
		livro.setNome("Git passo-a-passo");
		livro.setEditora("Algaworks");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		livro.setPublicacao(sdf.parse("15/01/2018"));
		
		livro.setResumo("Resumo do livro");
		
		String localizacao = cliente.salvar(livro);
		
		System.out.println("URI do livro salvo: " + localizacao);
	}
}
