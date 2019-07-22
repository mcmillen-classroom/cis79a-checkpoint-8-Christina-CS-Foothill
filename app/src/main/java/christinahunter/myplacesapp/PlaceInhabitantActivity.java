package christinahunter.myplacesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlaceInhabitantActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSubmitButton;
    private EditText mNameEditText;
    private EditText mDescriptionEditText;
    private TextView mPlaceNameView;
    private String id;
    private String placeName;

    DatabaseReference databaseInhabitants;

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

        databaseInhabitants = FirebaseDatabase.getInstance().getReference("inhabitants").child(id);

    }

    @Override
    public void onClick(View view) {

        saveInhabitant();

    }

    public void saveInhabitant(){

        String inhabitantName = mNameEditText.getText().toString().trim();
        String inhabitantDescription = mDescriptionEditText.getText().toString().trim();

        if(!TextUtils.isEmpty(inhabitantName)){
            String inhandId = databaseInhabitants.push().getKey();
            Inhabitant inhabitant = new Inhabitant(inhabitantName,inhabitantDescription, inhandId);
            databaseInhabitants.child(inhandId).setValue(inhabitant);
            Toast.makeText(this,"Inhabitant saved successfully",Toast.LENGTH_SHORT).show();
        }
        else{

            Toast.makeText(this,"Name should not be empty",Toast.LENGTH_SHORT).show();
        }

    }
}
