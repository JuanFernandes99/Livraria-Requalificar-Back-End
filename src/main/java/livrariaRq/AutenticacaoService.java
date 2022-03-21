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
	private final ClienteRepository clienteRepo;
	private final FuncionarioRepository funcionarioRepo;

	@Autowired
	public AutenticacaoService(ClienteRepository aClienteRepo, FuncionarioRepository aFuncionarioRepo) {
		clienteRepo = aClienteRepo;
		funcionarioRepo = aFuncionarioRepo;
	}

	public boolean autenticacaoFuncionario(Funcionario aFuncionario) {
		List<Funcionario> listaFuncionario = new ArrayList<>();

		funcionarioRepo.findAll().forEach(listaFuncionario::add);

		for (Funcionario funcionario : listaFuncionario) {

			if (funcionario.getPalavraPasse().equals(aFuncionario.getPalavraPasse())
					&& funcionario.getNickName().equals(aFuncionario.getNickName())) {
				funcionario.setLoginAtivo(true);
				funcionarioRepo.save(funcionario);
				return true;
			}
		}
		return false;
	}

	public boolean autenticacaoCliente(Cliente aCliente) {
		List<Cliente> listaCliente = new ArrayList<>();

		clienteRepo.findAll().forEach(listaCliente::add);

		for (Cliente cliente : listaCliente) {
			if (cliente.getPalavraPasse().equals(aCliente.getPalavraPasse())
					&& cliente.getEmail().equals(aCliente.getEmail())) {

				cliente.setLoginAtivo(true);
				clienteRepo.save(cliente);
				return true;
			}
		}
		return false;
	}

}
