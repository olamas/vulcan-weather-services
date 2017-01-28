package com.vulcan.weather.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vulcan.weather.model.Forecast;
import com.vulcan.weather.model.pagination.PageRequest;
import com.vulcan.weather.services.ForecastService;

@RestController
@RequestMapping("/forecast")
public class ForecastController {

	@Autowired
	private ForecastService forecastService;
	
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public ResponseEntity<Forecast> forecastByDay(@RequestParam("day")  final String day) {
		Forecast forecast = forecastService.getByDay(Integer.valueOf(day));
		if (forecast == null) {
			return new ResponseEntity<Forecast>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Forecast>(forecast, HttpStatus.OK);
	}
		
	@RequestMapping(value = "/weather", method = RequestMethod.POST)
	public ResponseEntity<List<Forecast>> forecastByPage(@RequestBody PageRequest  pagination) {
		List<Forecast> forecasts = forecastService.getPaginated(pagination);
		if (forecasts == null) {
			return new ResponseEntity<List<Forecast>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Forecast>>(forecasts, HttpStatus.OK);
	}
	
}
