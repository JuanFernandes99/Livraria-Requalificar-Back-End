package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseCarrinho;
import livrariaRq.model.CarrinhoDeCompras;
import livrariaRq.service.CarrinhoService;
import livrariaRq.service.ClienteCompraService;

@RestController
public class CarrinhoController {

	private final CarrinhoService carrinhoService;
	private final ClienteCompraService clienteCarrinhoService;

	@Autowired
	public CarrinhoController(CarrinhoService aCarrinhoService, ClienteCompraService aClienteCarrinhoService) {

		carrinhoService = aCarrinhoService;
		clienteCarrinhoService = aClienteCarrinhoService;
	}

	@PostMapping("/addCarrinho")
	public ResponseEntity<SimpleResponseCarrinho> addCarrinho(@RequestBody CarrinhoDeCompras aCarrinho) {
		SimpleResponseCarrinho src = new SimpleResponseCarrinho();

		if (carrinhoService.addCarrinho(aCarrinho)) {
			src.setAsSuccess("Carrinho adicionado com sucesso");
			src.setCarrinhos(carrinhoService.getAllCarrinhos());
			return ResponseEntity.status(HttpStatus.OK).body(src);
		}

		else {
			src.setMessage(" Ocorreu um erro ao adicionar o carrinho  ");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(src);
		}

	}

	@PostMapping("/addCarrinho/{aCarrinho_id}/cliente/{aCliente_id}")
	public String addCarrinhoToClienteByIds(@PathVariable String aCarrinho_id, @PathVariable String aCliente_id) {
		return clienteCarrinhoService.adicionarCarrinhoToCliente(aCarrinho_id, aCliente_id);
	}

	@GetMapping("/getAllCarrinhos")
	public List<CarrinhoDeCompras> getAllCarrinhos() {
		return carrinhoService.getAllCarrinhos();
	}
}
