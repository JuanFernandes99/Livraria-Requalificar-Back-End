package livrariaRq.repository;

import org.springframework.data.repository.CrudRepository;

import livrariaRq.model.livro.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long> {

}
