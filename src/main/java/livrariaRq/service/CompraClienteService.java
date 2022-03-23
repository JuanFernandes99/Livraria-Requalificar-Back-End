package livrariaRq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.CompraRepository;

@Service
public class CompraClienteService {

	private CompraRepository compraRepository;
	private ClienteRepository clienteRepository;

	@Autowired
	public CompraClienteService(CompraRepository aCompraRepository, ClienteRepository aClienteRepository) {
		compraRepository = aCompraRepository;
		clienteRepository = aClienteRepository;
	}

}
