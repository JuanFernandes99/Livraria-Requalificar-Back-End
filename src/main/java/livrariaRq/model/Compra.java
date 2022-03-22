package livrariaRq.model;

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

import livrariaRq.model.livro.Livro;

@Entity
@Table(name = "Compra")
public class Compra {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private double valorCompra;
	private int quantidadeVoucher;
	private List<CarrinhoDeCompras> carrinho;
	private List<Voucher> vouchers;

	@OneToMany(mappedBy = "compra")
	@JsonIgnore
	private List<Livro> livros = new ArrayList<>();

	public void adicionarLivroCompra(Livro aLivro) {
		livros.add(aLivro);
	}

	public void adicionarVoucher(Voucher aVoucher) {
		vouchers.add(aVoucher);
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

	public List<CarrinhoDeCompras> getCarrinho() {
		return carrinho;
	}

	public List<Voucher> getVouchers() {
		return vouchers;
	}

	public void setValorCompra(double aValorCompra) {
		valorCompra = aValorCompra;
	}

	public void setQuantidadeVoucher(int aQuantidadeVoucher) {
		quantidadeVoucher = aQuantidadeVoucher;
	}

	public void setCarrinho(List<CarrinhoDeCompras> aCarrinho) {
		carrinho = aCarrinho;
	}

	public void setVouchers(List<Voucher> aVouchers) {
		vouchers = aVouchers;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> aLivros) {
		livros = aLivros;
	}

}
