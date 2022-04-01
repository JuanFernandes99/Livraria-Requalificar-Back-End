package livrariaRq.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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

	public List<Compra> getAllCompras() {
		List<Compra> compras = new ArrayList<>();
		compraRepo.findAll().forEach(compras::add);
		return compras;
	}

	public List<Compra> getValorCompraDesc() {
		List<Compra> compras = new ArrayList<>();
		compraRepo.findAll().forEach(compras::add);
		compras.sort(Comparator.comparing(Compra::getValorCompra).reversed());
		return compras;
	}

	public List<Compra> getValorCompraAsc() {
		List<Compra> compras = new ArrayList<>();
		compraRepo.findAll().forEach(compras::add);
		compras.sort(Comparator.comparing(Compra::getValorCompra));
		return compras;
	}

}
