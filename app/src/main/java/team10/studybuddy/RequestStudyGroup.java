package team10.studybuddy;

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
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RequestStudyGroup extends AppCompatActivity {

    Spinner s_myCourse;
    EditText studentListView;

    List<String> myCoursesList = new ArrayList<String>();
    List<String> StudentList = new ArrayList<String>();
    String[] inputCourse;
    boolean InputVerified=false, printStudent=false;
    boolean flag=false;
    ParseQuery<ParseObject> query;
    ParseUser tempUser;

    int tempNum;
    String tempPrefix,tempStr,str_mycourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_study_group);

        s_myCourse = (Spinner) findViewById(R.id.id_group_myCourses);
        studentListView = (EditText) findViewById(R.id.StList);

        myCoursesList.add("My Course(s)");
        query = ParseQuery.getQuery("Student_Course");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < List.size(); i++) {
                        tempNum = List.get(i).getInt("course_number");
                        tempPrefix = List.get(i).getString("prefix");
                        tempStr = tempPrefix + " " + tempNum;
                        myCoursesList.add(tempStr);

                    }


                } else {


                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RequestStudyGroup.this, android.R.layout.simple_spinner_item,myCoursesList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s_myCourse.setAdapter(adapter);
        s_myCourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_mycourse = (String) parent.getItemAtPosition(position).toString();

                if (str_mycourse != ("My Course(s)")) {
                    InputVerified = true;
                    inputCourse = str_mycourse.split(" ", 2);
                }

                if(InputVerified){
                    query = ParseQuery.getQuery("Student_Course");

                    query.whereEqualTo("prefix", inputCourse[0]);
                    query.whereEqualTo("course_number",Integer.parseInt(inputCourse[1]));
                    query.findInBackground(new FindCallback<ParseObject>() {
                        public void done(List<ParseObject> List, ParseException e) {
                            if (e == null) {

                                for(int i=0;i<List.size();i++) {
                                    tempUser =List.get(i).getParseUser("user");


                                }





                            }
                            else {


                            }
                        }
                    });
                }



                }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        String test=printStudent +".";
        Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT).show();





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


}
