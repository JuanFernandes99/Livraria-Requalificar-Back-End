package livrariaRq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	private boolean isUtilizado = false;
	@OneToOne(mappedBy = "voucher")
	@JsonIgnore
	private Compra compra;

	@ManyToOne
	@JoinColumn(name = "Cliente_id")
	@JsonIgnore
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public double getValorVoucher() {
		return valorVoucher;
	}

	public Compra getCompra() {
		return compra;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public boolean isUtilizado() {
		return isUtilizado;
	}

	public void setValorVoucher(double aValorVoucher) {
		valorVoucher = aValorVoucher;
	}

	public void setCompra(Compra aCompra) {
		compra = aCompra;
	}

	public void setCliente(Cliente aCliente) {
		cliente = aCliente;
	}

	public void setUtilizado(boolean aIsUtilizado) {
		isUtilizado = aIsUtilizado;
	}

}