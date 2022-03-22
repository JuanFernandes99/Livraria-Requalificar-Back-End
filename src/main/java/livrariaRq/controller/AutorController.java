package livrariaRq.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseAutor;
import livrariaRq.model.livro.Autor;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.service.AutorEditoraService;
import livrariaRq.service.AutorService;

@RestController
public class AutorController {

	private final AutorService autorService;
	private final AutorEditoraService autorEditoraService;

	public AutorController(AutorService aAutorService, AutorEditoraService aAutorEditoraService) {
		autorService = aAutorService;
		autorEditoraService = aAutorEditoraService;
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
		if (autorService.addAutor(aAutor)) {
			sra.setAsSuccess("Autor adicionado com sucesso");
			sra.setAutores(autorService.getAllAutores());
			return ResponseEntity.status(HttpStatus.OK).body(sra);
		} else {
			sra.setMessage("Ocorreu um erro ao adicionar o autor");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sra);
		}
	}

	@PostMapping("/addAutor/{aAutor_id}/editora/{aEditora_id}")
	public String addAndarToCentroComercialByIds(@PathVariable String aAutor_id, @PathVariable String aEditora_id) {
		return autorEditoraService.addAutorToEditora(aAutor_id, aEditora_id);
	}

	@GetMapping("/getAllAutores")
	public List<Autor> getAllAutores() {
		return autorService.getAllAutores();
	}

}