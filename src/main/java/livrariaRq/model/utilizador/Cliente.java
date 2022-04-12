package livrariaRq.model.utilizador;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import livrariaRq.model.Compra;
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
	private String type = "cliente";
	private Date dataNascimento;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private List<Compra> compras;

	@OneToMany(mappedBy = "cliente")
	private List<Voucher> vouchers = new ArrayList<>();

	public static String encriptPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		try {
			messageDigest.update(password.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new BigInteger(1, messageDigest.digest()).toString(16);
	}

	public void adicionarCompra(Compra aCompra) {
		compras.add(aCompra);
	}

	public void adicionarVoucher(Voucher aVoucher) {
		vouchers.add(aVoucher);
	}

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

	public List<Voucher> getVouchers() {
		return vouchers;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
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

	public void setMorada(String aMorada) {
		morada = aMorada;
	}

	public void setVouchers(List<Voucher> aVouchers) {
		vouchers = aVouchers;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
