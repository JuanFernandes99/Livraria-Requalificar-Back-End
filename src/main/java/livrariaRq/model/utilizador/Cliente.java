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
@Table(name = "Cliente")

public class Cliente {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String morada;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataNascimento;

	private String palavraPasse;

	private String email;
	private boolean ativo;

//	private List<Compra> compras;
	// private List<Voucher> vouchers;

	public String getNome() {
		return nome;

	}

	public Date getDataNascimento() {

		return dataNascimento;
	}

	public String getPalavraPasse() {
		return palavraPasse;
	}

	public String getEmail() {
		return email;
	}

	public String getMorada() {
		return morada;
	}

	/*
	 * public List<Compra> getCompras() { return compras; }
	 * 
	 * public List<Voucher> getVouchers() { return vouchers; }
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

	public void setEmail(String aEmail) {
		email = aEmail;
	}

	public void setMorada(String aMorada) {
		morada = aMorada;
	}

	/*
	 * public void setCompras(List<Compra> aCompras) { compras = aCompras; }
	 * 
	 * public void setVouchers(List<Voucher> aVouchers) { vouchers = aVouchers; }
	 */
	public Long getId() {
		return id;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean aAtivo) {
		ativo = aAtivo;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", morada=" + morada + ", dataNascimento=" + dataNascimento
				+ ", palavraPasse=" + palavraPasse + ", email=" + email + ", ativo=" + ativo + "]";
	}

}
