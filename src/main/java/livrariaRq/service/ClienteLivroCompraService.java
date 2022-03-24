package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.Compra;
import livrariaRq.model.livro.Autor;
import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.CompraRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class ClienteLivroCompraService {

	private final CompraRepository compraRepo;
	private final ClienteRepository clienteRepo;
	private final LivroRepository livroRepo;

	@Autowired
	public ClienteLivroCompraService(CompraRepository aCompraRepo, ClienteRepository aClienteRepo,
			LivroRepository aLivroRepo) {
		compraRepo = aCompraRepo;
		clienteRepo = aClienteRepo;
		livroRepo = aLivroRepo;
	}

	public boolean adicionarCompra(Compra aCompra) {
		Optional<Cliente> opcionalCliente = clienteRepo.findById(aCompra.getCliente().getId());

		Cliente clienteAux = opcionalCliente.get();

		if (aCompra.getId() == null) {

			List<Livro> compraLivro = new ArrayList<>();

			for (Livro livro : aCompra.getLivros()) {
				Optional<Livro> livroCompra = livroRepo.findById(livro.getId());
				compraLivro.add(livroCompra.get());
			}
			aCompra.setLivros(compraLivro);
			clienteAux.adicionarCompra(aCompra);
			aCompra.setCliente(clienteAux);

			clienteRepo.save(clienteAux);
			compraRepo.save(aCompra);

			return true;
		} else {
			return false;
		}

	}

	public boolean VerificarCliente(Compra aCompra) {

		Optional<Cliente> opcionalCliente = clienteRepo.findById(aCompra.getCliente().getId());

		if (opcionalCliente.isEmpty() || aCompra.getCliente() == null) {
			return false;
		} else {
			return true;

		}
	}

	public boolean VerificarLivro(Compra aCompra) {

		for (Livro livro : aCompra.getLivros()) {
			Optional<Livro> livroCompra = livroRepo.findById(livro.getId());

			if (!livroCompra.isPresent()) {
				return false;
			}
		}

		return true;
	}

}