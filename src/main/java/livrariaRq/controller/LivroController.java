package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseLivro;
import livrariaRq.model.livro.Livro;
import livrariaRq.service.LivroService;

@RestController
public class LivroController {

	private final LivroService livroService;

	@Autowired
	public LivroController(LivroService aLivroService) {
		livroService = aLivroService;
	}

	@PostMapping("/addLivro")
	public ResponseEntity<SimpleResponseLivro> addLivro(@RequestBody Livro aLivro) {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (aLivro.getAutor() == null || aLivro.getAutor().isBlank()) {
			srl.setMessage("Autor inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getTitulo() == null || aLivro.getTitulo().isBlank()) {
			srl.setMessage("Título inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getPreco() >= 0) {
			srl.setMessage("Preço inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getQuantidadeStock() >= 0) {
			srl.setMessage("Quantidade em Stock inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getEditora() == null || aLivro.getEditora().isBlank()) {
			srl.setMessage("Editora inválida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getNumeroPaginas() >= 0) {
			srl.setMessage("Número de páginas inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getSinopse() == null || aLivro.getSinopse().isBlank()) {
			srl.setMessage("Editora do livro inválida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getEdicao() == null || aLivro.getEdicao().isBlank()) {
			srl.setMessage("Edição do livro inválida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (livroService.addLivro(aLivro)) {
			srl.setAsSuccess("Livro adicionado com sucesso");
			srl.setLivros(livroService.getLivros());
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		} else {
			srl.setMessage("Ocorreu um erro");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

	}

	@GetMapping("/getLivros")
	public List<Livro> getLivros() {
		return livroService.getLivros();
	}

}
