package livrariaRq.dto;

import java.util.ArrayList;
import java.util.List;

import livrariaRq.model.livro.Autor;

public class SimpleResponseAutor extends SimpleResponse {
	List<Autor> autores;

	public SimpleResponseAutor() {
		autores = new ArrayList<Autor>();
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> aAutores) {
		autores = aAutores;
	}

}
