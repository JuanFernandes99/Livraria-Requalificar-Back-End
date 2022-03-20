package livrariaRq.service;

import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.repository.FuncionarioRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class FuncionarioLivroService {

	private final LivroRepository livroRepo;
	private final FuncionarioRepository funcionarioRepo;

	public FuncionarioLivroService(LivroRepository aLivroRepo, FuncionarioRepository aFuncionarioRepo) {
		livroRepo = aLivroRepo;
		funcionarioRepo = aFuncionarioRepo;
	}
	
	public boolean addLivro(Funcionario aFuncionario , Livro aLivro) {
		Funcionario funcionario = funcionarioRepo.findById(aFuncionario.getId()).get();
		
		if (aLivro.getId() == null && funcionario.isAtivo()) {
			livroRepo.save(aLivro);
			return true;
		}
		return false;
	}
	
}