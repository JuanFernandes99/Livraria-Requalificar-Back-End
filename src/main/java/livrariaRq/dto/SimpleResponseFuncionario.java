package livrariaRq.dto;

import java.util.ArrayList;
import java.util.List;

import livrariaRq.model.utilizador.Funcionario;

public class SimpleResponseFuncionario extends SimpleResponse {
	List<Funcionario> funcionarios;

	public SimpleResponseFuncionario() {
		funcionarios = new ArrayList<>();
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> aFuncionarios) {
		funcionarios = aFuncionarios;
	}

}
