package livrariaRq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Autor;

import livrariaRq.model.livro.Livro;
import livrariaRq.repository.AutorRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class LivroAutorService {

	private final LivroRepository livroRepo;
	private final AutorRepository autorRepo;

	@Autowired
	public LivroAutorService(LivroRepository aLivroRepo, AutorRepository aAutorRepo) {
		livroRepo = aLivroRepo;
		autorRepo = aAutorRepo;
	}

	public String addLivroToAutor(String aLivroId, String aAutorId) {
		Optional<Livro> opcionalLivro = livroRepo.findById(Long.parseLong(aLivroId));
		Optional<Autor> opcionalAutor = autorRepo.findById(Long.parseLong(aAutorId));

		if (opcionalLivro.isPresent() && opcionalAutor.isPresent()) {

			Livro livroAux = opcionalLivro.get();

			Autor autorAux = opcionalAutor.get();

			livroAux.adicionarAutor(autorAux);
			autorAux.adicionarLivro(livroAux);
			livroAux.setEditora(autorAux.getEditora());
			livroAux.setAutor(autorAux.getNome());

			livroRepo.save(livroAux); // save pq estamos a adicionar novos dados
			autorRepo.save(autorAux);

			return "Sucesso ao adicionar o livro: " + livroAux.getTitulo() + " ao autor: " + autorAux.getNome();

		}

		return "Insucesso ao adicionar o livro ao autor";
	}

}
