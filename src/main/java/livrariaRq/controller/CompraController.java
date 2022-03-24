package livrariaRq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseCarrinho;
import livrariaRq.dto.SimpleResponseCompra;
import livrariaRq.model.CarrinhoDeCompras;
import livrariaRq.model.Compra;
import livrariaRq.service.CompraCarrinhoService;
import livrariaRq.service.CompraService;

@RestController
public class CompraController {
	private final CompraService compraService;
	private final CompraCarrinhoService compraCarrinhoService;

	@Autowired
	public CompraController(CompraService aCompraService, CompraCarrinhoService aCompraCarrinhoService) {

		compraService = aCompraService;
		compraCarrinhoService = aCompraCarrinhoService;
	}
	
	@PostMapping("/addCompra")
	public ResponseEntity<SimpleResponseCompra> addCarrinho(@RequestBody Compra aCompra) {
		SimpleResponseCompra src = new SimpleResponseCompra();

		if (compraService.addCompra(aCompra)) {
			src.setAsSuccess("Compra adicionado com sucesso");
			src.setCompras(compraService.getAllCompras());
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}

		else {
			src.setMessage(" Ocorreu um erro ao adicionar a compra  ");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

	}
	@PostMapping("/addCarrinho/{aCarrinho_id}/compra/{aCompra_id}")
	public String addCarrinhoToCompraByIds(@PathVariable String aCarrinho_id, @PathVariable String aCompra_id) {
		return compraCarrinhoService.adicionarCarrinhoToCompra(aCarrinho_id, aCompra_id);
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
