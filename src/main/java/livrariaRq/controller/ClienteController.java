package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseCliente;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.service.ClienteService;

@RestController
public class ClienteController {

	private final ClienteService clienteService;

	@Autowired
	public ClienteController(ClienteService aClienteService) {
		clienteService = aClienteService;
	}

	@PostMapping("/addCliente")
	public ResponseEntity<SimpleResponseCliente> addCliente(@RequestBody Cliente aCliente) {
		SimpleResponseCliente src = new SimpleResponseCliente();
		
		if (aCliente.getNome() == null || aCliente.getNome().isBlank()) {
			src.setMessage("Nome inválido");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}
		if (aCliente.getEmail() == null || aCliente.getEmail().isBlank()) {
			src.setMessage("Email invÃ¡lido");
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
			src.setAsSuccess("Cliente criado com sucesso");
			src.setClientes(clienteService.getClientes());
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}

		else {
			src.setMessage(" Ocorreu um erro ");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

	}

	@GetMapping("/getClientes")
	public List<Cliente> getClientes() {
		return clienteService.getClientes();
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
