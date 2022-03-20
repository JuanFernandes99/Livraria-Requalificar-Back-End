package livrariaRq.model.utilizador;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Funcionario")
public class Funcionario {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataNascimento;

	private String palavraPasse;
	private String nickName;
	private boolean ativo;
	// private List<Compra> compras = new ArrayList<Compra>();

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

	/*
	 * public List<Compra> getCompras() { return compras; }
	 */

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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean aAtivo) {
		ativo = aAtivo;
	}

	/*
	 * public void setCompras(List<Compra> aCompras) { compras = aCompras; }
	 */

}
