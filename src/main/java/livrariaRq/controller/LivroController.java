package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseLivro;
import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.service.FuncionarioLivroService;
import livrariaRq.service.LivroService;
import livrariaRq.utils.WrapperFuncionarioLivro;

@RestController
public class LivroController {

	private final LivroService livroService;
	private final FuncionarioLivroService funcionarioLivroService;

	@Autowired
	public LivroController(LivroService aLivroService, FuncionarioLivroService aFuncionarioLivroService) {
		livroService = aLivroService;
		funcionarioLivroService = aFuncionarioLivroService;
	}

	@PostMapping("/addLivro")
	public ResponseEntity<SimpleResponseLivro> addLivro(@RequestBody WrapperFuncionarioLivro aWrapper) {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (aWrapper.getLivro().getAutor() == null || aWrapper.getLivro().getAutor().isBlank()) {
			srl.setMessage("Autor inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aWrapper.getLivro().getTitulo() == null || aWrapper.getLivro().getTitulo().isBlank()) {
			srl.setMessage("Título inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aWrapper.getLivro().getPreco() <= 0) {
			srl.setMessage("Preço inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aWrapper.getLivro().getPreco() <= 0) {
			srl.setMessage("Preço inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aWrapper.getLivro().getQuantidadeStock() <= 0) {

			srl.setMessage("Quantidade em Stock inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aWrapper.getLivro().getEditora() == null || aWrapper.getLivro().getEditora().isBlank()) {
			srl.setMessage("Editora inválida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aWrapper.getLivro().getNumeroPaginas() <= 0) {

			srl.setMessage("Número de páginas inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aWrapper.getLivro().getSinopse() == null || aWrapper.getLivro().getSinopse().isBlank()) {
			srl.setMessage("Editora do livro inválida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aWrapper.getLivro().getEdicao() == null || aWrapper.getLivro().getEdicao().isBlank()) {
			srl.setMessage("Edição do livro inválida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (funcionarioLivroService.addLivro(aWrapper.getFuncionario(), aWrapper.getLivro())) {
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

	@PutMapping("/updateLivro")
	public ResponseEntity<SimpleResponseLivro> updateLivro(@RequestBody Livro aLivro) {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (livroService.uptadeLivro(aLivro)) {
			srl.setAsSuccess("Livro atualizado com sucesso");
			srl.setLivros(livroService.getLivros());
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
	}
}
