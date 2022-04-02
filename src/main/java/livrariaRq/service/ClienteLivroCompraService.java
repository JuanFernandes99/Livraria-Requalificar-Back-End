package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.Compra;
import livrariaRq.model.livro.Livro;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.CompraRepository;
import livrariaRq.repository.LivroRepository;

@Service
public class ClienteLivroCompraService {

	private final CompraRepository compraRepo;
	private final ClienteRepository clienteRepo;
	private final LivroRepository livroRepo;

	@Autowired
	public ClienteLivroCompraService(CompraRepository aCompraRepo, ClienteRepository aClienteRepo,
			LivroRepository aLivroRepo) {
		compraRepo = aCompraRepo;
		clienteRepo = aClienteRepo;
		livroRepo = aLivroRepo;
	}

	public boolean adicionarCompra(Compra aCompra) {

		Optional<Cliente> opcionalCliente = clienteRepo.findById(aCompra.getCliente().getId());

		Cliente clienteAux = opcionalCliente.get();

		if (aCompra.getId() == null) {

			List<Livro> compraLivro = new ArrayList<>();

			for (Livro livro : aCompra.getLivros()) {

				Optional<Livro> livroCompra = livroRepo.findById(livro.getId());
				Livro livroAux = livroCompra.get();
				int livrosComprados = 0;
				if (livroAux.getQuantidadeStock() >= livro.getQuantidadeStock()) {
					livrosComprados = livroAux.getQuantidadeStock() - livro.getQuantidadeStock();
					livroAux.setQuantidadeStock(livrosComprados);
					livroAux.setQuantidadeComprada(livro.getQuantidadeStock() + livroAux.getQuantidadeComprada());
					compraLivro.add(livroAux);
				}
			}

<<<<<<< HEAD
			if (!aCompra.getCliente().getVouchers().isEmpty()) {
				Optional<Voucher> opcionalVoucher = voucherRepo.findById(aCompra.getVoucher().getId());
				Voucher voucherAux = opcionalVoucher.get();

				voucherAux.setUtilizado(true);
				System.out.print(voucherAux);
				voucherRepo.save(voucherAux); // ?
			}

			if (aCompra.getValorCompra() > 50 && aCompra.getValorCompra() < 150) {

				Voucher voucher = new Voucher();
				voucher.setValorVoucher(0.05);

				voucher.setCliente(clienteAux);
				// voucher.setCompra(aCompra);
				voucherRepo.save(voucher);
				aCompra.setVoucher(voucher);
				clienteAux.adicionarVoucher(voucher);

			}

			if (aCompra.getValorCompra() > 150) {
				Voucher voucher = new Voucher();

				voucher.setValorVoucher(0.15);
				voucher.setCliente(clienteAux);
				// voucher.setCompra(aCompra);
				voucherRepo.save(voucher);
				aCompra.setVoucher(voucher);
				clienteAux.adicionarVoucher(voucher);

			}

			aCompra.setLivros(compraLivro);
			aCompra.setCliente(clienteAux);

=======
			aCompra.setLivros(compraLivro);
>>>>>>> parent of 27b9c1b (good)
			clienteAux.adicionarCompra(aCompra);
			aCompra.setCliente(clienteAux);

			clienteRepo.save(clienteAux);
			compraRepo.save(aCompra);

			return true;
		} else {
			return false;
		}

	}

	public List<Compra> getComprasByClienteId(String aClienteId) {
		Optional<Cliente> opcionalCliente = clienteRepo.findById(Long.parseLong(aClienteId));
		Cliente cliente = opcionalCliente.get();
		List<Compra> comprasCliente = new ArrayList<>();
		for (Compra compras : cliente.getCompras()) {
			comprasCliente.add(compras);
		}

		return comprasCliente;
	}

	public boolean VerificarCliente(Compra aCompra) {

		Optional<Cliente> opcionalCliente = clienteRepo.findById(aCompra.getCliente().getId());

		if (opcionalCliente.isEmpty() || aCompra.getCliente() == null) {
			return false;
		} else {
			return true;

		}
	}

	public boolean VerificarLivro(Compra aCompra) {

		for (Livro livro : aCompra.getLivros()) {
			Optional<Livro> livroCompra = livroRepo.findById(livro.getId());

			if (!livroCompra.isPresent()) {
				return false;
			}
		}

		return true;
	}

}