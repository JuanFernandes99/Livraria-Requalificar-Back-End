package livrariaRq.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private int quantidadeVoucher;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "carrinho_id")
	private CarrinhoDeCompras carrinho;

	@OneToOne
	@JoinColumn(name = "voucher_id")
	private Voucher voucher;

	@OneToMany(mappedBy = "compra")
	private List<Livro> livros = new ArrayList<>();

	public void adicionarLivroCompra(Livro aLivro) {
		livros.add(aLivro);
	}

	public void removeLivroCompra(Livro aLivro) {
		livros.remove(aLivro);
	}

	public Long getId() {
		return id;
	}

	public double getValorCompra() {
		return valorCompra;
	}

	public int getQuantidadeVoucher() {
		return quantidadeVoucher;
	}

	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
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

	public void setQuantidadeVoucher(int aQuantidadeVoucher) {
		quantidadeVoucher = aQuantidadeVoucher;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> aLivros) {
		livros = aLivros;
	}

	public void setCarrinho(CarrinhoDeCompras aCarrinho) {
		carrinho = aCarrinho;
	}

	public void setVoucher(Voucher aVoucher) {
		voucher = aVoucher;
	}

}
