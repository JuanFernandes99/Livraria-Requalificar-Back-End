package livrariaRq.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.CarrinhoDeCompras;
import livrariaRq.model.Compra;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.CarrinhoRepository;
import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.CompraRepository;

@Service
public class CompraCarrinhoService {

	private CompraRepository compraRepo;
	private CarrinhoRepository carrinhoRepo;
	private ClienteRepository clienteRepo;

	@Autowired
	public CompraCarrinhoService(CompraRepository aCompraRepo, CarrinhoRepository aCarrinhoRepo,
			ClienteRepository aClienteRepo) {
		compraRepo = aCompraRepo;
		carrinhoRepo = aCarrinhoRepo;
		clienteRepo = aClienteRepo;
	}

	public String adicionarCarrinhoToCompra(String aCarrinhoId, String aCompraId) {
		Optional<CarrinhoDeCompras> opcionalCarrinho = carrinhoRepo.findById(Long.parseLong(aCarrinhoId));
		Optional<Compra> opcionalCompra = compraRepo.findById(Long.parseLong(aCompraId));

		if (opcionalCarrinho.isPresent() && opcionalCompra.isPresent()) {

			Compra compraAux = opcionalCompra.get();

			CarrinhoDeCompras carrinhoAux = opcionalCarrinho.get();

			Optional<Cliente> opcionalCliente = clienteRepo.findById(carrinhoAux.getCliente().getId()); // o carrinho
																										// desse cliente

			Cliente clienteAux = opcionalCliente.get();

			compraAux.setCarrinho(carrinhoAux);
			// compraAux.setLivros(carrinhoAux.getLivros());
			compraAux.setCliente(clienteAux);
			compraAux.setValorCompra(carrinhoAux.getValorTotalPagar());
			clienteAux.adicionarCompra(compraAux);
			/*
			 * if(compraAux.getValorCompra() >= 50 || compraAux.getValorCompra() < 100) {
			 * carrinhoAux.getCliente().adicionarVoucher(null);; }
			 */
			compraRepo.save(compraAux); // save pq estamos a adicionar novos dados
			carrinhoRepo.save(carrinhoAux);
			clienteRepo.save(clienteAux);

			return "Sucesso ao adicionar o carrinho com id: " + carrinhoAux.getId() + "a compra: " + compraAux.getId();

		}
		return "Insucesso ao adicionar o carrinho a compra";

	}

	public double valorTotal(String aCarrinhoId) {
		Optional<CarrinhoDeCompras> opcionalCarrinho = carrinhoRepo.findById(Long.parseLong(aCarrinhoId));
		if (opcionalCarrinho.isPresent()) {
			CarrinhoDeCompras carrinhoAux = opcionalCarrinho.get();
			carrinhoAux.getValorTotalPagar();
		}
		return 0;

	}

}
