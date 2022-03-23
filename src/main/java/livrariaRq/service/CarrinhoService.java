package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.CarrinhoDeCompras;
import livrariaRq.model.livro.Autor;
import livrariaRq.repository.CarrinhoRepository;

@Service
public class CarrinhoService {
	private final CarrinhoRepository carrinhoRepo;
	
	@Autowired
	public CarrinhoService(CarrinhoRepository aCarrinhoRepo) {
		
		carrinhoRepo = aCarrinhoRepo;
	}
	
	public boolean addCarrinho(CarrinhoDeCompras aCarrinho) {

		if (aCarrinho.getId() == null) {
			carrinhoRepo.save(aCarrinho);
			return true;
		}
		return false;
	}
	
	public List<CarrinhoDeCompras> getAllCarrinhos() {

		List<CarrinhoDeCompras> carrinhos = new ArrayList<>();
		carrinhoRepo.findAll().forEach(carrinhos::add);
		return carrinhos;
	}
	
	
}
