package livrariaRq.dto;

import java.util.ArrayList;
import java.util.List;

import livrariaRq.model.utilizador.Cliente;

public class SimpleResponseCliente extends SimpleResponse {
	List<Cliente> clientes;

	public SimpleResponseCliente() {
		clientes = new ArrayList<Cliente>();
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> aClientes) {
		clientes = aClientes;
	}

}
