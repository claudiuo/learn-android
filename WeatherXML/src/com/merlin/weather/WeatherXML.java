package com.merlin.weather;

import java.util.ArrayList;
import java.util.List;

import com.merlin.weather.EfficientAdapter.ViewHolder;
import com.merlin.weather.util.BmpFromURL;

import android.app.ListActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WeatherXML extends ListActivity {

	private List<Forecast> forecasts;
	private Current currentConditions;
	static String feedUrl = "http://www.google.com/ig/api?weather=84043&hl=en";
	private static final String TAG = "WeatherXML";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        EfficientAdapter adapter = new EfficientAdapter(this);
        loadFeed();
        adapter.setData(forecasts.toArray(new Forecast[0]));
        setListAdapter(adapter);

        setTopView();
    }

	private void setTopView() {
		LinearLayout topLayoutView = (LinearLayout) findViewById(R.id.topLayout);

		ViewHolder holder = new ViewHolder();
        holder.text = (TextView) topLayoutView.findViewById(R.id.text);
        holder.icon = (ImageView) topLayoutView.findViewById(R.id.icon);

        BmpFromURL myBmpFromURL = new BmpFromURL(currentConditions.getIconUrl().toString());
        Bitmap myBitmap = myBmpFromURL.getMyBitmap();

        holder.icon.setImageBitmap(myBitmap);
        holder.text.setText(currentConditions.toString());
	}

	private void loadFeed(){
		List<String> forecastDays = new ArrayList<String>();
    	try{
    		Log.i(TAG, "loading weather XML");
    		WeatherParser parser = new AndroidSaxWeatherXMLParser(feedUrl);
	    	long start = System.currentTimeMillis();
	    	WeatherInfo weatherInfo = parser.parse();
	    	forecasts = weatherInfo.getForecasts();
	    	currentConditions = weatherInfo.getCurrentConditions();
	    	long duration = System.currentTimeMillis() - start;
	    	Log.i(TAG, "Parser duration=" + duration);
	    	for (Forecast f : forecasts){
	    		forecastDays.add(f.getDayOfWeek()+"-"+f.getCondition()+
	    				"-"+f.getLow()+"-"+f.getHigh()+"-"+f.getIconUrl());
	    	}
    	} catch (Throwable t){
    		Log.e(TAG,t.getMessage(),t);
    	}
    }
}