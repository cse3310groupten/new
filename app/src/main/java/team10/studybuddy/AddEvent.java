package team10.studybuddy;

import android.content.Intent;
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

    private Button AddButton;
    private EditText day, month, year, description,pre,number;
    ParseQuery<ParseObject> query;
    ParseObject new_event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        AddButton = (Button) findViewById(R.id.AddButton);
        day = (EditText) findViewById(R.id.Daytext);
        month = (EditText) findViewById(R.id.Monthtext);
        year = (EditText) findViewById((R.id.Yeartext));
        pre = (EditText) findViewById(R.id.Prefixtext);
        number =(EditText) findViewById(R.id.Cousetext);
        description = (EditText) findViewById(R.id.Descriptiontext);
        AddButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AddButton:
                query = ParseQuery.getQuery("Event");
                final String day_string = day.getText().toString();
                final String month_string = month.getText().toString();
                final String year_string = year.getText().toString();
                final String description_string = description.getText().toString();
                final String prefix_string = pre.getText().toString();
                final String number_string = number.getText().toString();
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null)
                        {
                            new_event = objects.get(0);
                            new_event.put("prefix",prefix_string);
                            new_event.put("course_number",number_string);
                            new_event.put("day",day_string);
                            new_event.put("year",year_string);
                            new_event.put("month",month_string);
                            new_event.put("description",description_string);
                        }
                    }
                });
                startActivity(new Intent(this, MainMenu.class));
                break;


        }
    }
}