package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.repository.LivroRepository;

@Service
public class LivroService {

	private final LivroRepository livroRepository;

	@Autowired
	public LivroService(LivroRepository aLivroRepository) {
		livroRepository = aLivroRepository;
	}

	public boolean addLivro(Livro aLivro, Funcionario aFuncionario) {

		if (aLivro.getId() == null && aFuncionario.isAtivo()) {
			livroRepository.save(aLivro);
			return true;
		}
		return false;
	}

	public List<Livro> getLivros() {
		List<Livro> livros = new ArrayList<>();
		livroRepository.findAll().forEach(livros::add);
		return livros;
	}

	public boolean checkIsbnLength(Livro aLivro) {

		if (aLivro.getiSBN().length() != 10) {
			return false;
		}

		return true;
	}

	public boolean checkIsbnValidator(Livro aLivro) {
		//0201530821 ISBN valido para testar
		
		int sum = 0;
		for (int i = 0; i < aLivro.getiSBN().length(); i++) {
			int digit = aLivro.getiSBN().charAt(i);
			sum += (digit * (10 - i));
		}

		if ((sum % 11) != 0) {
			return false;
		} else {
			return true;
		}

	}

	public boolean uptadeLivro(Livro aLivro) {
		if (aLivro.getId() == null || livroRepository.findById(aLivro.getId()).isEmpty()) {
			return false;
		}
		Livro livroUpdate = livroRepository.findById(aLivro.getId()).get();

		if (aLivro.getAutor() != null || !aLivro.getAutor().isBlank()) {
			livroUpdate.setAutor(aLivro.getAutor());

		}
		if (aLivro.getTitulo() != null || !aLivro.getTitulo().isBlank()) {
			livroUpdate.setTitulo(aLivro.getTitulo());
		}
		if (aLivro.getPreco() <= 0) {
			livroUpdate.setPreco(aLivro.getPreco());

		}
		if (aLivro.getQuantidadeStock() <= 0) {
			livroUpdate.setQuantidadeStock(aLivro.getQuantidadeStock());
		}
		if (aLivro.getEditora() != null || !aLivro.getEditora().isBlank()) {
			livroUpdate.setEditora(aLivro.getEditora());
		}
		if (aLivro.getNumeroPaginas() <= 0) {
			livroUpdate.setNumeroPaginas(aLivro.getNumeroPaginas());
		}

		if (aLivro.getSinopse() != null || !aLivro.getSinopse().isBlank()) {
			livroUpdate.setSinopse(aLivro.getSinopse());
		}
		if (aLivro.getEdicao() != null || !aLivro.getEdicao().isBlank()) {
			livroUpdate.setEdicao(aLivro.getEdicao());
		}
		if (aLivro.getDataLancamento() != null) {
			livroUpdate.setDataLancamento(aLivro.getDataLancamento());
		}
		livroRepository.save(livroUpdate);
		return true;
	}

}
