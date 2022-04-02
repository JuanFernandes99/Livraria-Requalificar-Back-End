package livrariaRq.service;

import static java.lang.Float.NaN;
import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public Optional<Cliente> getClienteById(String aId) {
		try {
			Long id_long = parseLong(aId);
			Optional<Cliente> clienteOpcional = clienteRepo.findById(id_long);
			if (aId == null || id_long == NaN || clienteOpcional.isEmpty()) {
				return null;
			}
			return clienteOpcional;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean updateCliente(Cliente aCliente) {
		if (aCliente.getId() == null || clienteRepo.findById(aCliente.getId()).isEmpty()) {

			return false;
		}

		Cliente clienteToUpdate = clienteRepo.findById(aCliente.getId()).get();
		clienteRepo.save(clienteToUpdate);

		return true;
	}
}
