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

public class AddEvent extends AppCompatActivity {
    private Spinner CurrentClasses;
    Button AddButton;

    ParseQuery<ParseObject> query;
    ParseObject st_course;

    List<String> Courses = new ArrayList<String>();
    String[] inputCourseToEdit;

    String str_courseToEdit, tempPrefix, tempStr;
    int tempNum;

    boolean InputVerified;
    boolean sendToMyCourse = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        CurrentClasses = (Spinner) findViewById(R.id.s_course_to_event);
        Courses.add("My Course(s)");
        query = ParseQuery.getQuery("Student_Course");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < List.size(); i++) {
                        tempNum = List.get(i).getInt("course_number");
                        tempPrefix = List.get(i).getString("prefix");
                        tempStr = tempPrefix + " " + tempNum;
                        Courses.add(tempStr);

                    }

                } else {


                }
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddEvent.this, android.R.layout.simple_spinner_item, Courses);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CurrentClasses.setAdapter(adapter);

        CurrentClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_courseToEdit = (String) parent.getItemAtPosition(position).toString();

                if (str_courseToEdit != ("My Course(s)")) {
                    InputVerified = true;
                    inputCourseToEdit = str_courseToEdit.split(" ", 2);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}