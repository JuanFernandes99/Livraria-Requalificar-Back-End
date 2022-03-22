package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Editora;
import livrariaRq.repository.EditoraRepository;

@Service
public class EditoraService {

	private final EditoraRepository editoraRepository;

	public EditoraService(EditoraRepository aEditoraRepository) {

		editoraRepository = aEditoraRepository;
	}

	public boolean addEditora(Editora aEditora) {
		if (aEditora.getId() == null) {
			editoraRepository.save(aEditora);
			return true;
		}
		return false;
	}

	public List<Editora> getAllEditoras() {
		List<Editora> editoras = new ArrayList<>();
		editoraRepository.findAll().forEach(editoras::add);
		return editoras;
	}

}