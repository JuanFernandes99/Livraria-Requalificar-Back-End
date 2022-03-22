package livrariaRq.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import livrariaRq.model.utilizador.Cliente;

@Entity
@Table(name = "Voucher")
public class Voucher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	private double valorVoucher;

	@OneToOne(mappedBy = "voucher")
	private Compra compra;

	@ManyToMany
	@JoinTable(name = "Cliente_Voucher", joinColumns = { @JoinColumn(name = "cliente_id") }, inverseJoinColumns = {
			@JoinColumn(name = "voucher_id") })
	@JsonIgnore
	List<Cliente> clientes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public double getValorVoucher() {
		return valorVoucher;
	}

	public Compra getCompra() {
		return compra;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setValorVoucher(double aValorVoucher) {
		valorVoucher = aValorVoucher;
	}

	public void setCompra(Compra aCompra) {
		compra = aCompra;
	}

	public void setClientes(List<Cliente> aClientes) {
		clientes = aClientes;
	}

}
