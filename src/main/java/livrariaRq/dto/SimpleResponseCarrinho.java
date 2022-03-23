package livrariaRq.dto;

import java.util.ArrayList;
import java.util.List;

import livrariaRq.model.CarrinhoDeCompras;

public class SimpleResponseCarrinho extends SimpleResponse {
	List<CarrinhoDeCompras> carrinhos;

	public SimpleResponseCarrinho() {
		carrinhos = new ArrayList<CarrinhoDeCompras>();
	}

	public List<CarrinhoDeCompras> getCarrinhos() {
		return carrinhos;
	}

	public void setCarrinhos(List<CarrinhoDeCompras> aCarrinhos) {
		carrinhos = aCarrinhos;
	}
	

}
