package livrariaRq.utils;

import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Funcionario;

public class WrapperFuncionarioLivro {
	private Livro livro;
	private Funcionario funcionario;
	public WrapperFuncionarioLivro(Livro livro, Funcionario funcionario) {
		super();
		this.livro = livro;
		this.funcionario = funcionario;
	}
	public Livro getLivro() {
		return livro;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	

}
