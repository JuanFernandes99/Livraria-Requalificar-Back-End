package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.Compra;
import livrariaRq.model.Voucher;
import livrariaRq.model.utilizador.Cliente;
import livrariaRq.repository.ClienteRepository;
import livrariaRq.repository.CompraRepository;
import livrariaRq.repository.VoucherRepository;

@Service
public class VoucherService {
	private final VoucherRepository voucherRepo;
	private final ClienteRepository clienteRepo;
	private final CompraRepository compraRepo;

	@Autowired
	public VoucherService(VoucherRepository aVoucherRepo, ClienteRepository aClienteRepo,
			CompraRepository aCompraRepo) {
		voucherRepo = aVoucherRepo;
		clienteRepo = aClienteRepo;
		compraRepo = aCompraRepo;
	}

	public boolean adicionarVoucher(Voucher aVoucher) {

		Optional<Cliente> opcionalCliente = clienteRepo.findById(aVoucher.getCliente().getId());

		Cliente clienteAux = opcionalCliente.get();

		Optional<Compra> opcionalCompra = compraRepo.findById(aVoucher.getCompra().getId());

		Compra compraAux = opcionalCompra.get();

		if (aVoucher.getId() == null) {

			clienteRepo.save(clienteAux);
			compraRepo.save(compraAux);
			voucherRepo.save(aVoucher);

			return true;
		} else {
			return false;
		}

	}

	public List<Voucher> getAllVouchers() {
		List<Voucher> vouchers = new ArrayList<>();
		voucherRepo.findAll().forEach(vouchers::add);
		return vouchers;
	}
}
