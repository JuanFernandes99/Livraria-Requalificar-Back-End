package livrariaRq.service;

import static java.lang.Float.NaN;
import static java.lang.Long.parseLong;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.repository.LivroRepository;

@Service
public class LivroService {

	private final LivroRepository livroRepo;

	@Autowired
	public LivroService(LivroRepository aLivroRepo) {
		livroRepo = aLivroRepo;
	}

	public boolean addLivro(Livro aLivro, Funcionario aFuncionario) {

		if (aLivro.getId() == null && aFuncionario.isLoginAtivo()) {
			livroRepo.save(aLivro);
			return true;
		}
		return false;
	}

	public List<Livro> getAllLivros() {
		List<Livro> livros = new ArrayList<>();
		livroRepo.findAll().forEach(livros::add);
		return livros;
	}

	public List<Livro> getLivrosPrecoAsc() {
		List<Livro> livros = new ArrayList<>();
		livroRepo.findAll().forEach(livros::add);
		livros.sort(Comparator.comparing(Livro::getPreco));
		return livros;
	}

	public List<Livro> getLivrosPrecoDesc() {
		List<Livro> livros = new ArrayList<>();
		livroRepo.findAll().forEach(livros::add);
		livros.sort(Comparator.comparing(Livro::getPreco).reversed());
		return livros;
	}

	public List<Livro> getLivrosPorDataDesc() {
		List<Livro> livros = new ArrayList<>();
		livroRepo.findAll().forEach(livros::add);
		livros.sort(Comparator.comparing(Livro::getDataLancamento).reversed());
		return livros;
	}

	public List<Livro> getLivrosPorDataAsc() {
		List<Livro> livros = new ArrayList<>();
		livroRepo.findAll().forEach(livros::add);
		livros.sort(Comparator.comparing(Livro::getDataLancamento));
		return livros;
	}

	public Optional<Livro> getLivroById(String aId) {
		try {
			Long id_long = parseLong(aId);
			Optional<Livro> livroOpcional = livroRepo.findById(id_long);
			if (aId == null || id_long == NaN || livroOpcional.isEmpty()) {
				return null;
			}
			return livroOpcional;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean verificarTamanhoIsbn(Livro aLivro) {

		if (aLivro.getiSBN().length() != 10) {
			return false;
		}
		return true;
	}

	public boolean verificarIsbnExistente(Livro aLivro) {

		for (Livro livros : getAllLivros()) {
			if (aLivro.getiSBN().equals(livros.getiSBN())) {
				return false;
			}
		}
		return true;
	}

	/*
	 * public boolean verificarValidacaoIsbn(Livro aLivro) { // 0201530821 ISBN
	 * valido para testar //9721041440
	 * 
	 * int sum = 0; for (int i = 0; i < aLivro.getiSBN().length(); i++) { int digit
	 * = aLivro.getiSBN().charAt(i); sum += (digit * (10 - i)); }
	 * 
	 * if ((sum % 11) != 0) { return false; } else { return true; }
	 * 
	 * }
	 */
	public boolean updateLivro(Livro aLivro) {
		if (aLivro.getId() == null || livroRepo.findById(aLivro.getId()).isEmpty()) {
			return false;
		}

		Livro livroToUpdate = livroRepo.findById(aLivro.getId()).get();

		if (aLivro.getTitulo() != null || !aLivro.getTitulo().isBlank()) {
			livroToUpdate.setTitulo(aLivro.getTitulo());
		}

		if (aLivro.getPreco() <= 0) {
			livroToUpdate.setPreco(aLivro.getPreco());
		}

		if (aLivro.getQuantidadeStock() <= 0) {
			livroToUpdate.setQuantidadeStock(aLivro.getQuantidadeStock());
		}

		if (aLivro.getNumeroPaginas() <= 0) {
			livroToUpdate.setNumeroPaginas(aLivro.getNumeroPaginas());
		}

		if (aLivro.getSinopse() != null || !aLivro.getSinopse().isBlank()) {
			livroToUpdate.setSinopse(aLivro.getSinopse());
		}

		if (aLivro.getEdicao() != null || !aLivro.getEdicao().isBlank()) {
			livroToUpdate.setEdicao(aLivro.getEdicao());
		}

		if (aLivro.getDataLancamento() != null) {
			livroToUpdate.setDataLancamento(aLivro.getDataLancamento());
		}

		livroRepo.save(livroToUpdate);
		return true;
	}

}
