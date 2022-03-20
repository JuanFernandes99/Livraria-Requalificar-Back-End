package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Livro;
import livrariaRq.repository.LivroRepository;

@Service
public class LivroService {

	private final LivroRepository livroRepository;

	@Autowired
	public LivroService(LivroRepository aLivroRepository) {
		livroRepository = aLivroRepository;
	}

	public boolean addLivro(Livro aLivro) {

		if (aLivro.getId() == null) {
			livroRepository.save(aLivro);
			return true;
		}
		return false;
	}

	public List<Livro> getLivros() {
		List<Livro> livros = new ArrayList<>();
		livroRepository.findAll().forEach(livros::add);
		return livros;
	}

}
