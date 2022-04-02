package livrariaRq.dto;


import livrariaRq.model.utilizador.Cliente;

public class SimpleResponseAutCliente extends SimpleResponse {
	private Cliente cliente;


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente aCliente) {
		cliente = aCliente;
	}

}