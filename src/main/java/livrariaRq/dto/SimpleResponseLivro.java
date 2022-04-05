package livrariaRq.dto;

import java.util.ArrayList;
import java.util.List;

import livrariaRq.model.livro.Livro;

public class SimpleResponseLivro extends SimpleResponse {
	List<Livro> livros;

	public SimpleResponseLivro() {
		livros = new ArrayList<Livro>();
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> aLivros) {
		livros = aLivros;
	}

}
