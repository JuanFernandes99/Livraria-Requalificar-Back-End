package livrariaRq.service;

import static java.lang.Long.parseLong;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Autor;
import livrariaRq.model.livro.Editora;
import livrariaRq.model.livro.Livro;
import livrariaRq.repository.AutorRepository;
import livrariaRq.repository.EditoraRepository;

@Service
public class AutorEditoraService {
	private final AutorRepository autorRepo;
	private final EditoraRepository editoraRepo;

	@Autowired
	public AutorEditoraService(AutorRepository aAutorRepo, EditoraRepository aEditoraRepo) {
		autorRepo = aAutorRepo;
		editoraRepo = aEditoraRepo;
	}


	public List<Livro> getLivrosPorEditora(String aEditora_id) {
		Long id_long = parseLong(aEditora_id);
		Optional<Editora> editoraOpcional = editoraRepo.findById(id_long);
		return editoraOpcional.get().getLivros();

	}

	public List<Livro> getLivrosPorAutor(String aAutor_id) {
		Long id_long = parseLong(aAutor_id);
		Optional<Autor> autorOpcional = autorRepo.findById(id_long);
		return autorOpcional.get().getLivros();

	}

}
