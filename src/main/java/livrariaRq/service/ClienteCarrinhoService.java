package livrariaRq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.CarrinhoDeCompras;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.CarrinhoRepository;
import livrariaRq.repository.ClienteRepository;

@Service
public class ClienteCarrinhoService {

	private final CarrinhoRepository carrinhoRepo;
	private final ClienteRepository clienteRepo;

	@Autowired
	public ClienteCarrinhoService(CarrinhoRepository aCarrinhoRepo, ClienteRepository aClienteRepo) {
		carrinhoRepo = aCarrinhoRepo;
		clienteRepo = aClienteRepo;
	}


	public String adicionarCarrinhoToCliente(String aCarrinhoId, String aClienteId) {
		Optional<CarrinhoDeCompras> opcionalCarrinho = carrinhoRepo.findById(Long.parseLong(aCarrinhoId));
		Optional<Cliente> opcionalCliente = clienteRepo.findById(Long.parseLong(aClienteId));

		if (opcionalCarrinho.isPresent() && opcionalCliente.isPresent()) {

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