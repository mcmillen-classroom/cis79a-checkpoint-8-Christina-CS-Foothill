package christinahunter.myplacesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PlaceArrayAdapter extends ArrayAdapter<Place> {

    public PlaceArrayAdapter(Context context, int resource, Place[] objects) {
        super(context, resource, objects);
    }

    public PlaceArrayAdapter(Context context, List<Place> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Place currPlace = getItem(position);

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.place_layout,parent,false);

        }

        TextView title = (TextView) convertView.findViewById(R.id.row_title);
        TextView subtitle = (TextView) convertView.findViewById(R.id.row_subtitle);

        title.setText(currPlace.getmName());
        subtitle.setText(currPlace.getmDescription());

        return convertView;
    }
}
