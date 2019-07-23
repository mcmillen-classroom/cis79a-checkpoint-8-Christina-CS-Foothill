package christinahunter.myplacesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PlaceInhabitantActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSubmitButton;
    private EditText mNameEditText;
    private EditText mDescriptionEditText;
    private TextView mPlaceNameView;
    private String id;
    private String placeName;

    ListView mListViewInhabitants;
    DatabaseReference databaseInhabitants;

    List<Inhabitant> inhabitants = new ArrayList<Inhabitant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_inhabitant);


        mSubmitButton = (Button) findViewById(R.id.save_button);
        mSubmitButton.setOnClickListener(this);

        mNameEditText = (EditText) findViewById(R.id.inhabitant_name_edit_text);
        mDescriptionEditText = (EditText) findViewById(R.id.inhabitant_description_edit_text);

        mPlaceNameView = (TextView) findViewById(R.id.place_name_view);

        Intent intent = getIntent();
        id = intent.getStringExtra(MainActivity.PLACE_ID);
        placeName = intent.getStringExtra(MainActivity.PLACE_NAME);

        mPlaceNameView.setText(placeName);

        mListViewInhabitants = (ListView) findViewById(R.id.inhabitant_list_view);

        databaseInhabitants = FirebaseDatabase.getInstance().getReference("inhabitants").child(id);

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseInhabitants.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 inhabitants.clear();
                 for(DataSnapshot inhabitantSnapShot: dataSnapshot.getChildren()){
                     Inhabitant inhabitant = inhabitantSnapShot.getValue(Inhabitant.class);
                     inhabitants.add(inhabitant);
                 }

                 InhabitantArrayAdapter inhabitantAdapter = new InhabitantArrayAdapter(PlaceInhabitantActivity.this,inhabitants);
                 mListViewInhabitants.setAdapter(inhabitantAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        saveInhabitant();
        finish();

    }

    public void saveInhabitant(){

        String inhabitantName = mNameEditText.getText().toString().trim();
        String inhabitantDescription = mDescriptionEditText.getText().toString().trim();

        if(!TextUtils.isEmpty(inhabitantName)){
            String inhabId = databaseInhabitants.push().getKey();
            Inhabitant inhabitant = new Inhabitant(inhabitantName,inhabitantDescription, inhabId);
            databaseInhabitants.child(inhabId).setValue(inhabitant);
            Toast.makeText(this,"Inhabitant saved successfully",Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(this,"Name should not be empty",Toast.LENGTH_SHORT).show();
        }

    }
}
