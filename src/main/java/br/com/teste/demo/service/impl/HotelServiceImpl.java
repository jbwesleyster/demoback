package br.com.teste.demo.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.demo.controller.response.CategoryResponse;
import br.com.teste.demo.controller.response.HotelResponse;
import br.com.teste.demo.controller.response.PriceResponse;
import br.com.teste.demo.controller.response.RoomResponse;
import br.com.teste.demo.model.Hotel;
import br.com.teste.demo.model.Price;
import br.com.teste.demo.repository.HotelRestClient;
import br.com.teste.demo.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	private static final BigDecimal COMISSAO = new BigDecimal("0.70");

	@Autowired
	HotelRestClient hotelRestClient;

	@Override
	public List<HotelResponse> calculoHoteis(int cityId, Date checkInDate, Date checkOutDate, int numberOfAdults, int numberOfChildren) {

		List<Hotel> hoteis = hotelRestClient.hotelPorCidade(cityId);
		List<Hotel> hoteis2 = hoteis;
		return hoteis2.stream().map(h -> {
			return calculoEstadia(h, checkInDate, checkOutDate, numberOfAdults, numberOfChildren);
		}).collect(Collectors.toList());
	}

	public HotelResponse calculoEstadia(Hotel hotel, Date checkInDate, Date checkOutDate, int numberOfAdults, int numberOfChildren) {
		
		LocalDate localDateAntigo = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDateNovo   = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Period periodo = Period.between(localDateAntigo, localDateNovo);
		BigDecimal qtdDias = new BigDecimal(periodo.getDays());

		HotelResponse retorno = new HotelResponse();
		retorno.setId(hotel.getId());
		retorno.setCity(hotel.getCityName());

		// ROOM
		List<RoomResponse> rooms = hotel.getRooms().stream().map(r -> {
			RoomResponse room = new RoomResponse();
			room.setId(r.getRoomID());
			room.setCategory(new CategoryResponse(r.getCategoryName()));
			Price price = r.getPrice();

			PriceResponse priceResponse = new PriceResponse();
			priceResponse.setPricePerDayAdult(qtdDias.multiply(price.getAdult()).divide(COMISSAO, RoundingMode.HALF_EVEN));
			priceResponse.setPricePerDayChild(qtdDias.multiply(price.getChild()).divide(COMISSAO, RoundingMode.HALF_EVEN));
			room.setPriceDetail(priceResponse);
			// TOTAL PRICE
			room.setTotalPrice(priceResponse.getPricePerDayAdult().add(priceResponse.getPricePerDayChild()));
			return room;
		}).collect(Collectors.toList());
		retorno.setRooms(rooms);

		return retorno;
	}

	@Override
	public List<HotelResponse> calculoHotel(int idHotel, Date checkInDate, Date checkOutDate, int numberOfAdults, int numberOfChildren) {
		List<Hotel> hoteis = hotelRestClient.hotelporId(idHotel);
		return hoteis.stream().map(h ->{
			return calculoEstadia(h, checkInDate, checkOutDate, numberOfAdults, numberOfChildren);
		}).collect(Collectors.toList());
		
	}
}
