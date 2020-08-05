package com.weather.demo.service;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.weather.demo.model1.Main;
import com.weather.demo.model1.Weather;
import com.weather.demo.model1.Weather_;

@Service
public class WeatherService {

	private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";

	private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

	private final RestTemplate restTemplate;

	private final String apiKey;

	public WeatherService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		this.apiKey = "6e6a7cbc2258bc1b48a74fa448e49fce";
	}

	public Weather getWeather(String country, String city) {
		logger.info("Requesting current weather for {}/{}", country, city);
		URI url = new UriTemplate(WEATHER_URL).expand(city, country, this.apiKey);
		Weather weather = invoke(url, Weather.class);
		Weather_ we = weather.getWeather().get(0);
		System.out.println(we.getId());
		we.getDescription();
		Main main= weather.getMain();
		System.out.println(main.getTemp());
		String cityName=weather.getName();
		System.out.println(cityName);
		
		return weather;
	}

	private <T> T invoke(URI url, Class<T> responseType) {
		RequestEntity<?> request = RequestEntity.get(url).accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = this.restTemplate.exchange(request, responseType);
		return exchange.getBody();
	}
}
