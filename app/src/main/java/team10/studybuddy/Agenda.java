package team10.studybuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.util.Date;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Agenda extends AppCompatActivity implements View.OnClickListener{

    Button addButton;
    Button ediButton;

    ParseQuery<ParseObject> Couesequery;
    ParseQuery<ParseObject> Eventquery;
    ParseObject st_course;
    String  tempPrefix, tempday,tempyear,tempmonth,tempDes="";
    int tempNum;
    String[] inputCourseToEdit;

    TextView dateText, agendaDesText;



    boolean InputVerified;
    boolean sendToMyCourse = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Intent intent = getIntent();

        dateText = (TextView) findViewById(R.id.agenda_date_des);
        agendaDesText = (TextView) findViewById(R.id.agenda_description);
        addButton = (Button) findViewById(R.id.AddButton);
        ediButton = (Button) findViewById(R.id.EditButton);
        addButton.setOnClickListener(this);
        ediButton.setOnClickListener(this);
        String day = intent.getStringExtra("day");
        String month = intent.getStringExtra("month");
        String year = intent.getStringExtra("year");
        tempday = day;
        tempmonth = month;
        tempyear = year;
        //Toast.makeText(getApplicationContext(), ""+month+"/"+day+"/"+year, Toast.LENGTH_SHORT).show();

        dateText.setText(""+month+"/"+day+"/"+year);
        Couesequery = ParseQuery.getQuery("Student_Course");
        Couesequery.whereEqualTo("user", ParseUser.getCurrentUser());
        Couesequery.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < List.size(); i++) {
                        if (List.get(i).getBoolean("inGroup")) {

                            tempNum = List.get(i).getInt("course_number");
                            tempPrefix = List.get(i).getString("prefix");

                            Eventquery = ParseQuery.getQuery("Event");
                            Eventquery.whereEqualTo("prefix", tempPrefix);
                            Eventquery.whereEqualTo("course_number", tempNum);
                            Eventquery.whereEqualTo("month", tempmonth);
                            Eventquery.whereEqualTo("day", tempday);
                            Eventquery.whereEqualTo("year", tempyear);
                            Eventquery.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> objects, ParseException e) {
                                    if (e == null) {

                                        if (objects.size() == 0) {
                                            //tempDes = "There is no Event";
                                        } else {
                                            for (int i = 0; i < objects.size(); i++) {
                                                tempDes += objects.get(i).getString("description") + "\n";

                                            }
                                        }
                                        agendaDesText.setText(tempDes);
                                    } else {
                                    }

                                }
                            });

                        }


                    }
                } else {


                }
            }
        });





        }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.AddButton:
                startActivity(new Intent(this, AddEvent.class));
                break;
            case R.id.EditButton:
                startActivity(new Intent(this, EditEvent.class));
                break;
        }

    }
}