package com.merlin13.weather;

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

	public List<Forecast> parse() {
		final Forecast currentForecast = new Forecast();
		RootElement root = new RootElement(ROOT_ELEMENT);
		final List<Forecast> forecasts = new ArrayList<Forecast>();
		Element channel = root.getChild(WEATHER);
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

		try {
			Xml.parse(this.getInputStream(), Xml.Encoding.UTF_8, root.getContentHandler());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return forecasts;
	}
}
