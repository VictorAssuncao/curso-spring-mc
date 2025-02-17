package com.vras.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import com.vras.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;

	public PagamentoComCartao() {
		super();
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
}
