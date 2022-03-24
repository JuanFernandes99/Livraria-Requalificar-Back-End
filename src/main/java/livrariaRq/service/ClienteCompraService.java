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

	public boolean adicionarCompra(Cliente aCliente, Compra aCompra) {
		Optional<Cliente> opcionalCliente = clienteRepo.findById(aCliente.getId());

		if (opcionalCliente.isEmpty()) {
			return false;
		} else {

			Cliente clienteAux = opcionalCliente.get();

			clienteAux.adicionarCompra(aCompra);
			aCompra.setCliente(clienteAux);

			clienteRepo.save(clienteAux); // save pq estamos a adicionar novos dados
			compraRepo.save(aCompra);

			return true;
		}

	}
}