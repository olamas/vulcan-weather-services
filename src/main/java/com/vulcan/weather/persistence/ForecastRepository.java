package com.vulcan.weather.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vulcan.weather.model.Forecast;

public interface ForecastRepository extends JpaRepository<Forecast, Integer> {

	public Forecast findByDay(Integer day);	
	
	public List<Forecast> findByDayGreaterThanAndDayLessThan(Integer startOffset,Integer endOffset);

}
