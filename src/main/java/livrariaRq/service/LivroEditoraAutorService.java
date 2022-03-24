package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Autor;
import livrariaRq.model.livro.Editora;
import livrariaRq.model.livro.Livro;
import livrariaRq.repository.AutorRepository;
import livrariaRq.repository.EditoraRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class LivroEditoraAutorService {

	private final LivroRepository livroRepo;
	private final EditoraRepository editoraRepo;
	private final AutorRepository autorRepo;

	@Autowired
	public LivroEditoraAutorService(LivroRepository aLivroRepo, EditoraRepository aEditoraRepo,
			AutorRepository aAutorRepo) {
		livroRepo = aLivroRepo;
		editoraRepo = aEditoraRepo;
		autorRepo = aAutorRepo;
	}

	public boolean addLivro(Livro aLivro) {

		Optional<Editora> editora = editoraRepo.findById(aLivro.getEditora().getId());
		Editora editoraAux = editora.get();

		List<Autor> autoresLivro = new ArrayList<>();

		for (Autor autor : aLivro.getAutores()) {
			Optional<Autor> autorLivro = autorRepo.findById(autor.getId());
			autoresLivro.add(autorLivro.get());
		}

		aLivro.setAutores(autoresLivro);
		aLivro.setEditora(editoraAux);

		if (aLivro.getId() == null) {
			livroRepo.save(aLivro);

			return true;
		}
		return false;
	}
}