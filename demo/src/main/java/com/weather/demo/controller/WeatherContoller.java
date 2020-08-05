package com.weather.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.demo.model1.Weather;
import com.weather.demo.service.WeatherService;

@RestController
@RequestMapping("/getWeather")
public class WeatherContoller {

	private static final Logger logger = LoggerFactory.getLogger(WeatherContoller.class);

	@Autowired
	private WeatherService weatherService;

	@GetMapping(path = "/{country}/{city}")
	public Weather getWeather(@PathVariable String country, @PathVariable String city) {
		logger.info("Retrieving data inside controller..");
		return weatherService.getWeather(country, city);
	}

	@GetMapping(path = "/hello")
	public String getHello() {
		return "hello";
	}
	


}