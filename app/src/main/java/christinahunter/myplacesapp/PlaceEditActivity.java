package christinahunter.myplacesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PlaceEditActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSubmitButton;
    private EditText mNameEditText;
    private EditText mDescriptionEditText;
    private int mPosition;
    private String mPlaceId;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_edit);
        setTitle(R.string.place_edit_title);

        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(this);

        mNameEditText = (EditText) findViewById(R.id.place_name_edit_text);
        mDescriptionEditText = (EditText) findViewById(R.id.place_description_edit_text);

        Intent data = getIntent();
        mNameEditText.setText(data.getStringExtra("place_name"));
        mDescriptionEditText.setText(data.getStringExtra("place_description"));
        mPosition = data.getIntExtra("place_position",-1);
        mPlaceId = data.getStringExtra("place_id");

        if(mPosition == -1){
            //TODO: throw exception
        }
    }

    @Override
    public void onClick(View v){
        String name = mNameEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();

        Intent resultData = new Intent();
        resultData.putExtra("place_position", mPosition);
        resultData.putExtra("place_name", name);
        resultData.putExtra("place_description", description);
        resultData.putExtra("place_id", mPlaceId);

        setResult(RESULT_OK, resultData);
        finish();
    }

}
