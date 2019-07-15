package christinahunter.myplacesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mlistView;
    private ArrayAdapter<Place> mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlistView = (ListView) findViewById(R.id.list_view);

        ArrayList<Place> myPlaces = new ArrayList<Place>();
        myPlaces.add(new Place("Laney College","Near Lake Merritt"));
        myPlaces.add(new Place("Berkeley City College","Near UC Berkeley"));
        myPlaces.add(new Place("College of Alameda","On Alameda Island"));
        myPlaces.add(new Place("Merritt College","Near Skyline"));

        mArrayAdapter = new PlaceArrayAdapter(this,myPlaces);

        mlistView.setAdapter(mArrayAdapter);
    }
}
