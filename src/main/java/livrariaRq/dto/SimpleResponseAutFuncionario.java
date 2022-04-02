package livrariaRq.dto;

import livrariaRq.model.utilizador.Funcionario;

public class SimpleResponseAutFuncionario extends SimpleResponse {
	private Funcionario funcionario;


	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario aFuncionario) {
		funcionario = aFuncionario;
	}

}