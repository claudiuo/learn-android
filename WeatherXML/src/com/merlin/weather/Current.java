package com.merlin.weather;

public class Current extends BaseWeatherCondition {

	private int tempF;
	private int tempC;
	private String humidity;
	private String wind;

	public int getTempF() {
		return tempF;
	}
	public void setTempF(String tempF) {
		this.tempF = Integer.parseInt(tempF);
	}
	public int getTempC() {
		return tempC;
	}
	public void setTempC(String tempC) {
		this.tempC = Integer.parseInt(tempC);
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Condition: ");
		sb.append(condition);
		sb.append('\n');
		sb.append("Temp (F): ");
		sb.append(tempF);
		sb.append(" - Temp (C): ");
		sb.append(tempC);
		sb.append('\n');
		sb.append("Humidity: ");
		sb.append(humidity);
		sb.append('\n');
//		sb.append("Wind: ");
		sb.append(wind);
		return sb.toString();
	}
}
