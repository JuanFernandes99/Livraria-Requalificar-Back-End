package livrariaRq.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import livrariaRq.model.utilizador.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

}
