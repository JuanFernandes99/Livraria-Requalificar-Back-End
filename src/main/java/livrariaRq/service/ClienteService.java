package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepo;

	@Autowired
	public ClienteService(ClienteRepository aClienteRepo) {
		clienteRepo = aClienteRepo;
	}

	public boolean addCliente(Cliente aCliente) {

		if (aCliente.getId() == null) {
			clienteRepo.save(aCliente);
			return true;
		}
		return false;
	}

	public List<Cliente> getAllClientes() {

		List<Cliente> clientes = new ArrayList<>();
		clienteRepo.findAll().forEach(clientes::add);
		return clientes;
	}

	public boolean updateCliente(Cliente aCliente) {
		if (aCliente.getId() == null || clienteRepo.findById(aCliente.getId()).isEmpty() || aCliente.getNome() != null
				|| aCliente.getDataNascimento() != null) {

			return false;
		}

		Cliente clienteToUpdate = clienteRepo.findById(aCliente.getId()).get();

		if (aCliente.getMorada() != null && !aCliente.getMorada().isBlank()) {
			clienteToUpdate.setMorada(aCliente.getMorada());
		}

		if (aCliente.getEmail() != null && !aCliente.getEmail().isBlank()) {
			clienteToUpdate.setEmail(aCliente.getEmail());
		}
		if (aCliente.getPalavraPasse() != null && !aCliente.getPalavraPasse().isBlank()) {
			clienteToUpdate.setPalavraPasse(aCliente.getPalavraPasse());
		}

		clienteRepo.save(clienteToUpdate);

		return true;
	}
}
