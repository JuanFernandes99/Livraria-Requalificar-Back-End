package livrariaRq.dto;

import livrariaRq.model.Voucher;

public class SimpleResponseVoucher extends SimpleResponse {
	private Voucher voucher;

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher aVoucher) {
		voucher = aVoucher;
	}

}