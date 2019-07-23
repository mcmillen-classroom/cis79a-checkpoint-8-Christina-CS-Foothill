package christinahunter.myplacesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class InhabitantArrayAdapter extends ArrayAdapter<Inhabitant> {


    public InhabitantArrayAdapter(Context context, int resource, Inhabitant[] objects) {
        super(context, resource, objects);
    }

    public InhabitantArrayAdapter(Context context, List<Inhabitant> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Inhabitant currInhabitant = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.inhabitant_layout, parent, false);

        }

        TextView title = (TextView) convertView.findViewById(R.id.row_name);
        TextView subtitle = (TextView) convertView.findViewById(R.id.row_description);

        title.setText(currInhabitant.getmInhabitantName());
        subtitle.setText(currInhabitant.getmInhabitantDescription());

        return convertView;
    }
}


