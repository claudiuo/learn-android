package com.merlin.weather;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

public class AndroidSaxWeatherXMLParser extends BaseWeatherParser {

	static final String ROOT_ELEMENT = "xml_api_reply";

	public AndroidSaxWeatherXMLParser(String feedUrl) {
		super(feedUrl);
	}

	public WeatherInfo parse() {
		WeatherInfo weatherInfo = new WeatherInfo();
		Current currentConditions = new Current();
		weatherInfo.setCurrentConditions(currentConditions);
		final List<Forecast> forecasts = new ArrayList<Forecast>();
		weatherInfo.setForecasts(forecasts);
		RootElement root = new RootElement(ROOT_ELEMENT);
		Element channel = root.getChild(WEATHER);

		parseCurrent(currentConditions, channel);
		parseForecast(forecasts, channel);

		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return weatherInfo;
	}

	private void parseForecast(final List<Forecast> forecasts, Element channel) {
		final Forecast currentForecast = new Forecast();
		Element item = channel.getChild(FORECAST_CONDITIONS);
		item.setEndElementListener(new EndElementListener(){
			@Override
			public void end() {
				forecasts.add(currentForecast.copy());
			}
		});
		item.getChild(CONDITION).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				currentForecast.setCondition(attributes.getValue(DATA));
			}
		});
		item.getChild(DAY_OF_WEEK).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				currentForecast.setDayOfWeek(attributes.getValue(DATA));
			}
		});
		item.getChild(LOW).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				currentForecast.setLow(attributes.getValue(DATA));
			}
		});
		item.getChild(HIGH).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				currentForecast.setHigh(attributes.getValue(DATA));
			}
		});
		item.getChild(ICON).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				currentForecast.setIconUrl(attributes.getValue(DATA));
			}
		});
	}

	private void parseCurrent(final Current current, Element channel) {
		Element item = channel.getChild(CURRENT_CONDITIONS);
//		item.setEndElementListener(new EndElementListener(){
//			@Override
//			public void end() {
//				current = .add(currentForecast.copy());
//			}
//		});
		item.getChild(CONDITION).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				current.setCondition(attributes.getValue(DATA));
			}
		});
		item.getChild(ICON).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				current.setIconUrl(attributes.getValue(DATA));
			}
		});
		item.getChild(TEMP_F).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				current.setTempF(attributes.getValue(DATA));
			}
		});
		item.getChild(TEMP_C).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				current.setTempC(attributes.getValue(DATA));
			}
		});
		item.getChild(HUMIDITY).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				current.setHumidity(attributes.getValue(DATA));
			}
		});
		item.getChild(WIND_CONDITION).setStartElementListener(new StartElementListener() {
			@Override
			public void start(Attributes attributes) {
				current.setWind(attributes.getValue(DATA));
			}
		});
	}
}
