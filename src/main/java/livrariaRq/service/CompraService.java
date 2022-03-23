package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.Compra;
import livrariaRq.repository.CompraRepository;

@Service
public class CompraService {
	private final CompraRepository compraRepository;

	@Autowired
	public CompraService(CompraRepository aCompraRepository) {
		compraRepository = aCompraRepository;
	}

	public List<Compra> getAllCompras() {
		List<Compra> compras = new ArrayList<>();
		compraRepository.findAll().forEach(compras::add);
		return compras;
	}

}
