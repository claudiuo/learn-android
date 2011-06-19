package com.merlin.weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ForecastInfo {
	static SimpleDateFormat DATE_FORMATTER =
		new SimpleDateFormat("yyyy-MM-dd");
	// TODO
	static SimpleDateFormat DATETIME_FORMATTER =
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String city;
	private String postalCode;
	private String latitudeE6;
	private String longitudeE6;
	private Date forecastDate; //"2010-10-29"
	private Date currentDateTime; //"2010-10-29 16:14:37 +0000"
	private String unitSystem;

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getLatitudeE6() {
		return latitudeE6;
	}
	public void setLatitudeE6(String latitudeE6) {
		this.latitudeE6 = latitudeE6;
	}
	public String getLongitudeE6() {
		return longitudeE6;
	}
	public void setLongitudeE6(String longitudeE6) {
		this.longitudeE6 = longitudeE6;
	}
	public String getForecastDate() {
		return DATE_FORMATTER.format(this.forecastDate);
	}
	public void setForecastDate(String forecastDate) {
		try {
			this.forecastDate = DATE_FORMATTER.parse(forecastDate.trim());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	public String getCurrentDateTime() {
		return DATETIME_FORMATTER.format(this.currentDateTime);
	}
	public void setCurrentDateTime(String currentDateTime) {
		//"2010-10-29 16:14:37 +0000"
		// remove the last part of the String
		currentDateTime = currentDateTime.substring(0, currentDateTime.lastIndexOf(" "));
		try {
			this.currentDateTime = DATETIME_FORMATTER.parse(currentDateTime.trim());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	public String getUnitSystem() {
		return unitSystem;
	}
	public void setUnitSystem(String unitSystem) {
		this.unitSystem = unitSystem;
	}

}
