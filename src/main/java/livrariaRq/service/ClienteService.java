package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

	@Autowired
	public ClienteService(ClienteRepository aClienteRepository) {
		clienteRepository = aClienteRepository;
	}

	public boolean addCliente(Cliente aCliente) {

		if (aCliente.getId() == null) {
			clienteRepository.save(aCliente);
			return true;
		}
		return false;
	}

	public List<Cliente> getClientes() {
		List<Cliente> clientes = new ArrayList<>();

		clienteRepository.findAll().forEach(clientes::add);

		return clientes;
	}

	public boolean updateCliente(Cliente aCliente) {
		if (aCliente.getId() == null || clienteRepository.findById(aCliente.getId()).isEmpty()
				|| aCliente.getNome() != null || aCliente.getDataNascimento() != null) {

			return false;
		}
		Cliente clienteUpdate = clienteRepository.findById(aCliente.getId()).get();

		if (aCliente.getMorada() != null && !aCliente.getMorada().isBlank()) {
			clienteUpdate.setMorada(aCliente.getMorada());
		}

		if (aCliente.getEmail() != null && !aCliente.getEmail().isBlank()) {
			clienteUpdate.setEmail(aCliente.getEmail());
		}
		if (aCliente.getPalavraPasse() != null && !aCliente.getPalavraPasse().isBlank()) {
			clienteUpdate.setPalavraPasse(aCliente.getPalavraPasse());
		}

		clienteRepository.save(clienteUpdate);

		return true;
	}

	public Optional<Cliente> getClienteOptional(Cliente aCliente) {
		return clienteRepository.findById(aCliente.getId());

	}

}
