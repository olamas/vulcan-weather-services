package com.vulcan.weather.controller.rest;

import static org.mockito.BDDMockito.given;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.vulcan.weather.model.Forecast;
import com.vulcan.weather.services.ForecastService;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat; 



@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ForecastControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
    private ForecastService forecastService;
		
		
	@Before
    public void setup() {
		given(this.forecastService.getByDay(1)).willReturn(
				createForecast(1,"LLUVIA"));
    }
	
	@Test
    public void invalidateGetWeatherByDay() throws Exception {
		ResponseEntity<Forecast> entity = this.restTemplate.getForEntity("/forecast/weathe?day=1",Forecast.class);
		assertThat(entity.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
	
	@Test
    public void validateGetWeatherByDay() throws Exception {
		ResponseEntity<Forecast> entity = this.restTemplate.getForEntity("/forecast/weather?day=1",Forecast.class);
		assertThat(entity.getStatusCode(), is(HttpStatus.OK));
		assertEquals(createForecast(1, "LLUVIA").getWeather(), entity.getBody().getWeather());
    }
	
	private Forecast createForecast(int day,String state) {
		Forecast forecast =  new Forecast();
		forecast.setId(1);
		forecast.setDay(day);		
		forecast.setWeather(state);
		return forecast;
	}	
}
