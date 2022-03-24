package livrariaRq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import livrariaRq.model.Voucher;
import livrariaRq.repository.VoucherRepository;

@Service
public class VoucherService {
	private final VoucherRepository voucherRepo;

	@Autowired
	public VoucherService(VoucherRepository aVoucherRepo) {
		voucherRepo = aVoucherRepo;
	}

	public List<Voucher> getAllVouchers() {
		List<Voucher> vouchers = new ArrayList<>();
		voucherRepo.findAll().forEach(vouchers::add);
		return vouchers;
	}
}
