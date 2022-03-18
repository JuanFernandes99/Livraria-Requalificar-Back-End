package livrariaRq.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ficha8.ficha8_resolucao.dto.SimpleResponse;
import ficha8.ficha8_resolucao.dto.SimpleResponseAndar;
import ficha8.ficha8_resolucao.model.Andar;
import livrariaRq.service.FuncionarioService;

@RestController
public class FuncionarioController {

	private final FuncionarioService funcionarioService;

	public FuncionarioController(FuncionarioService aFuncionarioService) {
		funcionarioService = aFuncionarioService;
	}
	
	@PostMapping(path = "/addFuncionario")
	public ResponseEntity<SimpleResponse> addAndar(@RequestBody Andar aAndar) {
		SimpleResponseAndar sr = new SimpleResponseAndar();

		if (aAndar.getNumeroAndar() != 0) {
			sr.setMessage("Numero de Andar Invalido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
		}

		if (aAndar.getNumeroMaxLojas() == 0) {
			sr.setMessage("numero maximo de lojas invalido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
		}

		if (andarService.addAndar(aAndar)) {
			sr.setAsSuccess("Andar Inserido Com Sucesso");
			sr.setAndares(andarService.getAllAndares());
			return ResponseEntity.status(HttpStatus.OK).body(sr);

		} else {
			sr.setAsError("Erro ao inserir o Andar");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sr);
		}

	}
	
	
}
