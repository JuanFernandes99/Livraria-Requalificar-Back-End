package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseLivro;
import livrariaRq.model.livro.Livro;
import livrariaRq.service.AutorEditoraService;
import livrariaRq.service.LivroEditoraAutorService;
import livrariaRq.service.LivroService;

@CrossOrigin
@RestController
public class LivroController {

	private final LivroService livroService;
	private final LivroEditoraAutorService livroEditoraAutorService;
	private final AutorEditoraService autorEditoraService;

	@Autowired
	public LivroController(LivroService aLivroService, LivroEditoraAutorService aLivroEditoraAutorService,
			AutorEditoraService aAutorEditoraService) {

		livroService = aLivroService;
		livroEditoraAutorService = aLivroEditoraAutorService;
		autorEditoraService = aAutorEditoraService;
	}

	@CrossOrigin
	@PostMapping(value = "/addLivro")

	public ResponseEntity<SimpleResponseLivro> addLivro(@RequestBody Livro aLivro) {
		SimpleResponseLivro srl = new SimpleResponseLivro();
		/*
		 * try { aLivro.setImage(file.getBytes()); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		if (aLivro.getTitulo() == null || aLivro.getTitulo().isBlank()) {
			srl.setMessage("Tem de inserir um titulo");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getDataLancamento() == null) {
			srl.setMessage("Tem de inserir uma data");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getIsbn() == null) {
			srl.setMessage("Tem de inserir um ISBN");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroService.verificarIsbnExistente(aLivro)) {
			srl.setMessage("O ISBN digitado já existe na base de dados");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroService.verificarTamanhoIsbn(aLivro)) {
			srl.setMessage("O ISBN não pode ser inferior a 10 caracteres");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		// Função que verifica o ISBN pelas regras dele e verifica se é valido

		/*
		 * if (!livroService.verificarValidacaoIsbn(aWrapper.getLivro())) {
		 * srl.setMessage("O ISBN não é valido"); return
		 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl); }
		 */
		if (aLivro.getPreco() <= 0) {
			srl.setMessage("Preço inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getQuantidadeStock() <= 0) {
			srl.setMessage("Quantidade em Stock inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getNumeroPaginas() <= 0) {
			srl.setMessage("Número de páginas inválido, tem de ser maior que 0");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getSinopse() == null || aLivro.getSinopse().isBlank()) {
			srl.setMessage("Tem de inserir uma sinopse");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getEdicao() == null || aLivro.getEdicao().isBlank()) {
			srl.setMessage("Tem de inserir uma edição do livro");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getAutores() == null || aLivro.getAutores().isEmpty()) {
			srl.setMessage("Tem de inserir pelo menos um autor");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (!livroEditoraAutorService.VerificarAutor(aLivro)) {
			srl.setMessage("O autor(es) nao existem");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getEditora() == null) {
			srl.setMessage("Tem de inserir uma edição do livro");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroEditoraAutorService.VerificarEditora(aLivro)) {
			srl.setMessage("A editora nao existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (!livroEditoraAutorService.autoresEditora(aLivro)) {
			srl.setMessage("Os autores tem de pertencer  a mesma editora");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (livroEditoraAutorService.addLivro(aLivro)) {
			srl.setAsSuccess("Livro adicionado com sucesso");
			srl.setLivros(livroService.getAllLivros());
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		} else {
			srl.setMessage("Ocorreu um erro ao adicionar o livro");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
	}

	@CrossOrigin
	@GetMapping("/getAllLivros")
	public ResponseEntity<SimpleResponseLivro> getAllLivros() {
		SimpleResponseLivro srl = new SimpleResponseLivro();

		if (livroService.getAllLivros().isEmpty()) {
			srl.setMessage("não tem livros registados na livraria");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		srl.setLivros(livroService.getAllLivros());
		srl.setAsSuccess("Lista de Livros existentes na livraria:");
		return ResponseEntity.status(HttpStatus.OK).body(srl);

	}

	@CrossOrigin
	@GetMapping("/getLivroById/{aId}")
	public Livro getCentroComercialById(@PathVariable String aId) {
		return livroService.getLivroById(aId).get();
	}

	@CrossOrigin
	@PutMapping("/updateLivro")
	public ResponseEntity<SimpleResponseLivro> updateLivro(@RequestBody Livro aLivro) {
		SimpleResponseLivro srl = new SimpleResponseLivro();
		if (aLivro.getTitulo() == null || aLivro.getTitulo().isBlank()) {
			srl.setMessage("Tem de inserir um titulo");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		/*
		 * if (aLivro.getDataLancamento() == null) {
		 * srl.setMessage("Tem de inserir uma data"); return
		 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl); }
		 */

		if (aLivro.getIsbn() == null) {
			srl.setMessage("Tem de inserir um ISBN");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroService.verificarIsbnExistenteStock(aLivro)) {
			srl.setMessage("O ISBN digitado já existe na base de dados");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroService.verificarTamanhoIsbn(aLivro)) {
			srl.setMessage("O ISBN não pode ser inferior a 10 caracteres");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		/*
		 * if (!livroService.verificarValidacaoIsbn(aWrapper.getLivro())) {
		 * srl.setMessage("O ISBN não é valido"); return
		 * ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl); }
		 */
		if (aLivro.getPreco() <= 0) {
			srl.setMessage("Preço inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getQuantidadeStock() <= 0) {
			srl.setMessage("Quantidade em Stock inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getNumeroPaginas() <= 0) {
			srl.setMessage("Número de páginas inválido, tem de ser maior que 0");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getSinopse() == null || aLivro.getSinopse().isBlank()) {
			srl.setMessage("Tem de inserir uma sinopse");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (aLivro.getEdicao() == null || aLivro.getEdicao().isBlank()) {
			srl.setMessage("Tem de inserir uma edição do livro");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getAutores() == null || aLivro.getAutores().isEmpty()) {
			srl.setMessage("Tem de inserir pelo menos um autor");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (!livroEditoraAutorService.VerificarAutor(aLivro)) {
			srl.setMessage("O autor(es) nao existem");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}
		if (aLivro.getEditora() == null) {
			srl.setMessage("Tem de inserir uma edição do livro");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroEditoraAutorService.VerificarEditora(aLivro)) {
			srl.setMessage("A editora nao existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (!livroEditoraAutorService.autoresEditora(aLivro)) {
			srl.setMessage("Os autores tem de pertencer  a mesma editora");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
		}

		if (livroService.updateLivro(aLivro)) {
			srl.setAsSuccess("Livro atualizado com sucesso");
			srl.setLivros(livroService.getAllLivros());
			return ResponseEntity.status(HttpStatus.OK).body(srl);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srl);
	}

	@CrossOrigin
	@GetMapping("/getLivrosPrecoAsc")
	public List<Livro> getLivrosPrecoAsc() {
		return livroService.getLivrosPrecoAsc();
	}

	@CrossOrigin
	@GetMapping("/getLivrosPrecoDesc")
	public List<Livro> getLivrosPrecoDec() {
		return livroService.getLivrosPrecoDesc();
	}

	@CrossOrigin
	@GetMapping("/getLivrosDataDesc")
	public List<Livro> getLivrosPorDataDesc() {
		return livroService.getLivrosPorDataDesc();
	}

	@CrossOrigin
	@GetMapping("/getLivrosDataAsc")
	public List<Livro> getLivrosPorDataAsc() {
		return livroService.getLivrosPorDataAsc();
	}

	@CrossOrigin
	@GetMapping("/getLivrosPorEditora/{aEditora_id}")
	public List<Livro> getLivrosPorEditora(@PathVariable String aEditora_id) {
		return autorEditoraService.getLivrosPorEditora(aEditora_id);
	}

	@CrossOrigin
	@GetMapping("/getLivrosPorAutor/{aAutor_id}")
	public List<Livro> getLivrosPorAutor(@PathVariable String aAutor_id) {

		return autorEditoraService.getLivrosPorAutor(aAutor_id);
	}

}
