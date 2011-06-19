package com.merlin.weather;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Forecast implements Comparable<Forecast>{
	static SimpleDateFormat FORMATTER =
		new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
	private String title;
	private URL link;
	private String description;
	private Date date;

	private static final String GOOGLE_URL = "http://www.google.com";

	private String dayOfWeek;
	private int low;
	private int high;
	private URL iconUrl;
	private String condition;

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public void setLow(String low) {
		this.low = Integer.parseInt(low);
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public void setHigh(String high) {
		this.high = Integer.parseInt(high);
	}

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

	public Forecast copy(){
		Forecast copy = new Forecast();
		copy.dayOfWeek = dayOfWeek;
		copy.low = low;
		copy.high = high;
		copy.iconUrl = iconUrl;
		copy.condition = condition;
		return copy;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Condition: ");
		sb.append(condition);
		sb.append('\n');
		sb.append("Low: ");
		sb.append(low);
		sb.append('\n');
		sb.append("High: ");
		sb.append(high);
		sb.append('\n');
		sb.append("Day of week: ");
		sb.append(dayOfWeek);
		return sb.toString();
	}

























	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}
	// getters and setters omitted for brevity
	public URL getLink() {
		return link;
	}

	public void setLink(String link) {
		try {
			this.link = new URL(link);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public String getDate() {
		return FORMATTER.format(this.date);
	}

	public void setDate(String date) {
		// pad the date if necessary
		while (!date.endsWith("00")){
			date += "0";
		}
		try {
			this.date = FORMATTER.parse(date.trim());
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Forecast other = (Forecast) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public int compareTo(Forecast another) {
		if (another == null) return 1;
		// sort descending, most recent first
		return another.date.compareTo(date);
	}
}
