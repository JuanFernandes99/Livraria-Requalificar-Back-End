package livrariaRq.model.livro;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private String nome;
	private String morada;

	@OneToMany(mappedBy = "editora") // autores presente na editora
	@JsonIgnore
	private List<Autor> autores = new ArrayList<>();

	@JsonIgnore
	@OneToMany(mappedBy = "editora") // livros presente na editora
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

	public Long getId() {
		return id;
	}

	public List<Livro> getLivros() {
		return livros;
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

	public void setLivros(List<Livro> aLivros) {
		livros = aLivros;
	}

}
