package livrariaRq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.utilizador.Cliente;
import livrariaRq.model.utilizador.Funcionario;
import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.FuncionarioRepository;

@Service
public class AutenticacaoService {
	private final ClienteRepository clienteRepository;
	private final FuncionarioRepository funcionarioRepository;

	@Autowired
	public AutenticacaoService(ClienteRepository aClienteRepository, FuncionarioRepository aFuncionarioRepository) {
		clienteRepository = aClienteRepository;
		funcionarioRepository = aFuncionarioRepository;
	}

	public boolean autenticacaoFuncionario(Funcionario aFuncionario) {
		List<Funcionario> listaFuncionario = new ArrayList<>();

		funcionarioRepository.findAll().forEach(listaFuncionario::add);
		for (Funcionario funcionario : listaFuncionario) {
			if (funcionario.getPalavraPasse().equals(aFuncionario.getPalavraPasse())) {
				// enviar mensagem
				funcionario.setAtivo(true);
				funcionarioRepository.save(funcionario);
				return true;
			}
		}
		return false;
	}

}
