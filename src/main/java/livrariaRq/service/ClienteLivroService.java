package livrariaRq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class ClienteLivroService {

	private final LivroRepository livroRepo;
	private final ClienteRepository clienteRepo;
@Autowired
	public ClienteLivroService(LivroRepository aLivroRepo, ClienteRepository aClienteRepo) {
		livroRepo = aLivroRepo;
		clienteRepo = aClienteRepo;
	}

//	Método para verificar se o cliente tem uma conta registada e pode aceder à lista de livros
	public boolean autenticacaoLivros(Cliente aCliente) {
		Cliente cliente = clienteRepo.findById(aCliente.getId()).get();
		if (aCliente.getId() != null && cliente.isLoginAtivo()) {
			return true;
		}
		return false;

	}

}
