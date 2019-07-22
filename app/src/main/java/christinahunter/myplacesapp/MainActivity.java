package christinahunter.myplacesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int REQUEST_PLACE_CREATE = 1;
    private static final int REQUEST_PLACE_EDIT = 3;
    public static final String PLACE_ID = "place_id";
    public static final String PLACE_NAME = "place_name";

    private ListView mlistView;
    private ArrayAdapter<Place> mArrayAdapter;
    private ArrayList<Place> myPlaces = new ArrayList<Place>();

    DatabaseReference dataBasePlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBasePlaces = FirebaseDatabase.getInstance().getReference("places");
        mlistView = (ListView) findViewById(R.id.list_view);


       myPlaces.add(new Place("Laney College","Near Lake Merritt"));
        myPlaces.add(new Place("Berkeley City College","Near UC Berkeley"));
        myPlaces.add(new Place("College of Alameda","On Alameda Island"));
        myPlaces.add(new Place("Merritt College","Near Skyline"));

        mArrayAdapter = new PlaceArrayAdapter(this,myPlaces);

        mlistView.setAdapter(mArrayAdapter);
        mlistView.setOnItemClickListener(this);
        mlistView.setOnCreateContextMenuListener(this);

        dataBasePlaces.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot placesSnapShot: dataSnapshot.getChildren()){
                    Place place = placesSnapShot.getValue(Place.class);
                    mArrayAdapter.add(place);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //executed if there is an error
                System.out.println("failed");

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(resultCode == RESULT_OK && requestCode == REQUEST_PLACE_CREATE){
            if(data != null){
                String name = data.getStringExtra(PLACE_NAME);
                String description = data.getStringExtra("place_description");
                String id = dataBasePlaces.push().getKey();
                Place newPlace = new Place(name,description,id);
                dataBasePlaces.child(id).setValue(newPlace);

                dataBasePlaces.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        myPlaces = new ArrayList<Place>();
                        for(DataSnapshot placesSnapShot: dataSnapshot.getChildren()){
                            Place place = placesSnapShot.getValue(Place.class);

                            mArrayAdapter.add(place);

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //executed if there is an error
                        System.out.println("failed");

                    }
                });
            }
        }

        if(resultCode == RESULT_OK && requestCode == REQUEST_PLACE_EDIT){
            String name = data.getStringExtra(PLACE_NAME);
            String description = data.getStringExtra("place_description");
            int position = data.getIntExtra("place_position",-1);

            if(position != -1){
                Place place = mArrayAdapter.getItem(position);
                place.setmName(name);
                place.setmDescription(description);
                //tells the arrayAdapter that the arraylist has been updated/changed
                mArrayAdapter.notifyDataSetChanged();
            }

        }

    }




    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.main_menu_create){

            launchCreateActivity();

            return true;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu,v,menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        //need to use this to get the position of the item
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

        if(item.getItemId() == R.id.context_menu_edit){
           Place place = mArrayAdapter.getItem(info.position);
           launchEditActivity(place,info.position);
           return true;
        }
        else if(item.getItemId() == R.id.context_menu_delete){
            Place place = mArrayAdapter.getItem(info.position);
            mArrayAdapter.remove(place);

        }
        else if(item.getItemId() == R.id.add_inhabitant){
            Place place = mArrayAdapter.getItem(info.position);
            launchAddInhabitantActivity(place);
        }

        return false;
    }

    private void launchCreateActivity(){

        Intent createIntent = new Intent(this, PlaceCreateActivity.class);
        startActivityForResult(createIntent, REQUEST_PLACE_CREATE);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Place place = mArrayAdapter.getItem(position);
        launchEditActivity(place, position);
    }

    private void launchEditActivity(Place place, int position) {
        Intent editIntent = new Intent(this, PlaceEditActivity.class);
        editIntent.putExtra("place_position",position);
        editIntent.putExtra(PLACE_NAME, place.getmName());
        editIntent.putExtra("place_description",place.getmDescription());
        startActivityForResult(editIntent, REQUEST_PLACE_EDIT);

    }

    private void launchAddInhabitantActivity(Place place){

        Intent inhabitantIntent = new Intent(this, PlaceInhabitantActivity.class);

        inhabitantIntent.putExtra(PLACE_ID, place.getmPlaceId());
        inhabitantIntent.putExtra(PLACE_NAME,place.getmName());

        startActivity(inhabitantIntent);

    }
}
