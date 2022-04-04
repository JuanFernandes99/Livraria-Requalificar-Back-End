package livrariaRq.model.livro;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import livrariaRq.model.Compra;

@Entity
@Table(name = "Livro")
public class Livro {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String isbn; // String porque o valor pode começar em 0
	private String sinopse;
	private String edicao;
	private String imagem;
	private double preco;
	private int quantidadeStock;
	private int quantidadeComprada;
	private int numeroPaginas;

	private Date dataLancamento;

//	ligação entre editora e livro
	@ManyToOne
	@JoinColumn(name = "Editora_id")
	private Editora editora;

//	ligação entre autor e livro
	@ManyToMany
	@JoinTable(name = "Livro_Autor", joinColumns = { @JoinColumn(name = "livro_id") }, inverseJoinColumns = {
			@JoinColumn(name = "autor_id") })
	List<Autor> autores = new ArrayList<>();

	@ManyToMany(mappedBy = "livros") // aqui ligação com livros através da lista???
	@JsonIgnore
	List<Compra> compras = new ArrayList<>();

	public void adicionarAutor(Autor aAutor) {
		autores.add(aAutor);
	}

	public void removerAutor(Autor aAutor) {
		autores.remove(aAutor);
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidadeStock() {
		return quantidadeStock;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public String getSinopse() {
		return sinopse;
	}

	public String getEdicao() {
		return edicao;
	}

	public String getImagem() {
		return imagem;
	}

	public Editora getEditora() {
		return editora;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public String getIsbn() {
		return isbn;
	}

	public int getQuantidadeComprada() {
		return quantidadeComprada;
	}

	public void setTitulo(String aTitulo) {
		titulo = aTitulo;
	}

	public void setPreco(double aPreco) {
		preco = aPreco;
	}

	public void setQuantidadeStock(int aQuantidadeStock) {
		quantidadeStock = aQuantidadeStock;
	}

	public void setEditora(Editora aEditora) {
		editora = aEditora;
	}

	public void setNumeroPaginas(int aNumeroPaginas) {
		numeroPaginas = aNumeroPaginas;
	}

	public void setDataLancamento(Date aDataLancamento) {
		dataLancamento = aDataLancamento;
	}

	public void setSinopse(String aSinopse) {
		sinopse = aSinopse;
	}

	public void setEdicao(String aEdicao) {
		edicao = aEdicao;
	}

	public void setImagem(String aImagem) {
		imagem = aImagem;
	}

	public void setAutores(List<Autor> aAutores) {
		autores = aAutores;
	}

	public void setIsbn(String aIsbn) {
		isbn = aIsbn;
	}

	public void setCompras(List<Compra> aCompras) {
		compras = aCompras;
	}

	public void setQuantidadeComprada(int quantidadeComprada) {
		this.quantidadeComprada = quantidadeComprada;
	}

}
