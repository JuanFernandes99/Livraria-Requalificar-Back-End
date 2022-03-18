package livrariaRq.repository;

import org.springframework.data.repository.CrudRepository;

import livrariaRq.model.utilizador.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
