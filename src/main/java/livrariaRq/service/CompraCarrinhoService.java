package livrariaRq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.CarrinhoDeCompras;
import livrariaRq.model.Compra;
import livrariaRq.repository.CarrinhoRepository;
import livrariaRq.repository.CompraRepository;

@Service
public class CompraCarrinhoService {

	private CompraRepository compraRepo;
	private CarrinhoRepository carrinhoRepo;

	@Autowired
	public CompraCarrinhoService(CompraRepository aCompraRepo, CarrinhoRepository aCarrinhoRepo) {
		compraRepo = aCompraRepo;
		carrinhoRepo = aCarrinhoRepo;
	}
	
	public String adicionarCarrinhoToCompra(String aCarrinhoId, String aCompraId) {
		Optional<CarrinhoDeCompras> opcionalCarrinho = carrinhoRepo.findById(Long.parseLong(aCarrinhoId));
		Optional<Compra> opcionalCompra = compraRepo.findById(Long.parseLong(aCompraId));

		if (opcionalCarrinho.isPresent() && opcionalCompra.isPresent()) {

			Compra compraAux = opcionalCompra.get();

			CarrinhoDeCompras carrinhoAux = opcionalCarrinho.get();

			
			compraAux.setCarrinho(carrinhoAux);
			carrinhoAux.setCompra(compraAux);

			compraRepo.save(compraAux); // save pq estamos a adicionar novos dados
			carrinhoRepo.save(carrinhoAux);

			return "Sucesso ao adicionar o carrinho com id: " + carrinhoAux.getId() + "a compra: "
					+ compraAux.getId();

		}
		return "Insucesso ao adicionar o carrinho a compra";

	}
	
	public double valorTotal(String aCarrinhoId)
	{
		Optional<CarrinhoDeCompras> opcionalCarrinho = carrinhoRepo.findById(Long.parseLong(aCarrinhoId));
		if (opcionalCarrinho.isPresent()) {
			CarrinhoDeCompras carrinhoAux = opcionalCarrinho.get();
			carrinhoAux.getValorTotalPagar();
		}
		return 0;
		
	}
	

}
