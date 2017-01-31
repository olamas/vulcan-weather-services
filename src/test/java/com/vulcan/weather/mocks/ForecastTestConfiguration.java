package com.vulcan.weather.mocks;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import com.vulcan.weather.persistence.ForecastRepository;
import com.vulcan.weather.services.ForecastService;

@Profile("test")
@Configuration
public class ForecastTestConfiguration {
		
	@Bean
    @Primary
    public ForecastRepository forecastRepository() {
        return Mockito.mock(ForecastRepository.class);
    }
	
	@Bean
	public ForecastService forecastService() {
        return new ForecastService();
    }
	
}
