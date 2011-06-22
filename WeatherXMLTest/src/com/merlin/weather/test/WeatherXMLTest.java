package com.merlin.weather.test;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.merlin.weather.WeatherXML;

public class WeatherXMLTest extends
		ActivityInstrumentationTestCase2<WeatherXML> {

	private WeatherXML mActivity;
	private TextView topView;
	private String resourceString;

	public WeatherXMLTest() {
		super("com.merlin.weather", WeatherXML.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mActivity = this.getActivity();
		topView = (TextView) mActivity.findViewById(com.merlin.weather.R.id.currentView);
		resourceString = mActivity.getString(com.merlin.weather.R.string.current);
	}

	public void testPreconditions() {
		assertNotNull(topView);
	}

	public void testText() {
		assertEquals(resourceString,(String)topView.getText());
	}
}
