 package livrariaRq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.Compra;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.CompraRepository;

@Service
public class ClienteCompraService {

	private final CompraRepository compraRepo;
	private final ClienteRepository clienteRepo;

	@Autowired
	public ClienteCompraService(CompraRepository aCompraRepo, ClienteRepository aClienteRepo) {
		compraRepo = aCompraRepo;
		clienteRepo = aClienteRepo;
	}

	public String adicionarCarrinhoToCliente(String aCompraId, String aClienteId) {
		Optional<Compra> compraOptional = compraRepo.findById(Long.parseLong(aCompraId));
		Optional<Cliente> opcionalCliente = clienteRepo.findById(Long.parseLong(aClienteId));

		if (compraOptional.isPresent() && opcionalCliente.isPresent()) {

			Cliente clienteAux = opcionalCliente.get();

			CarrinhoDeCompras carrinhoAux = opcionalCarrinho.get();

			clienteAux.setCarrinho(carrinhoAux);
			carrinhoAux.setCliente(clienteAux);

			clienteRepo.save(clienteAux); // save pq estamos a adicionar novos dados
			carrinhoRepo.save(carrinhoAux);

			return "Sucesso ao adicionar o carrinho com id: " + carrinhoAux.getId() + "ao cliente: "
					+ clienteAux.getNome();

		}
		return "Insucesso ao adicionar o carrinho ao cliente";

	}
}