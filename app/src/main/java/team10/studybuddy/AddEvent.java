package team10.studybuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class AddEvent extends AppCompatActivity implements View.OnClickListener {

    Button AddButton;
    EditText day,month,year,description;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        AddButton = (Button) findViewById(R.id.AddButton);
        day = (EditText) findViewById(R.id.Daytext);
        month = (EditText) findViewById(R.id.Monthtext);
        year = (EditText) findViewById((R.id.Yeartext));
        
    }

    @Override
    public void onClick(View v) {

    }
}