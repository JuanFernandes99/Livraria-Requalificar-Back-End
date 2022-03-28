package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.model.Voucher;
import livrariaRq.service.VoucherService;
@CrossOrigin
@RestController
public class VoucherController {

	private final VoucherService voucherService;

	@Autowired
	public VoucherController(VoucherService aVoucherService) {
		voucherService = aVoucherService;
	}
	@CrossOrigin
	@GetMapping("/getAllVouchers")
	public List<Voucher> getVouchers() {
		return voucherService.getAllVouchers();
	}

}
