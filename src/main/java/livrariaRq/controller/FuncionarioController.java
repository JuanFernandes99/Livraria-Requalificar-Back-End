package livrariaRq.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponse;
import livrariaRq.dto.SimpleResponseFuncionario;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.service.FuncionarioService;

@RestController
public class FuncionarioController {

	private final FuncionarioService funcionarioService;

	public FuncionarioController(FuncionarioService aFuncionarioService) {
		funcionarioService = aFuncionarioService;
	}

	@PostMapping(path = "/addFuncionario")
	public ResponseEntity<SimpleResponse> addAndar(@RequestBody Funcionario aFuncionario) {
		SimpleResponseFuncionario sr = new SimpleResponseFuncionario();

		if (aFuncionario.getNome() == null || aFuncionario.getNome().isBlank()) {
			sr.setMessage("Nome Invalido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
		}

		if (aFuncionario.getDataNascimento() == null) {
			sr.setMessage("Data de nascimento invalida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
		}

		if (aFuncionario.getPalavraPasse() == null || aFuncionario.getPalavraPasse().isBlank()) {
			sr.setMessage("PalavraPasse invalida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
		}
		if (funcionarioService.addFuncionario(aFuncionario)) {
			sr.setAsSuccess("Funcionario Inserido Com Sucesso");
			sr.setFuncionarios(funcionarioService.getAllFuncionarios());
			return ResponseEntity.status(HttpStatus.OK).body(sr);

		} else {
			sr.setMessage("Erro a inserir o Funcionario");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
		}

	}

	@GetMapping("/getAllFuncionarios")
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioService.getAllFuncionarios();
	}

}
