package br.com.teste.demo.controller.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RoomResponse {
	private Integer id;
	private CategoryResponse category;
	private BigDecimal totalPrice;
	private PriceResponse priceDetail;
}
