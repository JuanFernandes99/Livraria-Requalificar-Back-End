package livrariaRq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.repository.CompraRepository;
import livrariaRq.repository.VoucherRepository;

@Service
public class CompraVoucherService {
	private final CompraRepository compraRepo;
	private final VoucherRepository voucherRepo;

	@Autowired
	public CompraVoucherService(CompraRepository aCompraRepo, VoucherRepository aVoucherRepo) {

		compraRepo = aCompraRepo;
		voucherRepo = aVoucherRepo;
	}

}
