package livrariaRq.model.utilizador;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import livrariaRq.model.CarrinhoDeCompras;
import livrariaRq.model.Voucher;

@Entity
@Table(name = "Cliente")

public class Cliente {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String morada;
	private String palavraPasse;
	private String email;
	private boolean loginAtivo;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataNascimento;

	// private List<Compra> compras;

	@ManyToMany(mappedBy = "clientes")
	private List<Voucher> vouchers = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "carrinho_id")
	private CarrinhoDeCompras carrinho;

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

	public Long getId() {
		return id;
	}

	public boolean isLoginAtivo() {
		return loginAtivo;
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

	public void setLoginAtivo(boolean aLoginAtivo) {
		loginAtivo = aLoginAtivo;
	}

	public List<Voucher> getVouchers() {
		return vouchers;
	}

	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
	}

	public void setVouchers(List<Voucher> aVouchers) {
		vouchers = aVouchers;
	}

	public void setCarrinho(CarrinhoDeCompras aCarrinho) {
		carrinho = aCarrinho;
	}

	/*
	 * public void setCompras(List<Compra> aCompras) { compras = aCompras; }
	 * 
	 * public void setVouchers(List<Voucher> aVouchers) { vouchers = aVouchers; }
	 */
}
