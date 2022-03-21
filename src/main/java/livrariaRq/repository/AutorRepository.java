package livrariaRq.repository;

import org.springframework.data.repository.CrudRepository;

import livrariaRq.model.livro.Autor;

public interface AutorRepository extends CrudRepository<Autor, Long> {

}
