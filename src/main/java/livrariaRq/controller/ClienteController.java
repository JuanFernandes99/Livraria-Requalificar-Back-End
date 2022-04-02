package livrariaRq.controller;

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

import livrariaRq.AutenticacaoService;
import livrariaRq.dto.SimpleResponse;
import livrariaRq.dto.SimpleResponseAutCliente;
import livrariaRq.dto.SimpleResponseCliente;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.service.ClienteService;

@CrossOrigin
@RestController
public class ClienteController {

	private final ClienteService clienteService;
	private final AutenticacaoService autenticacaoService;

	@Autowired
	public ClienteController(ClienteService aClienteService, AutenticacaoService aAutenticacaoService) {
		clienteService = aClienteService;
		autenticacaoService = aAutenticacaoService;
	}

	@CrossOrigin
	@PostMapping("/addCliente")
	public ResponseEntity<SimpleResponseCliente> addCliente(@RequestBody Cliente aCliente) {
		SimpleResponseCliente src = new SimpleResponseCliente();
		try {
			if (aCliente.getNome() == null || aCliente.getNome().isBlank()) {
				src.setMessage("Tem de inserir um nome");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
			}

			if (aCliente.getMorada() == null || aCliente.getMorada().isBlank()) {
				src.setMessage("Tem de inserir uma morada");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
			}
			if (aCliente.getEmail() == null || aCliente.getEmail().isBlank()) {
				src.setMessage("Tem de inserir um email");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
			}

			if (aCliente.getPalavraPasse() == null || aCliente.getPalavraPasse().isBlank()) {
				src.setMessage("Tem de inserir uma Palavra-Passe");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
			}

			if (aCliente.getDataNascimento() == null) {
				src.setMessage("Data de nascimento inválida, formato esperado: dd-MM-yyyy");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);

			}

			if (clienteService.addCliente(aCliente)) {
				src.setAsSuccess("Cliente adicionado com sucesso");
				src.setClientes(clienteService.getAllClientes());
				return ResponseEntity.status(HttpStatus.OK).body(src);
			}

		} catch (Exception e) {
			src.setMessage("Data de nascimento inválida, formato esperado: dd-MM-yyyy");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}
		return null;
	}

	@CrossOrigin
	@PostMapping(path = "/autenticacaoCliente")
	public ResponseEntity<SimpleResponse> autenticacaoCliente(@RequestBody Cliente aCliente) {
		SimpleResponseAutCliente src = new SimpleResponseAutCliente();

		if (!autenticacaoService.validacaoEmailCliente(aCliente)) {
			src.setMessage("Email invalido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);

		}

		if (!autenticacaoService.validacaoPalavraPasseCliente(aCliente)) {
			src.setMessage("PalavraPasse invalida");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);

		}

		if (autenticacaoService.autenticacaoCliente(aCliente)) {
			src.setAsSuccess("Cliente autenticado Com Sucesso");
			src.setCliente(autenticacaoService.clienteAutenticado(aCliente));
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}
		src.setMessage("Ocorreu um erro de autenticação");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
	}

	@CrossOrigin
	@GetMapping("/getClienteById/{aId}")
	public Cliente getClienteById(@PathVariable String aId) {
		return clienteService.getClienteById(aId).get();
	}

	@CrossOrigin
	@GetMapping("/getAllClientes")
	public ResponseEntity<SimpleResponseCliente> getAllClientes() {
		SimpleResponseCliente src = new SimpleResponseCliente();

		if (clienteService.getAllClientes().isEmpty()) {
			src.setMessage("Inexistência de clientes");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}
		src.setClientes(clienteService.getAllClientes());
		src.setAsSuccess("Lista de clientes existentes na livraria:");
		return ResponseEntity.status(HttpStatus.OK).body(src);
	}

	@CrossOrigin
	@PutMapping("/updateCliente")
	public ResponseEntity<SimpleResponseCliente> updateCliente(@RequestBody Cliente aCliente) {
		SimpleResponseCliente src = new SimpleResponseCliente();

		if (aCliente.getMorada() == null || aCliente.getMorada().isBlank()) {
			src.setMessage("Tem de inserir uma morada");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}
		if (aCliente.getEmail() == null || aCliente.getEmail().isBlank()) {
			src.setMessage("Tem de inserir um email");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

		if (aCliente.getPalavraPasse() == null || aCliente.getPalavraPasse().isBlank()) {
			src.setMessage("Tem de inserir uma Palavra-Passe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}
		if (clienteService.updateCliente(aCliente)) {
			src.setAsSuccess("Cliente atualizado com sucesso");
			src.setClientes(clienteService.getAllClientes());
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
	}

}
