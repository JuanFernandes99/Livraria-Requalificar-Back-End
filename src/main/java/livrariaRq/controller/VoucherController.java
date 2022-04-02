package livrariaRq.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import livrariaRq.dto.SimpleResponseVoucher;
import livrariaRq.model.Voucher;
import livrariaRq.service.ClienteVoucherService;
import livrariaRq.service.VoucherService;

@CrossOrigin
@RestController
public class VoucherController {

	private final VoucherService voucherService;
	private final ClienteVoucherService clienteVoucherService;

	@Autowired
	public VoucherController(VoucherService aVoucherService, ClienteVoucherService aVoucherClienteService) {
		voucherService = aVoucherService;
		clienteVoucherService = aVoucherClienteService;
	}

	@CrossOrigin
	@PostMapping("/addVoucher")
	public ResponseEntity<SimpleResponseVoucher> addVoucher(@RequestBody Voucher aVoucher) {
		SimpleResponseVoucher srv = new SimpleResponseVoucher();

		if (voucherService.adicionarVoucher(aVoucher)) {
			srv.setAsSuccess("Voucher adicionado com sucesso");
			srv.setVoucher(aVoucher);
			return ResponseEntity.status(HttpStatus.OK).body(srv);
		} else {
			srv.setMessage("Ocorreu um erro ao adicionar o voucher");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(srv);
		}
	}

	@CrossOrigin
	@GetMapping("/getAllVouchers")
	public List<Voucher> getVouchers() {
		return voucherService.getAllVouchers();
	}

	@CrossOrigin
	@GetMapping("/getVouchersCliente/{aId}")
	public List<Voucher> getComprasCliente(@PathVariable String aId) {

		return clienteVoucherService.getVouchersByClienteId(aId);
	}

}
