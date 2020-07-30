package br.com.teste.demo.controller.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PriceResponse {

	private BigDecimal pricePerDayAdult;
	private BigDecimal pricePerDayChild;
}
