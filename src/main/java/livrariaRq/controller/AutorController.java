package livrariaRq.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseAutor;
import livrariaRq.model.livro.Autor;

import livrariaRq.service.AutorService;

@RestController
public class AutorController {

	private final AutorService autorService;

	public AutorController(AutorService aAutorService) {
		autorService = aAutorService;
	}

	@PostMapping("/addAutor")
	public ResponseEntity<SimpleResponseAutor> addAutor(@RequestBody Autor aAutor) {
		SimpleResponseAutor sra = new SimpleResponseAutor();

		if (aAutor.getNome() == null || aAutor.getNome().isBlank()) {
			sra.setMessage("Tem de inserir um nome");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sra);
		}
		if (aAutor.getEmail() == null || aAutor.getEmail().isBlank()) {
			sra.setMessage("Tem de inserir uma morada");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sra);
		}

		if (aAutor.getDataNascimento() == null) {
			sra.setMessage("Data de nascimento inválida, formato esperado: dd-MM-yyyy");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sra);
		}

		if (aAutor.getEditora() == null) {
			sra.setMessage("O autor tem de pertencer a uma editora");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sra);
		}

		if (!autorService.VerificarEditora(aAutor)) {
			sra.setMessage("A editora nao existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sra);
		}

		if (autorService.addAutor(aAutor)) {
			sra.setAsSuccess("Autor adicionado com sucesso");
			sra.setAutores(autorService.getAllAutores());
			return ResponseEntity.status(HttpStatus.OK).body(sra);
		} else {
			sra.setMessage("Ocorreu um erro ao adicionar o autor");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sra);
		}
	}

	@GetMapping("/getAllAutores")
	public List<Autor> getAllAutores() {
		return autorService.getAllAutores();
	}

}
