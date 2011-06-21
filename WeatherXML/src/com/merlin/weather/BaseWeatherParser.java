package com.merlin.weather;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BaseWeatherParser implements WeatherParser {

	// names of the XML tags
	static final String FORECAST_CONDITIONS = "forecast_conditions";
	static final String CURRENT_CONDITIONS = "current_conditions";
	static final String WEATHER = "weather";
	static final String CONDITION = "condition";
	static final String DAY_OF_WEEK = "day_of_week";
	static final String LOW = "low";
	static final String HIGH = "high";
	static final String ICON = "icon";
	static final String TEMP_F = "temp_f";
	static final String TEMP_C = "temp_c";
	static final String HUMIDITY = "humidity";
	static final String WIND_CONDITION = "wind_condition";
	// names of the XML attribute for all tags
	static final String DATA = "data";

	private final URL feedUrl;

	private final String testXML = "<xml_api_reply version=\"1\">"+
	"<weather module_id=\"0\" tab_id=\"0\" mobile_row=\"0\" mobile_zipped=\"1\" row=\"0\" section=\"0\">"+
	"<forecast_information>"+
	 "<city data=\"Lehi, UT\"/>"+
	 "<postal_code data=\"84043\"/>"+
	 "<latitude_e6 data=\"\"/>"+
	 "<longitude_e6 data=\"\"/>"+
	 "<forecast_date data=\"2010-10-29\"/>"+
	 "<current_date_time data=\"2010-10-29 16:14:37 +0000\"/>"+
	 "<unit_system data=\"US\"/>"+
	"</forecast_information>"+
	"<current_conditions>"+
	 "<condition data=\"Partly Cloudy\"/>"+
	 "<temp_f data=\"45\"/>"+
	 "<temp_c data=\"7\"/>"+
	 "<humidity data=\"Humidity: 52%\"/>"+
	 "<icon data=\"/ig/images/weather/partly_cloudy.gif\"/>"+
	 "<wind_condition data=\"Wind: S at 11 mph\"/>"+
	"</current_conditions>"+
	"<forecast_conditions>"+
	 "<day_of_week data=\"Fri\"/>"+
	 "<low data=\"44\"/>"+
	 "<high data=\"61\"/>"+
	 "<icon data=\"/ig/images/weather/partly_cloudy.gif\"/>"+
	 "<condition data=\"Partly Cloudy\"/>"+
	"</forecast_conditions>"+
	"<forecast_conditions>"+
	 "<day_of_week data=\"Sat\"/>"+
	 "<low data=\"41\"/>"+
	 "<high data=\"55\"/>"+
	 "<icon data=\"/ig/images/weather/chance_of_storm.gif\"/>"+
	 "<condition data=\"Scattered Thunderstorms\"/>"+
	"</forecast_conditions>"+
	"<forecast_conditions>"+
	 "<day_of_week data=\"Sun\"/>"+
	 "<low data=\"37\"/>"+
	 "<high data=\"55\"/>"+
	 "<icon data=\"/ig/images/weather/partly_cloudy.gif\"/>"+
	 "<condition data=\"Partly Cloudy\"/>"+
	"</forecast_conditions>"+
	"<forecast_conditions>"+
	 "<day_of_week data=\"Mon\"/>"+
	 "<low data=\"38\"/>"+
	 "<high data=\"54\"/>"+
	 "<icon data=\"/ig/images/weather/sunny.gif\"/>"+
	 "<condition data=\"Sunny\"/>"+
	"</forecast_conditions>"+
	"</weather>"+
	"</xml_api_reply>";


	protected BaseWeatherParser(String feedUrl){
		try {
			this.feedUrl = new URL(feedUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	protected InputStream getInputStream() {
		try {
			return feedUrl.openConnection().getInputStream();
//			InputStream is = new ByteArrayInputStream(testXML.getBytes("UTF-8"));
//			return is;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}





}