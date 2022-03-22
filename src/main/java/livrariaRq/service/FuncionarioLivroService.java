package livrariaRq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.repository.FuncionarioRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class FuncionarioLivroService {

	private final LivroRepository livroRepo;
	private final FuncionarioRepository funcionarioRepo;

	@Autowired
	public FuncionarioLivroService(LivroRepository aLivroRepo, FuncionarioRepository aFuncionarioRepo) {
		livroRepo = aLivroRepo;
		funcionarioRepo = aFuncionarioRepo;
	}

	public boolean addLivro(Funcionario aFuncionario, Livro aLivro) {

		Funcionario funcionario = funcionarioRepo.findById(aFuncionario.getId()).get();

		if (aLivro.getId() == null && funcionario.isLoginAtivo()) {
			livroRepo.save(aLivro);
			return true;
		}
		return false;
	}

}
