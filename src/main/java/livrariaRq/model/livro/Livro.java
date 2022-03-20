package livrariaRq.model.livro;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Livro")
public class Livro {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String autor;
	private String titulo;

	// ver se é possivel através de anotações

	private String ISBN;  //String pq pode começar em 0
	private double preco;
	private int quantidadeStock;
	private String editora;
	private int numeroPaginas;

	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date dataLancamento = new Date();
	private String sinopse;
	private String edicao;

	// ainda nao está feito a imagem
	private String imagem;

	// private List<Autor> autores;

	public Long getId() {
		return id;
	}

	public String getAutor() {
		return autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getiSBN() {
		return ISBN;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidadeStock() {
		return quantidadeStock;
	}

	public String getEditora() {
		return editora;
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

//	public List<Autor> getAutores() {
//		return autores;
//	}

	public void setAutor(String aAutor) {
		autor = aAutor;
	}

	public void setTitulo(String aTitulo) {
		titulo = aTitulo;
	}

	public void setiSBN(String aISBN) {
		ISBN = aISBN;
	}

	public void setPreco(double aPreco) {
		preco = aPreco;
	}

	public void setQuantidadeStock(int aQuantidadeStock) {
		quantidadeStock = aQuantidadeStock;
	}

	public void setEditora(String aEditora) {
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

//	public void setAutores(List<Autor> aAutores) {
//		autores = aAutores;
//	}

}
