package livrariaRq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Autor;
import livrariaRq.model.livro.Editora;
import livrariaRq.repository.AutorRepository;
import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.EditoraRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class AutorEditoraService {
	private final AutorRepository autorRepo;
	private final EditoraRepository editoraRepo;

	@Autowired
	public AutorEditoraService(AutorRepository aAutorRepo, EditoraRepository aEditoraRepo) {
		autorRepo = aAutorRepo;
		editoraRepo = aEditoraRepo;
	}

	public String addAutorToEditora(String aAutorId, String aEditoraId) {
		Optional<Editora> opcionalEditora = editoraRepo.findById(Long.parseLong(aEditoraId));
		Optional<Autor> opcionalAutor = autorRepo.findById(Long.parseLong(aAutorId));

		if (opcionalEditora.isPresent() && opcionalAutor.isPresent()) {

			Editora editoraAux = opcionalEditora.get();

			Autor autorAux = opcionalAutor.get();

			editoraAux.adicionarAutor(autorAux);
			autorAux.setEditora(editoraAux);
			editoraRepo.save(editoraAux); // save pq estamos a adicionar novos dados
			autorRepo.save(autorAux);

			return "Sucesso ao adicionar o autor: " + autorAux.getNome() + " à editora: " + editoraAux.getNome();

		}

		return "Insucesso ao adicionar o autor à editora";
	}

}
