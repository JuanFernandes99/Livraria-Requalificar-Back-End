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

		if (aLivro.getId() == null) {
			aLivro.setAutores(autoresLivro);
			aLivro.setEditora(editoraAux);
			livroRepo.save(aLivro);

			return true;
		}
		return false;
	}

	public boolean VerificarEditora(Livro aLivro) {

		Optional<Editora> editora = editoraRepo.findById(aLivro.getEditora().getId());

		if (aLivro.getEditora() == null || !editora.isPresent()) {
			return false;
		}
		return true;

	}

	public boolean autoresEditora(Livro aLivro) {
		Optional<Editora> editoraLivro = editoraRepo.findById(aLivro.getEditora().getId());
		Editora editora = editoraLivro.get();

		for (Autor autores : aLivro.getAutores()) {
			Optional<Autor> autorLivro = autorRepo.findById(autores.getId());
			Autor autor = autorLivro.get();
			if (autor.getEditora() != editora) {
				return false;
			}
		}
		return true;
	}

	public boolean VerificarAutor(Livro aLivro) {

		for (Autor autor : aLivro.getAutores()) {
			Optional<Autor> autorLivro = autorRepo.findById(autor.getId());

			if (!autorLivro.isPresent()) {
				return false;
			}
		}

		return true;
	}
}