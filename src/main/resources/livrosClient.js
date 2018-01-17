function preencheLinhaTabela(livro){
	var linhaTabela = $("<tr/>");
	$("#tbLivrosBody").append(linhaTabela);
	linhaTabela.append("<td>" + livro.id + "</td>");
	linhaTabela.append("<td>" + livro.nome + "</td>");
	linhaTabela.append("<td>" + livro.editora + "</td>");
	linhaTabela.append("<td>" + livro.publicacao + "</td>");
	linhaTabela.append("<td>" + livro.resumo + "</td>");
}

function preencheTabela(livros){
	$("#tbLivrosBody tr").remove();
	$.each(livros, function(i, livro){
		preencheLinhaTabela(livro);
	});
}

$(document).ready(function(){
	$("#btnListarLivros").on("click", function(){
		$.ajax({
				url: "http://localhost:8787/livros",
				type: "GET",
				headers: {
					"Authorization": "Basic YWxnYXdvcmtzOnNlbmhh"
				},
				success: function(response){
					preencheTabela(response);
				}
			});
	});
});