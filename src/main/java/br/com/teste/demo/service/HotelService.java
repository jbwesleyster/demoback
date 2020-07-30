package br.com.teste.demo.service;

import java.util.Date;
import java.util.List;

import br.com.teste.demo.controller.response.HotelResponse;

public interface HotelService {

	List<HotelResponse> calculoHoteis(int cityId, Date checkInDate, Date checkOutDate, int numberOfAdults,
			int numberOfChildren);

	List<HotelResponse> calculoHotel(int idHotel, Date checkInDate, Date checkOutDate, int numberOfAdults,
			int numberOfChildren);

}