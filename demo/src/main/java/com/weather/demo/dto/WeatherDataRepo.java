package com.weather.demo.dto;

import org.springframework.data.jpa.repository.JpaRepository;


public interface WeatherDataRepo extends JpaRepository<WeatherData, Integer> {

}
