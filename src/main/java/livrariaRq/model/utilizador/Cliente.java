package livrariaRq.model.utilizador;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE) // default (yyyy-MM-dd)
	private Date dataNascimento;

	private String palavraPasse;

	private String email;
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

}
