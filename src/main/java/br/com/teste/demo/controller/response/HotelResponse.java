package br.com.teste.demo.controller.response;

import java.util.List;

import lombok.Data;

@Data
public class HotelResponse {

	private Integer id;
	private String city;
	private List<RoomResponse> rooms;
}
