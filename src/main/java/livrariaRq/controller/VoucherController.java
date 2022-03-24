package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.model.Voucher;
import livrariaRq.service.VoucherService;

@RestController
public class VoucherController {

	private final VoucherService voucherService;

	@Autowired
	public VoucherController(VoucherService aVoucherService) {
		voucherService = aVoucherService;
	}

	@GetMapping("/getAllVouchers")
	public List<Voucher> getVouchers() {
		return voucherService.getAllVouchers();
	}

}
