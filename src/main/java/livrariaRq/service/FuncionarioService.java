package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.utilizador.Cliente;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	private final FuncionarioRepository funcionarioRepo;
	private static int counter = 1;

	@Autowired
	public FuncionarioService(FuncionarioRepository aFuncionarioRepo) {
		funcionarioRepo = aFuncionarioRepo;
	}

	public boolean addFuncionario(Funcionario aFuncionario) {

		if (aFuncionario.getId() == null || aFuncionario.getNickName() == null
				|| aFuncionario.getNickName().isBlank()) {

			aFuncionario.setNickName(aFuncionario.getNome() + String.valueOf(counter));
			funcionarioRepo.save(aFuncionario);
			counter++;
			return true;
		}
		return false;
	}

	// ver se é preciso
	public List<Funcionario> getAllFuncionarios() {
		List<Funcionario> listaFuncionarios = new ArrayList<>();

		funcionarioRepo.findAll().forEach(listaFuncionarios::add);
		return listaFuncionarios;

	}
}
