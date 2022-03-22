package livrariaRq.service;

import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Editora;
import livrariaRq.model.livro.Livro;
import livrariaRq.repository.EditoraRepository;

@Service
public class EditoraService {

	private final EditoraRepository editoraRepo;

	public EditoraService(EditoraRepository aEditoraRepo) {

		editoraRepo = aEditoraRepo;
	}

	public boolean addEditora(Editora aEditora) {
		if (aEditora.getId() == null) {
			editoraRepo.save(aEditora);
			return true;
		}
		return false;
	}

	public List<Editora> getAllEditoras() {
		List<Editora> editoras = new ArrayList<>();
		editoraRepo.findAll().forEach(editoras::add);
		return editoras;
	}

}