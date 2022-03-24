package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.Compra;
import livrariaRq.repository.CompraRepository;

@Service
public class CompraService {
	private final CompraRepository compraRepo;

	@Autowired
	public CompraService(CompraRepository aCompraRepo) {
		compraRepo = aCompraRepo;
	}

	public boolean addCompra(Compra aCompra) {
		if (aCompra.getId() == null) {
			compraRepo.save(aCompra);
			return true;
		}
		return false;
	}

	public List<Compra> getAllCompras() {
		List<Compra> compras = new ArrayList<>();
		compraRepo.findAll().forEach(compras::add);
		return compras;
	}

	public double valorTotal(String aCompraId) {
		Optional<Compra> compraOptional = compraRepo.findById(Long.parseLong(aCompraId));
		if (compraOptional.isPresent()) {
			Compra carrinhoAux = compraOptional.get();
			carrinhoAux.getValorCompra(); // valor total da compra
		}
		return 0;

	}

}
