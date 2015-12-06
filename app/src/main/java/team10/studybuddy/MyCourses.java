package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MyCourses extends AppCompatActivity {

    ParseUser currentUser = ParseUser.getCurrentUser();
    ParseObject course_info = new ParseObject("Student_Course");

    Button add,edit;
    TextView tv1;
    String prefix,temp="";
    int cnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);

        add = (Button) findViewById(R.id.btn_course_add);
        edit = (Button) findViewById(R.id.btn_course_edit);

        tv1 = (TextView) findViewById(R.id.course1);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Student_Course");
        query.whereEqualTo("user", currentUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {

                    String str = "Retrieved " + List.size() + " scores";
                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                    for(int i=0;i<List.size();i++)
                    {
                        prefix = List.get(i).getString("prefix");
                        cnumber = List.get(i).getInt("course_number");

                        temp += prefix+" "+cnumber+"\n";
                        tv1.setText(temp);


                    }

                } else {



                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_courses, menu);
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

    public void openAddCoursePage(View view)
    {
        Intent intent = new Intent(this, AddCourse.class);
        startActivity(intent);

    }
}
