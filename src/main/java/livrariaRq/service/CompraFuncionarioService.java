package livrariaRq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.repository.CompraRepository;
import livrariaRq.repository.FuncionarioRepository;

@Service
public class CompraFuncionarioService {
	private final CompraRepository compraRepository;
	private final FuncionarioRepository funcionarioRepository;

	@Autowired
	public CompraFuncionarioService(CompraRepository aCompraRepository, FuncionarioRepository aFuncionarioRepository) {
		compraRepository = aCompraRepository;
		funcionarioRepository = aFuncionarioRepository;

	}

}