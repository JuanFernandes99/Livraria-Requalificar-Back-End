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

	public String getNome() {
		return nome;
	}

	public String getMorada() {
		return morada;
	}

//	public List<Autor> getAutores() {
//		return autores;
//	}

	public void setNome(String aNome) {
		nome = aNome;
	}

	public void setMorada(String aMorada) {
		morada = aMorada;
	}

//	public void setAutores(List<Autor> aAutores) {
//		autores = aAutores;
//	}

}
