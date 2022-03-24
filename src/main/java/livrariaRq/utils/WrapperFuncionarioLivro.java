package livrariaRq.utils;

import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Funcionario;

public class WrapperFuncionarioLivro {
	private Livro livro;
	private Funcionario funcionario;

	public WrapperFuncionarioLivro(Livro aLivro, Funcionario aFuncionario) {
		livro = aLivro;
		funcionario = aFuncionario;
	}

	public Livro getLivro() {
		return livro;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setLivro(Livro aLivro) {
		livro = aLivro;
	}

	public void setFuncionario(Funcionario aFuncionario) {
		funcionario = aFuncionario;
	}

}