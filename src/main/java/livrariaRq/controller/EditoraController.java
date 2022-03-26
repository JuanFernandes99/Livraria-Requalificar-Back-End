package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseEditora;
import livrariaRq.model.livro.Editora;
import livrariaRq.service.EditoraService;
@CrossOrigin
@RestController
public class EditoraController {

	private final EditoraService editoraService;

	@Autowired
	public EditoraController(EditoraService aEditoraService) {
		editoraService = aEditoraService;
	}
	@CrossOrigin
	@PostMapping("/addEditora")
	public ResponseEntity<SimpleResponseEditora> addEditora(@RequestBody Editora aEditora) {
		SimpleResponseEditora sre = new SimpleResponseEditora();

		if (aEditora.getNome() == null || aEditora.getNome().isBlank()) {
			sre.setMessage("Tem de inserir um nome");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sre);
		}
		if (aEditora.getMorada() == null || aEditora.getMorada().isBlank()) {
			sre.setMessage("Tem de inserir uma morada");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sre);
		}
		if (editoraService.addEditora(aEditora)) {
			sre.setAsSuccess("Editora adicionada com sucesso");
			sre.setEditoras(editoraService.getAllEditoras());
			return ResponseEntity.status(HttpStatus.OK).body(sre);
		} else {
			sre.setMessage("Ocorreu um erro ao adicionar a editora");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sre);
		}
	}
	@CrossOrigin
	@GetMapping("/getAllEditoras")
	public List<Editora> getAllEditoras() {
		return editoraService.getAllEditoras();
	}

}
