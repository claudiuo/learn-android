package com.merlin.weather;

import java.util.List;

public class WeatherInfo {

	private Current currentConditions;
	private List<Forecast> forecasts;

	public Current getCurrentConditions() {
		return currentConditions;
	}
	public void setCurrentConditions(Current currentConditions) {
		this.currentConditions = currentConditions;
	}
	public List<Forecast> getForecasts() {
		return forecasts;
	}
	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}
	public void addForecast(Forecast forecast) {
		this.forecasts.add(forecast);
	}
}
