package livrariaRq.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Cliente;

@Entity
@Table(name = "Carrinho")
public class CarrinhoDeCompras {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private double valorTotalPagar;

	@OneToOne(mappedBy = "carrinho")
	private Compra compra;

	@OneToOne(mappedBy = "carrinho")
	private Cliente cliente;

	@OneToMany(mappedBy = "carrinho")
	private List<Livro> livros;

	public void adicionarLivro(Livro aLivro) {
		livros.add(aLivro);
	}

	public void removeLivro(Livro aLivro) {
		livros.remove(aLivro);
	}
	
	public Long getId() {
		return id;
	}

	public Compra getCompra() {
		return compra;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setCompra(Compra aCompra) {
		compra = aCompra;
	}

	public void setLivros(List<Livro> aLivros) {
		livros = aLivros;
	}

	public double getValorTotalPagar() {
		return valorTotalPagar;
	}

	public void setValorTotalPagar(double aValorTotalPagar) {
		valorTotalPagar = aValorTotalPagar;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente aCliente) {
		cliente = aCliente;
	}

}
