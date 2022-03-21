package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;

import livrariaRq.AutenticacaoService;
import livrariaRq.dto.SimpleResponse;
import livrariaRq.dto.SimpleResponseFuncionario;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.service.FuncionarioService;

@RestController
public class FuncionarioController {

	private final FuncionarioService funcionarioService;
	private final AutenticacaoService autenticacaoService;

	@Autowired
	public FuncionarioController(FuncionarioService aFuncionarioService, AutenticacaoService aAutenticacaoService) {
		funcionarioService = aFuncionarioService;
		autenticacaoService = aAutenticacaoService;
	}

	@PostMapping(path = "/addFuncionario")
	public ResponseEntity<SimpleResponse> addFuncionario(@RequestBody Funcionario aFuncionario) {
		SimpleResponseFuncionario srf = new SimpleResponseFuncionario();
		
		if (aFuncionario.getNome() == null || aFuncionario.getNome().isBlank()) {
			srf.setMessage("Nome invalido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
		}

		if (aFuncionario.getDataNascimento() == null) {
			srf.setMessage("Data de nascimento invalida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
		}

		if (aFuncionario.getPalavraPasse() == null || aFuncionario.getPalavraPasse().isBlank()) {
			srf.setMessage("Palavra-Passe invalida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
		}

		if (funcionarioService.addFuncionario(aFuncionario)) {
			srf.setAsSuccess("Funcionario adicionado com sucesso");
			srf.setFuncionarios(funcionarioService.getAllFuncionarios());
			return ResponseEntity.status(HttpStatus.OK).body(srf);

		} else {
			srf.setMessage("Ocorreu um erro ao adicionar o Funcionário");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
		}

	}

	@PostMapping(path = "/autenticacaoFuncionario")
	public ResponseEntity<SimpleResponse> autenticacaoFuncionario(@RequestBody Funcionario aFuncionario) {
		SimpleResponseFuncionario srf = new SimpleResponseFuncionario();

		if (autenticacaoService.autenticacaoFuncionario(aFuncionario)) {
			srf.setAsSuccess("Funcionario autenticado com sucesso");
			srf.setFuncionarios(funcionarioService.getAllFuncionarios());
			return ResponseEntity.status(HttpStatus.OK).body(srf);
		}
		srf.setMessage("Ocorreu um erro de autenticação");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
	}
	
	
	/* Em dúvida se é para implementar
	@GetMapping("/getAllFuncionarios")
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioService.getAllFuncionarios();
	}
*/


}
