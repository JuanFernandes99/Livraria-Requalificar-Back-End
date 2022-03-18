package livrariaRq.service;

import org.springframework.stereotype.Service;

import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	private final FuncionarioRepository funcionarioRepo;

	public FuncionarioService(FuncionarioRepository aFuncionarioRepo) {
		funcionarioRepo = aFuncionarioRepo;
	}

	public boolean addFuncionario(Funcionario aFuncionario) {

		if (aFuncionario.getId() == null) {

			funcionarioRepo.save(aFuncionario);

			return true;
		}
		return false;
	}
}
