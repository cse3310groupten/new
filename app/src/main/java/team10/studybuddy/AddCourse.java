package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class AddCourse extends AppCompatActivity {

    EditText course1;
    Button submit;
    private Spinner cPrefix1;
    private static final String[]prefix = {"PREFIX", "BE","BIOL", "CE","CHEM", "CSE", "EE", "ENGR","GEOL", "IE","MATH", "MSE", "MAE", "NE","PHYS","SCIE"};
    ParseUser currentUser = ParseUser.getCurrentUser();
    //ParseObject course_info = new ParseObject("Course");
    ParseObject st_course = new ParseObject("Student_Course");

    String str_prefix1;
    int cNum1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        course1 = (EditText) findViewById(R.id.courseNum1);

        submit = (Button) findViewById(R.id.id_submit_course_btn);

        cPrefix1 = (Spinner) findViewById(R.id.sPrefix1);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCourse.this, android.R.layout.simple_spinner_item, prefix);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cPrefix1.setAdapter(adapter);

        cPrefix1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_prefix1 = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_course, menu);
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

    public void submitCourse(View view)
    {
        if(course1.getText().toString().equals("")) cNum1=0;
        else cNum1=Integer.parseInt(course1.getText().toString());

        if((cNum1>0) && !(str_prefix1.equals("PREFIX")) )
        {

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Student_Course");
            query.whereEqualTo("user", currentUser.getCurrentUser());
            query.whereEqualTo("prefix", str_prefix1);
            query.whereEqualTo("course_number", cNum1);
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> List, ParseException e) {
                    if (e == null) {
                        String str = "already enrolled in " + str_prefix1 + " " + cNum1+ ". Please try again";
                        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                    } else {

                        st_course.put("prefix", str_prefix1);
                        st_course.put("course_number", cNum1);
                        st_course.put("user", currentUser.getCurrentUser());

                        st_course.saveInBackground();

                        Toast.makeText(getApplicationContext(), "Added course successfully.", Toast.LENGTH_SHORT).show();

                    }
                }
            });



           // Toast.makeText(getApplicationContext(), "Added course(s) successfully.", Toast.LENGTH_SHORT).show();



        }
        else
        {
            Toast.makeText(getApplicationContext(), "Wrong information. Please try again.", Toast.LENGTH_SHORT).show();


        }


    }

}
