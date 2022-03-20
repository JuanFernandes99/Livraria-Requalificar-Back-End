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
			if (funcionario.getPalavraPasse().equals(aFuncionario.getPalavraPasse())
					&& funcionario.getNickName().equals(aFuncionario.getNickName())) {
				// enviar mensagem
				funcionario.setAtivo(true);
				funcionarioRepository.save(funcionario);
				return true;
			}
		}
		return false;
	}

	public boolean autenticacaoCliente(Cliente aCliente) {
		List<Cliente> listaCliente = new ArrayList<>();

		clienteRepository.findAll().forEach(listaCliente::add);
		for (Cliente cliente : listaCliente) {
			if (cliente.getPalavraPasse().equals(aCliente.getPalavraPasse())
					&& cliente.getEmail().equals(aCliente.getEmail())) {
				// enviar mensagem
				cliente.setAtivo(true);
				clienteRepository.save(cliente);
				return true;
			}
		}
		return false;
	}

}
