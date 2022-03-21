package livrariaRq.controller;

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
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.service.ClienteLivroService;
import livrariaRq.service.FuncionarioLivroService;
import livrariaRq.service.LivroService;
import livrariaRq.utils.WrapperFuncionarioLivro;

@RestController
public class LivroController {

	private final LivroService livroService;
	private final FuncionarioLivroService funcionarioLivroService;
	private final ClienteLivroService clienteLivroService;

	@Autowired
	public LivroController(LivroService aLivroService, FuncionarioLivroService aFuncionarioLivroService,
			ClienteLivroService aClienteLivroService) {
		livroService = aLivroService;
		funcionarioLivroService = aFuncionarioLivroService;
		clienteLivroService = aClienteLivroService;
	}

	@PostMapping("/addLivro")
	public ResponseEntity<SimpleResponseLivro> addLivro(@RequestBody WrapperFuncionarioLivro aWrapper) {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (aWrapper.getLivro().getAutor() == null || aWrapper.getLivro().getAutor().isBlank()) {
			srl.setMessage("Tem de inserir um autor");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aWrapper.getLivro().getTitulo() == null || aWrapper.getLivro().getTitulo().isBlank()) {
			srl.setMessage("Tem de inserir um titulo");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroService.verificarIsbnExistente(aWrapper.getLivro())) {
			srl.setMessage("O ISBN digitado já existe na base de dados");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroService.verificarTamanhoIsbn(aWrapper.getLivro())) {
			srl.setMessage("O ISBN não pode ser inferior a 10 caracteres");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroService.verificarValidacaoIsbn(aWrapper.getLivro())) {
			srl.setMessage("O ISBN não é valido");
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
			srl.setMessage("Tem de inserir uma editora");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aWrapper.getLivro().getNumeroPaginas() <= 0) {
			srl.setMessage("Número de páginas inválido, tem de ser maior que 0");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aWrapper.getLivro().getSinopse() == null || aWrapper.getLivro().getSinopse().isBlank()) {
			srl.setMessage("Tem de inserir uma sinopse");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aWrapper.getLivro().getEdicao() == null || aWrapper.getLivro().getEdicao().isBlank()) {
			srl.setMessage("Tem de inserir uma edição do livro");
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
	public ResponseEntity<SimpleResponseLivro> getLivros(@RequestBody Cliente aCliente) {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (!clienteLivroService.autenticacaoLivros(aCliente)) {
			srl.setMessage("Tem de fazer login primeiro, para conseguir ver os livros disponíveis na livraria");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (livroService.getLivros().isEmpty()) {
			srl.setMessage("nao tem livros registados na livraria");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (clienteLivroService.autenticacaoLivros(aCliente)) {
			srl.setLivros(livroService.getLivros());
			srl.setAsSuccess("Lista de Livros:");
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);

	}

	@PutMapping("/updateLivro")
	public ResponseEntity<SimpleResponseLivro> updateLivro(@RequestBody Livro aLivro) {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (livroService.updateLivro(aLivro)) {
			srl.setAsSuccess("Livro atualizado com sucesso");
			srl.setLivros(livroService.getLivros());
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
	}
}
