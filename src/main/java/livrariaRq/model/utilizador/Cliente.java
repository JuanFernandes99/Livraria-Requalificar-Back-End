package livrariaRq.model.utilizador;

import java.sql.Date;
import java.util.List;

import livrariaRq.model.Compra;
import livrariaRq.model.Voucher;

public class Cliente {

	private Long id;

	private String nome;
	private Date dataNascimento;
	private String palavraPasse;
	private String email;
	private List<Compra> compras;
	private List<Voucher> vouchers;

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

	public List<Compra> getCompras() {
		return compras;
	}

	public List<Voucher> getVouchers() {
		return vouchers;
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

	public void setEmail(String aEmail) {
		email = aEmail;
	}

	public void setCompras(List<Compra> aCompras) {
		compras = aCompras;
	}

	public void setVouchers(List<Voucher> aVouchers) {
		vouchers = aVouchers;
	}

	public Long getId() {
		return id;
	}

}
