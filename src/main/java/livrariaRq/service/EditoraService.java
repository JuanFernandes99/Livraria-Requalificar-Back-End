package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Editora;
import livrariaRq.repository.EditoraRepository;

@Service
public class EditoraService {

	private final EditoraRepository editoraRepo;

	@Autowired
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