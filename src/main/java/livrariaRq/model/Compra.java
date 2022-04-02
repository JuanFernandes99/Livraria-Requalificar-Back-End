package livrariaRq.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Cliente;

@Entity
@Table(name = "Compra")
public class Compra {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double valorCompra;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "voucher_id")
	private Voucher voucher;

	@ManyToMany
	@JoinTable(name = "Compra_Livro", joinColumns = { @JoinColumn(name = "compra_id") }, inverseJoinColumns = {
			@JoinColumn(name = "livro_id") })
	private List<Livro> livros = new ArrayList<>();

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> aLivros) {
		livros = aLivros;
	}

	public Long getId() {
		return id;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente aCliente) {
		cliente = aCliente;
	}

	public void setValorCompra(double aValorCompra) {
		valorCompra = aValorCompra;
	}

	public void setVoucher(Voucher aVoucher) {
		voucher = aVoucher;
	}

}
