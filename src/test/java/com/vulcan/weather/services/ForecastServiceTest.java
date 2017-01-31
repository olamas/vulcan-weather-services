package com.vulcan.weather.services;


import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.vulcan.weather.mocks.ForecastTestConfiguration;
import com.vulcan.weather.model.Forecast;
import com.vulcan.weather.model.pagination.PageRequest;
import com.vulcan.weather.persistence.ForecastRepository;



@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes=ForecastTestConfiguration.class)
public class ForecastServiceTest {

	private static final String CO_STATE_VALUE = "CO";

	private static final String LLUVIA_STATE_VALUE = "LLUVIA";

	private static final String ESTABLE_STATE_VALUE = "ESTABLE";

	@Autowired
    private ForecastRepository forecastRepository;
	
	@Autowired
    private ForecastService forecastService;
	
	private Forecast forecast;
	
	private List<Forecast> forecasts;
	
	@Before
	public void setUp(){
		this.forecast=createForecast(1,ESTABLE_STATE_VALUE);
		this.forecasts = Arrays.asList(createForecast(1, LLUVIA_STATE_VALUE),createForecast(2, ESTABLE_STATE_VALUE),createForecast(3, CO_STATE_VALUE));
	}
	
	
	@Test
    public void whenDayIsProvided_thenRetrievedForecastCorrect() {
		Mockito.when(forecastRepository.findByDay(1)).thenReturn(this.forecast);
		Forecast weather = forecastService.getByDay(1);
        Assert.assertEquals(ESTABLE_STATE_VALUE, weather.getWeather());
    }
	
	@Test
    public void whenDayIsNull_thenDontRetrievedForecast() {
		Mockito.when(forecastRepository.findByDay(null)).thenReturn(null);
		Forecast weather = forecastService.getByDay(null);
        Assert.assertNull(weather);
    }
	
	@Test
    public void whenPaginationIsProvided_thenRetrievedForecastValidList() {
		PageRequest pagination = new PageRequest(0, 3);		
		Mockito.when(forecastRepository.findByDayGreaterThanAndDayLessThan(pagination.getOffset(), pagination.getLimit())).thenReturn(this.forecasts);

		List<Forecast> weathers = forecastService.getPaginated(pagination);
        Assert.assertTrue(weathers.size()==3);
        Assert.assertEquals(weathers.get(0).getWeather(),LLUVIA_STATE_VALUE);
        
    }	
	
	private Forecast createForecast(int day,String state) {
		Forecast forecast =  new Forecast();
		forecast.setId(1);
		forecast.setDay(day);		
		forecast.setWeather(state);
		return forecast;
	}	
	
}
