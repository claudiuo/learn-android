package com.merlin.weather;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseWeatherCondition {

	private static final String GOOGLE_URL = "http://www.google.com";

	public URL iconUrl;
	public String condition;

	public URL getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		try {
			this.iconUrl = new URL(GOOGLE_URL+iconUrl);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
