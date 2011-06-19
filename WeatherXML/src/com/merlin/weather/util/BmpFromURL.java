package com.merlin.weather.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BmpFromURL {

	private Bitmap myBitmap;

	public BmpFromURL(String imageURL){

		// myImageURL is the URL object we are going to create from
		// the String we are passing in the constructor (imageURL).

		URL myImageURL = null;

		// We put a “try-catch” in this piece of code, because we need
		// to ensure that the URL is well formed. In the case that the
		// “imageURL” is not a valid URL, the exception will be launched,
		// giving a error message.

		try {
			myImageURL = new URL(imageURL);
		} catch (MalformedURLException error) {
			error.printStackTrace();
		}

		// In the next lines we open the connection to the URL using
		// HttpURLConnection, we open an InputStream and we construct
		// a Bitmap from the input Stream, using the BitmapFactory.
		// This way, we will have our image in the “Bitmap” object.

		try {
			HttpURLConnection connection = (HttpURLConnection)myImageURL.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			myBitmap = BitmapFactory.decodeStream(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Bitmap getMyBitmap() {
		return myBitmap;
	}
}
