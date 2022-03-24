package livrariaRq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseCompra;
import livrariaRq.model.Compra;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.service.ClienteCompraService;
import livrariaRq.service.CompraService;

@RestController
public class CompraController {
	private final CompraService compraService;
	private final ClienteCompraService clienteCompraService;

	@Autowired
	public CompraController(CompraService aCompraService, ClienteCompraService aClienteCompraService) {

		compraService = aCompraService;
		clienteCompraService = aClienteCompraService;
	}

	@PostMapping("/addCompra")
	public ResponseEntity<SimpleResponseCompra> addCompra(Compra aCompra) {
		SimpleResponseCompra src = new SimpleResponseCompra();

		if (!clienteCompraService.VerificarCliente(aCompra)) {
			src.setMessage("O cliente nao existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}
		if (aCompra.getCliente() == null) {
			src.setMessage("Deve inserir um cliente");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}


		if (aCompra.getLivros() == null || aCompra.getLivros().isEmpty()) {
			src.setMessage("Tem de inserir pelo menos um livro a compra");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

		if (clienteCompraService.adicionarCompra(aCompra)) {
			src.setAsSuccess("Compra adicionada com sucesso");
			src.setCompras(compraService.getAllCompras());
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}

		else {
			src.setMessage(" Ocorreu um erro ao adicionar a compra  ");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

	}

	@GetMapping("/getAllCompras")
	public ResponseEntity<SimpleResponseCompra> getAllCompras() {
		SimpleResponseCompra src = new SimpleResponseCompra();

		if (compraService.getAllCompras().isEmpty()) {
			src.setMessage("Inexistência de compras");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}
		src.setCompras(compraService.getAllCompras());
		src.setAsSuccess("Lista de todas as compras efetuadas na livraria:");
		return ResponseEntity.status(HttpStatus.OK).body(src);
	}

}
