package br.com.teste.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.demo.controller.response.HotelResponse;
import br.com.teste.demo.service.HotelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
@CrossOrigin
public class HotelController {

	@Autowired
	private HotelService hotelService;

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = HotelResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })

	@ApiOperation(value = "Consultar Hotel pelo id da cidade", consumes = "application/json", produces = "application/json")
	@GetMapping("/find-hotel-cidade/{cityId}/{checkInDate}/{checkOutDate}/{numberOfAdults}/{numberOfChildren}")
	public List<HotelResponse> buscaHotelPorCidade(@PathVariable("cityId") int cityId,
			@PathVariable("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
			@PathVariable("checkOutDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate,
			@PathVariable("numberOfAdults") int numberOfAdults,
			@PathVariable("numberOfChildren") int numberOfChildren) {

		return hotelService.calculoHoteis(cityId, checkInDate, checkOutDate, numberOfAdults, numberOfChildren);
	}

	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = HotelResponse.class),
			@ApiResponse(code = 400, message = "Bad Request", response = Error.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Error.class) })

	@ApiOperation(value = "Consultar Hotel pelo id da cidade", consumes = "application/json", produces = "application/json")
	@GetMapping("/find-hotel/{hotelId}/{checkInDate}/{checkOutDate}/{numberOfAdults}/{numberOfChildren}")
	public List<HotelResponse> buscaHotelId(@PathVariable("hotelId") int hotelId,
			@PathVariable("checkInDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkInDate,
			@PathVariable("checkOutDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkOutDate,
			@PathVariable("numberOfAdults") int numberOfAdults,
			@PathVariable("numberOfChildren") int numberOfChildren) {

		return hotelService.calculoHotel(hotelId, checkInDate, checkOutDate, numberOfAdults, numberOfChildren);
	}

}
