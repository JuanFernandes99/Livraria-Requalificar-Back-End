package livrariaRq.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.CarrinhoDeCompras;
import livrariaRq.model.Compra;
import livrariaRq.model.livro.Autor;
import livrariaRq.model.livro.Editora;
import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.repository.AutorRepository;
import livrariaRq.repository.EditoraRepository;
import livrariaRq.repository.FuncionarioRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class FuncionarioLivroService {

	private final LivroRepository livroRepo;
	private final FuncionarioRepository funcionarioRepo;
	private final EditoraRepository editoraRepo;
	private final AutorRepository autorRepo;

	@Autowired
	public FuncionarioLivroService(LivroRepository aLivroRepo, FuncionarioRepository aFuncionarioRepo,
			EditoraRepository editoraRepo, AutorRepository autorRepo) {
		livroRepo = aLivroRepo;
		funcionarioRepo = aFuncionarioRepo;
		this.editoraRepo = editoraRepo;
		this.autorRepo = autorRepo;
	}

	public boolean addLivro(Funcionario aFuncionario, Livro aLivro) {

		Optional<Editora> editora = editoraRepo.findById(aLivro.getEditora().getId());
		Editora editoraAux = editora.get();

		List<Autor> autoresLivro = new ArrayList<>();

		for (Autor autor : aLivro.getAutores()) {
			Optional<Autor> autorLivro = autorRepo.findById(autor.getId());
			autoresLivro.add(autorLivro.get());
		}
		
		
		aLivro.setAutores(autoresLivro);
		aLivro.setEditora(editoraAux);
		
		if (aLivro.getId() == null && autenticacaoFuncionario(aFuncionario)) {
			livroRepo.save(aLivro);
			
			return true;
		}
		return false;
	}

// verificar se está correto
	public boolean autenticacaoFuncionario(Funcionario aFuncionario) {
		Funcionario funcionario = funcionarioRepo.findById(aFuncionario.getId()).get();
		if (aFuncionario.getId() != null && funcionario.isLoginAtivo()) {
			return true;
		}
		return false;

	}

}