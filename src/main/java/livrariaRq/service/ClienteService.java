package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;

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

}
