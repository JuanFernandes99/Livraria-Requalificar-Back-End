package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Autor;
import livrariaRq.model.livro.Editora;
import livrariaRq.repository.AutorRepository;
import livrariaRq.repository.EditoraRepository;

@Service
public class AutorService {
	private final AutorRepository autorRepo;
	private final EditoraRepository editoraRepo;

	@Autowired
	public AutorService(AutorRepository aAutorRepo, EditoraRepository editoraRepo) {
		autorRepo = aAutorRepo;
		this.editoraRepo = editoraRepo;
	}

	// falta o update , e ver quem regista ao autor.

	public boolean addAutor(Autor aAutor) {
		Optional<Editora> editora = editoraRepo.findById(aAutor.getEditora().getId());
		Editora editoraAux = editora.get();

		aAutor.setEditora(editoraAux);
		if (aAutor.getId() == null) {
			autorRepo.save(aAutor);
			return true;
		}
		return false;
	}

	public List<Autor> getAllAutores() {

		List<Autor> autores = new ArrayList<>();
		autorRepo.findAll().forEach(autores::add);
		return autores;
	}

}
