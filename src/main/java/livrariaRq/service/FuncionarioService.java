package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	private final FuncionarioRepository funcionarioRepo;

	@Autowired
	public FuncionarioService(FuncionarioRepository aFuncionarioRepo) {
		funcionarioRepo = aFuncionarioRepo;
	}

	public boolean addFuncionario(Funcionario aFuncionario) {

		if (aFuncionario.getId() == null || aFuncionario.getNickName() == null
				|| aFuncionario.getNickName().isBlank()) {

			funcionarioRepo.save(aFuncionario);
			return true;
		}
		return false;
	}

	public List<Funcionario> getAllFuncionarios() {
		List<Funcionario> listaFuncionarios = new ArrayList<>();

		funcionarioRepo.findAll().forEach(listaFuncionarios::add);
		return listaFuncionarios;

	}
}
