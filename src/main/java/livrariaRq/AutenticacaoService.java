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

				return true;
			}
		}
		return false;
	}

	public Funcionario funcionarioAutenticado(Funcionario aFuncionario) {
		List<Funcionario> listaFuncionario = new ArrayList<>();

		funcionarioRepo.findAll().forEach(listaFuncionario::add);

		for (Funcionario funcionario : listaFuncionario) {
			if (funcionario.getNickName().equals(aFuncionario.getNickName())
					&& funcionario.getPalavraPasse().equals(aFuncionario.getPalavraPasse())) {

				return funcionario;
			}
		}
		return null;

	}

	public boolean autenticacaoCliente(Cliente aCliente) {
		List<Cliente> listaCliente = new ArrayList<>();

		clienteRepo.findAll().forEach(listaCliente::add);

		for (Cliente cliente : listaCliente) {
			if (cliente.getPalavraPasse().equals(aCliente.getPalavraPasse())
					&& cliente.getEmail().equals(aCliente.getEmail())) {

				return true;
			}
		}
		return false;
	}

	public Cliente clienteAutenticado(Cliente aCliente) {
		List<Cliente> listaCliente = new ArrayList<>();

		clienteRepo.findAll().forEach(listaCliente::add);

		for (Cliente cliente : listaCliente) {
			if (cliente.getPalavraPasse().equals(aCliente.getPalavraPasse())
					&& cliente.getEmail().equals(aCliente.getEmail())) {

				return cliente;
			}
		}
		return null;

	}

	public boolean validacaoEmailCliente(Cliente aCliente) {
		List<Cliente> listaCliente = new ArrayList<>();

		clienteRepo.findAll().forEach(listaCliente::add);

		for (Cliente cliente : listaCliente) {
			if (cliente.getEmail().equals(aCliente.getEmail())) {

				return true;
			}
		}
		return false;
	}

	public boolean validacaoPalavraPasseCliente(Cliente aCliente) {
		List<Cliente> listaCliente = new ArrayList<>();

		clienteRepo.findAll().forEach(listaCliente::add);

		for (Cliente cliente : listaCliente) {
			if (cliente.getPalavraPasse().equals(aCliente.getPalavraPasse())) {

				return true;
			}
		}
		return false;
	}

	public boolean validacaoPalavraPasseFuncionario(Funcionario aFuncionario) {
		List<Funcionario> listaFuncionario = new ArrayList<>();

		funcionarioRepo.findAll().forEach(listaFuncionario::add);

		for (Funcionario funcionario : listaFuncionario) {
			if (funcionario.getPalavraPasse().equals(aFuncionario.getPalavraPasse())) {

				return true;
			}
		}
		return false;
	}

	public boolean validacaoNickNameFuncionario(Funcionario aFuncionario) {
		List<Funcionario> listaFuncionario = new ArrayList<>();

		funcionarioRepo.findAll().forEach(listaFuncionario::add);

		for (Funcionario funcionario : listaFuncionario) {
			if (funcionario.getNickName().equals(aFuncionario.getNickName())) {

				return true;
			}
		}
		return false;
	}
}
