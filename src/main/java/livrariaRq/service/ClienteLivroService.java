package livrariaRq.service;


import org.springframework.stereotype.Service;

import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class ClienteLivroService {

	private final LivroRepository livroRepo;
	private final ClienteRepository clienteRepo;

	public ClienteLivroService(LivroRepository aLivroRepo, ClienteRepository aClienteRepo) {
		livroRepo = aLivroRepo;
		clienteRepo = aClienteRepo;
	}

	public boolean autenticacaoLivros(Cliente aCliente) {
		Cliente cliente = clienteRepo.findById(aCliente.getId()).get();
		if (aCliente.getId() != null && cliente.isAtivo()) {
			return true;
		}
		return false;

	}

}
