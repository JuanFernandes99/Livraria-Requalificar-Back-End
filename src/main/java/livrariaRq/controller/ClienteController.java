package livrariaRq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.AutenticacaoService;
import livrariaRq.dto.SimpleResponse;
import livrariaRq.dto.SimpleResponseCliente;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.service.ClienteService;

@RestController
public class ClienteController {

	private final ClienteService clienteService;
	private final AutenticacaoService autenticacaoService;

	@Autowired
	public ClienteController(ClienteService aClienteService, AutenticacaoService aAutenticacaoService) {
		clienteService = aClienteService;
		autenticacaoService = aAutenticacaoService;
	}

	@PostMapping("/addCliente")
	public ResponseEntity<SimpleResponseCliente> addCliente(@RequestBody Cliente aCliente) {
		SimpleResponseCliente src = new SimpleResponseCliente();

		if (aCliente.getNome() == null || aCliente.getNome().isBlank()) {
			src.setMessage("Nome inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

		if (aCliente.getEmail() == null || aCliente.getEmail().isBlank()) {
			src.setMessage("Email inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

		if (aCliente.getPalavraPasse() == null || aCliente.getPalavraPasse().isBlank()) {
			src.setMessage("Palavra-Passe inválida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

		if (aCliente.getDataNascimento() == null) {
			src.setMessage("Data de nascimento inválida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);

		}

		if (clienteService.addCliente(aCliente)) {
			src.setAsSuccess("Cliente adicionado com sucesso");
			src.setClientes(clienteService.getClientes());
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}

		else {
			src.setMessage(" Ocorreu um erro ao adicionar o cliente  ");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

	}

	@PostMapping(path = "/autenticacaoCliente")
	public ResponseEntity<SimpleResponse> autenticacaoCliente(@RequestBody Cliente aCliente) {
		SimpleResponseCliente src = new SimpleResponseCliente();

		if (autenticacaoService.autenticacaoCliente(aCliente)) {
			src.setAsSuccess("Cliente autenticado Com Sucesso");
			src.setClientes(clienteService.getClientes());
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}
		src.setMessage("Ocorreu um erro de autenticação");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
	}

	@GetMapping("/getAllClientes")
	public ResponseEntity<SimpleResponseCliente> getAllClientes() {
		SimpleResponseCliente src = new SimpleResponseCliente();

		if (clienteService.getClientes().isEmpty()) {
			src.setMessage("Inexistência de clientes");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}
		src.setClientes(clienteService.getClientes());
		src.setAsSuccess("Lista de clientes existentes na livraria:");
		return ResponseEntity.status(HttpStatus.OK).body(src);
	}

	@PutMapping("/updateCliente")
	public ResponseEntity<SimpleResponseCliente> updateCliente(@RequestBody Cliente aCliente) {
		SimpleResponseCliente src = new SimpleResponseCliente();

		if (clienteService.updateCliente(aCliente)) {
			src.setAsSuccess("Cliente atualizado com sucesso");
			src.setClientes(clienteService.getClientes());
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
	}

}
