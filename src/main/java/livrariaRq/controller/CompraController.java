package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseCompra;
import livrariaRq.model.Compra;
import livrariaRq.service.ClienteLivroCompraService;
import livrariaRq.service.CompraService;

@CrossOrigin
@RestController
public class CompraController {
	private final CompraService compraService;
	private final ClienteLivroCompraService clienteLivroCompraService;

	@Autowired
	public CompraController(CompraService aCompraService, ClienteLivroCompraService aClienteCompraService) {

		compraService = aCompraService;
		clienteLivroCompraService = aClienteCompraService;
	}

	@CrossOrigin
	@PostMapping("/addCompra")
	public ResponseEntity<SimpleResponseCompra> addCompra(@RequestBody Compra aCompra) {
		SimpleResponseCompra src = new SimpleResponseCompra();

		if (aCompra.getCliente() == null) {
			src.setMessage("Deve inserir um cliente");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}
		if (!clienteLivroCompraService.VerificarCliente(aCompra)) {
			src.setMessage("O cliente nao existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

		if (aCompra.getLivros() == null || aCompra.getLivros().isEmpty()) {
			src.setMessage("Tem de inserir pelo menos um livro a compra");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

		if (!clienteLivroCompraService.VerificarLivro(aCompra)) {
			src.setMessage("O livro nao existe");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

		if (clienteLivroCompraService.adicionarCompra(aCompra)) {
			src.setAsSuccess("Compra adicionada com sucesso");
			src.setCompras(compraService.getAllCompras());
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}

		else {
			src.setMessage(" Ocorreu um erro ao adicionar a compra  ");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

	}

	@CrossOrigin
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

	@CrossOrigin
	@GetMapping("/getComprasCliente/{aId}")
	public List<Compra> getComprasCliente(@PathVariable String aId) {

		return clienteLivroCompraService.getComprasByClienteId(aId);
	}

}
