package br.com.teste.demo.repository;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.teste.demo.model.Hotel;

@FeignClient(url = "https://cvcbackendhotel.herokuapp.com/hotels", name = "hotel")
public interface HotelRestClient {

	@GetMapping("avail/{idCidade}")
	public List<Hotel> hotelPorCidade(@PathVariable("idCidade") int idCidade);

	@GetMapping("{idHotel}")
	public List<Hotel> hotelporId(@PathVariable("idHotel") int idHotel);

}