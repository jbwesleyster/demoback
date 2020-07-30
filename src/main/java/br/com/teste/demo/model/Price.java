package br.com.teste.demo.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Price {

	private BigDecimal adult;
	private BigDecimal child;
}
