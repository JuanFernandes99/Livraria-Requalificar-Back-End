package livrariaRq.model.utilizador;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import livrariaRq.model.Compra;

public class Funcionario {
	private String nome;
	private Date dataNascimento;
	private String palavraPasse;
	private String nickName;

	private List<Compra> compras = new ArrayList<Compra>();

	public String getNome() {
		return nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public String getPalavraPasse() {
		return palavraPasse;
	}

	public String getNickName() {
		return nickName;
	}

	public List<Compra> getCompras() {
		return compras;
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

	public void setNickName(String aNickName) {
		nickName = aNickName;
	}

	public void setCompras(List<Compra> aCompras) {
		compras = aCompras;
	}

}
