package com.merlin13.weather;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.ArrayAdapter;

public class WeatherXML extends ListActivity {

	private List<Forecast> forecasts;
	static String feedUrl = "http://www.google.com/ig/api?weather=84043&hl=en";
	private static final String TAG = "WeatherXML";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        EfficientAdapter adapter = new EfficientAdapter(this);
//        adapter.setData(DATA);
        loadFeed();
        adapter.setData(forecasts.toArray(new Forecast[0]));
        setListAdapter(adapter);
    }

	private void loadFeed(){
		List<String> forecastDays = new ArrayList<String>();
    	try{
    		Log.i(TAG, "loading weather XML");
    		WeatherParser parser = new AndroidSaxWeatherXMLParser(feedUrl);
	    	long start = System.currentTimeMillis();
	    	forecasts = parser.parse();
	    	long duration = System.currentTimeMillis() - start;
	    	Log.i(TAG, "Parser duration=" + duration);
//	    	String xml = writeXml();
//	    	Log.i(TAG, xml);
	    	for (Forecast f : forecasts){
	    		forecastDays.add(f.getDayOfWeek()+"-"+f.getCondition()+
	    				"-"+f.getLow()+"-"+f.getHigh()+"-"+f.getIconUrl());
	    	}
//	    	ArrayAdapter<String> adapter =
//	    		new ArrayAdapter<String>(this, R.layout.row, forecastDays);
//	    	this.setListAdapter(adapter);
    	} catch (Throwable t){
    		Log.e(TAG,t.getMessage(),t);
    	}
//    	return forecastDays;
    }

	private String writeXml(){
		XmlSerializer serializer = Xml.newSerializer();
		StringWriter writer = new StringWriter();
		try {
			serializer.setOutput(writer);
			serializer.startDocument("UTF-8", true);
			serializer.startTag("", "messages");
			serializer.attribute("", "number", String.valueOf(forecasts.size()));
			for (Forecast msg: forecasts){
				serializer.startTag("", "message");
				serializer.attribute("", "date", msg.getDate());
				serializer.startTag("", "title");
				serializer.text(msg.getTitle());
				serializer.endTag("", "title");
				serializer.startTag("", "url");
				serializer.text(msg.getLink().toExternalForm());
				serializer.endTag("", "url");
				serializer.startTag("", "body");
				serializer.text(msg.getDescription());
				serializer.endTag("", "body");
				serializer.endTag("", "message");
			}
			serializer.endTag("", "messages");
			serializer.endDocument();
			return writer.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}