package com.merlin13.weather;

import java.net.URI;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.merlin13.weather.util.BmpFromURL;

public class EfficientAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Forecast[] data;

    public EfficientAdapter(Context context) {
        // Cache the LayoutInflate to avoid asking for a new one each time.
        mInflater = LayoutInflater.from(context);

        // Icons bound to the rows.
//        mIcon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon48x48_1);
//        mIcon2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon);
    }

    /**
     * The number of items in the list is determined by the number of speeches
     * in our array.
     *
     * @see android.widget.ListAdapter#getCount()
     */
    public int getCount() {
        return data.length;
    }

    /**
     * Since the data comes from an array, just returning the index is
     * sufficent to get at the data. If we were using a more complex data
     * structure, we would return whatever object represents one row in the
     * list.
     *
     * @see android.widget.ListAdapter#getItem(int)
     */
    public Object getItem(int position) {
        return position;
    }

    /**
     * Use the array index as a unique id.
     *
     * @see android.widget.ListAdapter#getItemId(int)
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Make a view to hold each row.
     *
     * @see android.widget.ListAdapter#getView(int, android.view.View,
     *      android.view.ViewGroup)
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        // A ViewHolder keeps references to children views to avoid unneccessary calls
        // to findViewById() on each row.
        ViewHolder holder;

        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_icon_text, null);

            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);

            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }

        // Bind the data efficiently with the holder.
        holder.text.setText(data[position].toString());

        BmpFromURL myBmpFromURL = new BmpFromURL(data[position].getIconUrl().toString());
        Bitmap myBitmap = myBmpFromURL.getMyBitmap();

        holder.icon.setImageBitmap(myBitmap);
        //holder.icon.setImageURI(Uri.parse("http://www.google.com/ig/images/weather/partly_cloudy.gif"));

        return convertView;
    }

    static class ViewHolder {
        TextView text;
        ImageView icon;
    }

	public void setData(Forecast[] data) {
		this.data = data;
	}
}
