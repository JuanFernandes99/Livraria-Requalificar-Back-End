package livrariaRq.service;

import static java.lang.Float.NaN;
import static java.lang.Long.parseLong;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			try {
				aCliente.setPalavraPasse(Cliente.encriptPassword(aCliente.getPalavraPasse()));
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clienteRepo.save(aCliente);
			return true;
		}
		return false;
	}

	public boolean isValidEmailAddress(String aEmail) {
		String regx = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(regx);
		Matcher matcher = pattern.matcher(aEmail);
		if (matcher.matches()) {
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

	public boolean clienteExiste(Cliente aCliente) {
		List<Cliente> clientes = new ArrayList<>();
		clienteRepo.findAll().forEach(clientes::add);

		for (Cliente cliente : clientes) {
			if (cliente.getEmail().equals(aCliente.getEmail())) {
				return false;
			}
		}

		return true;

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
