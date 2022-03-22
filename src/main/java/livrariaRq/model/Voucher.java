package livrariaRq.model;

import javax.persistence.OneToOne;

public class Voucher {

	@OneToOne(mappedBy = "voucher")
	private Compra compra;

}
