package team10.studybuddy;

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

import java.util.ArrayList;
import java.util.List;

public class EditCourse extends AppCompatActivity {

    private Spinner courseToEdit, coursePrefix;
    EditText courseNumber;
    Button edit,delete;

    private static final String[]prefix = {"PREFIX", "BE","BIOL", "CE","CHEM", "CSE", "EE", "ENGR","GEOL", "IE","MATH", "MSE", "MAE", "NE","PHYS","SCIE"};
    ParseUser currentUser;
    List<String> Courses = new ArrayList<String>();

    String str_courseToEdit,str_prefix,tempPrefix,tempStr;
    int input_courseNum,tempNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_course);

        courseToEdit = (Spinner) findViewById(R.id.s_course_to_edit);
        coursePrefix = (Spinner) findViewById(R.id.s_new_course_prefix);
        courseNumber = (EditText) findViewById(R.id.new_course_num);
        edit = (Button) findViewById(R.id.id_edit_btn);
        delete = (Button) findViewById(R.id.id_delete_btn);

        Courses.add("My Course(s)");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student_Course");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {
                    for(int i=0;i<List.size();i++)
                    {
                        tempNum = List.get(i).getInt("course_number");
                        tempPrefix = List.get(i).getString("prefix");
                        tempStr = tempPrefix + " "+tempNum;
                        Courses.add(tempStr);

                    }

                }

                else {


                }
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditCourse.this, android.R.layout.simple_spinner_item, Courses);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseToEdit.setAdapter(adapter);

        courseToEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_courseToEdit = (String) parent.getItemAtPosition(position).toString();


                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        adapter = new ArrayAdapter<String>(EditCourse.this, android.R.layout.simple_spinner_item, prefix);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursePrefix.setAdapter(adapter);

        coursePrefix.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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

        getMenuInflater().inflate(R.menu.menu_edit_course, menu);

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

    public void openDelete(View view)
    {
        String coursePrefixToEdit;
        int courseNumToEdit;

      // coursePrefixToEdit = str_courseToEdit.split("")[0];
      //  courseNumToEdit = Integer.parseInt(str_courseToEdit.split(" ",1));


    }
    public void openEdit(View view)
    {

        Toast.makeText(getApplicationContext(), "Edited my course.", Toast.LENGTH_SHORT).show();

    }

}
