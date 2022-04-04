package livrariaRq.dto;

import livrariaRq.model.livro.Livro;

public class SimpleResponseLivro extends SimpleResponse {
	private Livro livro;

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro aLivro) {
		livro = aLivro;
	}

}
