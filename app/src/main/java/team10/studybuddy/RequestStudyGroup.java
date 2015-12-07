package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RequestStudyGroup extends AppCompatActivity {

    Spinner myCourse;
    TextView StudyGroup;

    List<String> myCourseList = new ArrayList<String>();

    String[] inputCourse;
    boolean inputVerified = false;


    ParseQuery<ParseObject> query;
    ParseObject tempObject;

    int tempNum;
    String tempStr,input_course,result="";
    String tempPrefix;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_study_group);

        myCourse = (Spinner) findViewById(R.id.id_group_myCourses);
        StudyGroup = (TextView) findViewById(R.id.studygroupListview);
        myCourseList.add("My Course(s)");
        query = ParseQuery.getQuery("Student_Course");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < List.size(); i++) {
                        tempPrefix = List.get(i).getString("prefix");
                        tempNum = List.get(i).getInt("course_number");

                        tempStr = tempPrefix + " " + tempNum;
                        myCourseList.add(tempStr);

                    }

                } else {
                }
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RequestStudyGroup.this, android.R.layout.simple_spinner_item,myCourseList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myCourse.setAdapter(adapter);
        myCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                input_course = (String) parent.getItemAtPosition(position).toString();
                if (input_course != "My Course(s)") {
                    inputVerified = true;
                    inputCourse = tempStr.split(" ", 2);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student_Course");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.whereEqualTo("inGroup",true);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {

                    String str = "Retrieved " + List.size() + " study group(s).";
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < List.size(); i++) {
                        tempPrefix = List.get(i).getString("prefix");
                        tempNum = List.get(i).getInt("course_number");


                        result+= tempPrefix + " " + tempNum + "\n";
                        StudyGroup.setText(result);


                    }

                    if(List.size()==0)
                        StudyGroup.setText("No study group available this time");

                } else {


                }
            }
        });

    }




        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_request_study_group, menu);
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




    public void joinGroup(final View view){

        if(inputVerified){

        query = ParseQuery.getQuery("Student_Course");
            query.whereEqualTo("user",ParseUser.getCurrentUser());
        query.whereEqualTo("prefix", inputCourse[0]);
        query.whereEqualTo("course_number", Integer.parseInt(inputCourse[1]));

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> List, ParseException e) {
                    if (e == null) {

                        tempObject = List.get(0);
                        tempObject.put("inGroup", true);
                        tempObject.saveInBackground();
                            Toast.makeText(getApplicationContext(), "joined in study group", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(view.getContext(), Course.class));
                    } else {
                    }
                }
            });

    }

    else
        {
            Toast.makeText(getApplicationContext(), "Choose my course", Toast.LENGTH_SHORT).show();

        }


}


    public void leaveGroup(final View view){

        if(inputVerified){

            query = ParseQuery.getQuery("Student_Course");
            query.whereEqualTo("user",ParseUser.getCurrentUser());
            query.whereEqualTo("prefix", inputCourse[0]);
            query.whereEqualTo("course_number", Integer.parseInt(inputCourse[1]));

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> List, ParseException e) {
                    if (e == null) {

                        tempObject = List.get(0);
                        tempObject.put("inGroup", false);
                        tempObject.saveInBackground();
                        Toast.makeText(getApplicationContext(), "You're no more in the study group", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(view.getContext(), Course.class));
                    } else {
                    }
                }
            });

        }

        else
        {
            Toast.makeText(getApplicationContext(), "Choose my course", Toast.LENGTH_SHORT).show();

        }}



}
