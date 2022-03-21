package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Autor;
import livrariaRq.repository.AutorRepository;

@Service
public class AutorService {
	private final AutorRepository autorRepo;

	@Autowired
	public AutorService(AutorRepository aAutorRepo) {
		autorRepo = aAutorRepo;
	}
	
	// falta o update , e ver quem regista ao autor.
	
	public boolean addAutor(Autor aAutor) {

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
