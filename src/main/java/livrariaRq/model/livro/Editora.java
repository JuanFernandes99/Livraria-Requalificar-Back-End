package livrariaRq.model.livro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private String nome;
	private String morada;

	@OneToMany(mappedBy = "autoresEditora") // autores presente na editora
	private List<Autor> autores = new ArrayList<>();

	@OneToMany(mappedBy = "livrosEditora")
	private List<Livro> livros = new ArrayList<>();

	public void adicionarAutor(Autor aAutor) {

		autores.add(aAutor);

	}

	public void removerAutor(Autor aAutor) {

		autores.remove(aAutor);

	}

	public void adicionarLivro(Livro aLivro) {

		livros.add(aLivro);

	}

	public void removerLivro(Livro aLivro) {

		livros.remove(aLivro);

	}

	public String getNome() {
		return nome;
	}

	public String getMorada() {
		return morada;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setNome(String aNome) {
		nome = aNome;
	}

	public void setMorada(String aMorada) {
		morada = aMorada;
	}

	public void setAutores(List<Autor> aAutores) {
		autores = aAutores;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> aLivros) {
		livros = aLivros;
	}

	public Long getId() {
		return id;
	}

}
