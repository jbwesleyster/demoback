package br.com.teste.demo.model;

import lombok.Data;

@Data
public class Room {
	private Integer roomID;
	private String categoryName;
	private Price price;
}
