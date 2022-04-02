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

@Service
public class ClienteVoucherService {
	private final ClienteRepository clienteRepo;

	@Autowired
	public ClienteVoucherService(ClienteRepository aClienteRepo) {

		clienteRepo = aClienteRepo;
	}

	public List<Voucher> getVouchersByClienteId(String aClienteId) {
		Optional<Cliente> opcionalCliente = clienteRepo.findById(Long.parseLong(aClienteId));
		Cliente cliente = opcionalCliente.get();
		List<Voucher> vouchersCliente = new ArrayList<>();
		for (Voucher vouchers : cliente.getVouchers()) {
			vouchersCliente.add(vouchers);
		}

		return vouchersCliente;
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
}
