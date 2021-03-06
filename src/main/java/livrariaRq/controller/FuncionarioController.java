package livrariaRq.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.AutenticacaoService;
import livrariaRq.dto.SimpleResponse;
import livrariaRq.dto.SimpleResponseAutFuncionario;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.service.FuncionarioService;

@CrossOrigin
@RestController
public class FuncionarioController {

	private final FuncionarioService funcionarioService;
	private final AutenticacaoService autenticacaoService;

	@Autowired
	public FuncionarioController(FuncionarioService aFuncionarioService, AutenticacaoService aAutenticacaoService) {
		funcionarioService = aFuncionarioService;
		autenticacaoService = aAutenticacaoService;
	}

	@CrossOrigin
	@PostMapping(path = "/addFuncionario")
	public ResponseEntity<SimpleResponse> addFuncionario(@RequestBody Funcionario aFuncionario) {
		SimpleResponseAutFuncionario srf = new SimpleResponseAutFuncionario();
		try {
			if (aFuncionario.getNome() == null || aFuncionario.getNome().isBlank()) {
				srf.setMessage("Nome invalido");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
			}

			if (aFuncionario.getDataNascimento() == null) {
				srf.setMessage("Data de nascimento inválida, formato esperado: dd-MM-yyyy");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
			}

			if (aFuncionario.getPalavraPasse() == null || aFuncionario.getPalavraPasse().isBlank()) {
				srf.setMessage("Tem de inserir uma Palavra-Passe");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
			}

			if (funcionarioService.addFuncionario(aFuncionario)) {
				srf.setAsSuccess("Funcionario adicionado com sucesso");
				srf.setFuncionario(aFuncionario);
				return ResponseEntity.status(HttpStatus.OK).body(srf);
			}
		} catch (Exception e) {
			srf.setMessage("Data de nascimento inválida, formato esperado: dd-MM-yyyy");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
		}
		return null;

	}

	@CrossOrigin
	@PostMapping(path = "/autenticacaoFuncionario")
	public ResponseEntity<SimpleResponse> autenticacaoFuncionario(@RequestBody Funcionario aFuncionario)
			throws NoSuchAlgorithmException {
		SimpleResponseAutFuncionario srf = new SimpleResponseAutFuncionario();

		if (!autenticacaoService.validacaoNickNameFuncionario(aFuncionario)) {
			srf.setMessage("Nick Name invalido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);

		}

		if (!autenticacaoService.validacaoPalavraPasseFuncionario(aFuncionario)) {
			srf.setMessage("PalavraPasse invalida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);

		}
		if (autenticacaoService.autenticacaoFuncionario(aFuncionario)) {
			srf.setAsSuccess("Funcionario autenticado com sucesso");
			srf.setFuncionario(autenticacaoService.funcionarioAutenticado(aFuncionario));
			return ResponseEntity.status(HttpStatus.OK).body(srf);
		}
		srf.setMessage("Ocorreu um erro de autenticação");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srf);
	}

	@CrossOrigin
	@GetMapping("/getAllFuncionarios")
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioService.getAllFuncionarios();
	}

}
