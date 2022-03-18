package livrariaRq.model.utilizador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import livrariaRq.model.Compra;

@Entity
@Table(name = "Funcionario")
public class Funcionario {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private Date dataNascimento;
	private String palavraPasse;
	private String nickName;

	private List<Compra> compras = new ArrayList<Compra>();

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}


	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getPalavraPasse() {
		return palavraPasse;
	}

	public String getNickName() {
		return nickName;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setNome(String aNome) {
		nome = aNome;
	}

	public void setDataNascimento(Date aDataNascimento) {
		dataNascimento = aDataNascimento;
	}

	public void setPalavraPasse(String aPalavraPasse) {
		palavraPasse = aPalavraPasse;
	}

	public void setNickName(String aNickName) {
		nickName = aNickName;
	}

	public void setCompras(List<Compra> aCompras) {
		compras = aCompras;
	}

}
