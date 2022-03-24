package livrariaRq.dto;

import java.util.ArrayList;
import java.util.List;

import livrariaRq.model.Compra;

public class SimpleResponseCompra extends SimpleResponse {
	List<Compra> compras;

	public SimpleResponseCompra() {

		compras = new ArrayList<>();
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> aCompras) {
		compras = aCompras;
	}

}
