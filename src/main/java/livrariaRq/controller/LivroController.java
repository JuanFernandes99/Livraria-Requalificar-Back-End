package livrariaRq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseLivro;
import livrariaRq.model.livro.Autor;
import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.service.AutorEditoraService;
import livrariaRq.service.ClienteLivroService;
import livrariaRq.service.FuncionarioLivroService;
import livrariaRq.service.LivroAutorService;
import livrariaRq.service.LivroCarrinhoService;
import livrariaRq.service.LivroService;
import livrariaRq.utils.WrapperFuncionarioLivro;

@RestController
public class LivroController {

	private final LivroService livroService;
	private final FuncionarioLivroService funcionarioLivroService;
	private final ClienteLivroService clienteLivroService;
	private final LivroAutorService livroAutorService;
	private final AutorEditoraService autorEditoraService;
	private final LivroCarrinhoService livroCarrinhoService;

	@Autowired
	public LivroController(LivroService aLivroService, FuncionarioLivroService aFuncionarioLivroService,
			ClienteLivroService aClienteLivroService, LivroAutorService aLivroAutorService,
			AutorEditoraService aAutorEditoraService, LivroCarrinhoService aLivroCarrinhoService) {
		livroService = aLivroService;
		funcionarioLivroService = aFuncionarioLivroService;
		clienteLivroService = aClienteLivroService;
		livroAutorService = aLivroAutorService;
		autorEditoraService = aAutorEditoraService;
		livroCarrinhoService = aLivroCarrinhoService;
	}

	@PostMapping("/addLivro")
	public ResponseEntity<SimpleResponseLivro> addLivro(@RequestBody WrapperFuncionarioLivro aWrapper) {
		SimpleResponseLivro srl = new SimpleResponseLivro();
		// Optional<Livro> livroOpcional =
		// livroRepo.findById(aWrapper.getLivro().getId());
		if (aWrapper.getLivro().getTitulo() == null || aWrapper.getLivro().getTitulo().isBlank()) {
			srl.setMessage("Tem de inserir um titulo");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aWrapper.getLivro().getDataLancamento() == null) {
			srl.setMessage("Tem de inserir uma data");
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
		/*
		 * if (!livroService.verificarValidacaoIsbn(aWrapper.getLivro())) {
		 * srl.setMessage("O ISBN não é valido"); return
		 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl); }
		 */
		if (aWrapper.getLivro().getPreco() <= 0) {
			srl.setMessage("Preço inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aWrapper.getLivro().getQuantidadeStock() <= 0) {
			srl.setMessage("Quantidade em Stock inválido");
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

		if (aWrapper.getLivro().getEditora() == null) {
			srl.setMessage("Tem de inserir uma editora ao livro");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aWrapper.getLivro().getAutores() == null || aWrapper.getLivro().getAutores().isEmpty()) {
			srl.setMessage("Tem de inserir pelo menos um autor");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (funcionarioLivroService.addLivro(aWrapper.getFuncionario(), aWrapper.getLivro())) {
			srl.setAsSuccess("Livro adicionado com sucesso");
			srl.setLivros(livroService.getAllLivros());
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		} else {
			srl.setMessage("Ocorreu um erro");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
	}

	@PostMapping("/addLivro/{aLivro_id}/autor/{aAutor_id}")
	public String addLivroToAutorByIds(@PathVariable String aLivro_id, @PathVariable String aAutor_id) {
		return livroAutorService.addLivroToAutor(aLivro_id, aAutor_id);
	}

	@PostMapping("/addLivro/{aLivro_id}/carrinho/{aCarrinho_id}")
	public String addLivroToCarrinhoByIds(@PathVariable String aLivro_id, @PathVariable String aCarrinho_id) {
		return livroCarrinhoService.adicionarLivroToCarrinho(aLivro_id, aCarrinho_id);
	}

	@GetMapping("/getAllLivros")
	public ResponseEntity<SimpleResponseLivro> getAllLivros(@RequestBody Cliente aCliente) {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (!clienteLivroService.autenticacaoLivros(aCliente)) {
			srl.setMessage("Tem de fazer login primeiro, para conseguir ver os livros disponíveis na livraria");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (livroService.getAllLivros().isEmpty()) {
			srl.setMessage("não tem livros registados na livraria");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (clienteLivroService.autenticacaoLivros(aCliente)) {
			srl.setLivros(livroService.getAllLivros());
			srl.setAsSuccess("Lista de Livros existentes na livraria:");
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);

	}

	// verificar se está correto!!
	@GetMapping("/getAllLivrosFuncionario")
	public ResponseEntity<SimpleResponseLivro> getAllLivrosFuncionario(@RequestBody Funcionario aFuncionario) {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (!funcionarioLivroService.autenticacaoFuncionario(aFuncionario)) {
			srl.setMessage("Tem de fazer login primeiro, para conseguir ver os livros disponíveis na livraria");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (livroService.getAllLivros().isEmpty()) {
			srl.setMessage("não tem livros registados na livraria");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (funcionarioLivroService.autenticacaoFuncionario(aFuncionario)) {
			srl.setLivros(livroService.getAllLivros());
			srl.setAsSuccess("Lista de Livros existentes na livraria:");
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);

	}

	@GetMapping("/getLivroById/{aId}")
	public Livro getCentroComercialById(@PathVariable String aId) {
		return livroService.getLivroById(aId).get();
	}

	@PutMapping("/updateLivro")
	public ResponseEntity<SimpleResponseLivro> updateLivro(@RequestBody Livro aLivro) {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (livroService.updateLivro(aLivro)) {
			srl.setAsSuccess("Livro atualizado com sucesso");
			srl.setLivros(livroService.getAllLivros());
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
	}

	@GetMapping("/getLivrosPrecoAsc")
	public List<Livro> getLivrosPrecoAsc() {
		return livroService.getLivrosPrecoAsc();
	}

	@GetMapping("/getLivrosPrecoDesc")
	public List<Livro> getLivrosPrecoDec() {
		return livroService.getLivrosPrecoDesc();
	}

	@GetMapping("/getLivrosDataDesc")
	public List<Livro> getLivrosPorDataDesc() {
		return livroService.getLivrosPorDataDesc();
	}

	@GetMapping("/getLivrosDataAsc")
	public List<Livro> getLivrosPorDataAsc() {
		return livroService.getLivrosPorDataAsc();
	}

	@GetMapping("/getLivrosPorEditora/{aEditora_id}")
	public List<Livro> getLivrosPorEditora(@PathVariable String aEditora_id) {
		return autorEditoraService.getLivrosPorEditora(aEditora_id);
	}

	@GetMapping("/getLivrosPorAutor/{aAutor_id}")
	public List<Livro> getLivrosPorAutor(@PathVariable String aAutor_id) {

		return autorEditoraService.getLivrosPorAutor(aAutor_id);
	}

}
