package com.vulcan.weather.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vulcan.weather.model.Forecast;
import com.vulcan.weather.model.pagination.PageRequest;
import com.vulcan.weather.persistence.ForecastRepository;

@Service("forecastService")
public class ForecastService {
	
	@Autowired
    private ForecastRepository forecastRepository;
	
	public Forecast getByDay(Integer day) {	
		return forecastRepository.findByDay(day);
	}

	public List<Forecast> getPaginated(PageRequest pagination) {		
		return forecastRepository.findByDayGreaterThanAndDayLessThan(pagination.getOffset(),pagination.getLimit());
	}
}
