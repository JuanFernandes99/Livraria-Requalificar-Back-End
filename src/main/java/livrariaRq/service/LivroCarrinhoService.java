package livrariaRq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.CarrinhoDeCompras;
import livrariaRq.model.livro.Livro;
import livrariaRq.repository.CarrinhoRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class LivroCarrinhoService {
	private final LivroRepository livroRepo;
	private final CarrinhoRepository carrinhoRepo;

	@Autowired
	public LivroCarrinhoService(LivroRepository aLivroRepo, CarrinhoRepository aCarrinhoRepo) {
		livroRepo = aLivroRepo;
		carrinhoRepo = aCarrinhoRepo;
	}

	public String adicionarLivroToCarrinho(String aLivroId, String aCarrinhoId) {
		Optional<Livro> opcionalLivro = livroRepo.findById(Long.parseLong(aLivroId));
		Optional<CarrinhoDeCompras> opcionalCarrinho = carrinhoRepo.findById(Long.parseLong(aCarrinhoId));

		if (opcionalLivro.isPresent() && opcionalCarrinho.isPresent()) {

			Livro livroAux = opcionalLivro.get();

			CarrinhoDeCompras carrinhoAux = opcionalCarrinho.get();

			livroAux.setCarrinho(carrinhoAux);
			carrinhoAux.setLivros(livroAux);

			livroRepo.save(livroAux); // save pq estamos a adicionar novos dados
			carrinhoRepo.save(carrinhoAux);

			return "Sucesso ao adicionar o livro: " + livroAux.getTitulo() + "ao carrinho: " + carrinhoAux.getId();

		}
		return "Insucesso ao adicionar o carrinho ao cliente";

	}
}
