package livrariaRq.model.livro;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Autor {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataNascimento;

	@ManyToOne
	@JoinColumn(name = "Editora_id") // vai criar na tabela do autor , uma coluna com editora_id
	private Editora editora; // ligação com a Editora

	@ManyToMany(mappedBy = "autores") // aqui ligação com livros através da lista???
	private List<Livro> livros = new ArrayList<>();

	public void adicionarLivro(Livro aLivro) {

		livros.add(aLivro);

	}

	public void removerLivro(Livro aLivro) {

		livros.remove(aLivro);

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setId(Long aId) {
		id = aId;
	}

	public void setNome(String aNome) {
		nome = aNome;
	}

	public void setDataNascimento(Date aDataNascimento) {
		dataNascimento = aDataNascimento;
	}

	public void setEmail(String aEmail) {
		email = aEmail;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora aEditora) {
		editora = aEditora;
	}

	public void setLivros(List<Livro> aLivros) {
		livros = aLivros;
	}

}
