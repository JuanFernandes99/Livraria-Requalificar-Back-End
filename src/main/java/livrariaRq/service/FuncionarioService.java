package livrariaRq.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public boolean addFuncionario(Funcionario aFuncionario) throws NoSuchAlgorithmException {

		if (aFuncionario.getId() == null) {

			aFuncionario.setNickName(aFuncionario.getNome() + String.valueOf(counter)); // Geração do nickname
			aFuncionario.setPalavraPasse(Funcionario.encriptPassword(aFuncionario.getPalavraPasse()));
			funcionarioRepo.save(aFuncionario);
			counter++;
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
