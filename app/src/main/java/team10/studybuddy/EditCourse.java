package team10.studybuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class EditCourse extends AppCompatActivity {

    Spinner CourseList,CoursePrefix;
    EditText course;
    Button submit;
    ParseUser currentUser = ParseUser.getCurrentUser();
    private static final String[]prefix = {"DELETE", "BE","BIOL", "CE","CHEM", "CSE", "EE", "ENGR","GEOL", "IE","MATH", "MSE", "MAE", "NE","PHYS","SCIE"};
    String[] str_mycourses;
    String str_prefix,str_mycourse, prefix1;
    int cnumber1;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_edit_course);

        CourseList = (Spinner) findViewById(R.id.sMyCourse);
        CoursePrefix = (Spinner) findViewById(R.id.sPrefix_edit);
        course = (EditText) findViewById(R.id.course1_edit);
        submit = (Button) findViewById(R.id.edit_course_submit_btn);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student_Course");
        query.whereEqualTo("user", currentUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {

            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {

                    String str = "Retrieved " + List.size() + " scores";
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                    for(int i=0;i<List.size();i++)
                    {
                        prefix1 = List.get(i).getString("prefix");
                        cnumber1 = List.get(i).getInt("course_number");

                        str_mycourses[i]=prefix1+" "+cnumber1;

                    }

                } else {



                }
            }
        });


       adapter = new ArrayAdapter<String>(EditCourse.this, android.R.layout.simple_spinner_item, str_mycourses);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CoursePrefix.setAdapter(adapter);

        CoursePrefix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_mycourse = (String) parent.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(), str_mycourse, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        adapter = new ArrayAdapter<String>(EditCourse.this, android.R.layout.simple_spinner_item, prefix);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CoursePrefix.setAdapter(adapter);

        CoursePrefix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_prefix = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
