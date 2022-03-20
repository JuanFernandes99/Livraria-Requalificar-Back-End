package livrariaRq.model.livro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Autor {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private Date dataNascimento;
	private String email;

	@ManyToOne
	@JoinColumn(name = "Editora_id")
	@JsonIgnore
	private Editora autoresEditora; // ligação com a Editora

//	aqui vai ser aligação com a classe livro de ManyToMany com a lista livros
//	private List<Livro> livros;

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

//	public List<Livro> getLivros() {
//		return livros;
//	}

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

//	public void setLivros(List<Livro> aLivros) {
//		livros = aLivros;
//	}

}
