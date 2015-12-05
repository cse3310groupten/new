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

import com.parse.ParseObject;
import com.parse.ParseUser;

public class AddCourse extends AppCompatActivity {

    EditText course1,course2,course3,course4,course5;
    Button add,submit;
    private Spinner cPrefix1,cPrefix2,cPrefix3,cPrefix4,cPrefix5;
    private static final String[]prefix = {"PREFIX", "BE","BIOL", "CE","CHEM", "CSE", "EE", "ENGR","GEOL", "IE","MATH", "MSE", "MAE", "NE","PHYS","SCIE"};
    ParseUser currentUser = ParseUser.getCurrentUser();
    ParseObject course_info = new ParseObject("Class");

    String str_prefix1,str_prefix2,str_prefix3,str_prefix4,str_prefix5;
    int cNum1,cNum2,cNum3,cNum4,cNum5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        course1 = (EditText) findViewById(R.id.courseNum1);
        course2 = (EditText) findViewById(R.id.courseNum2);
        course3 = (EditText) findViewById(R.id.courseNum3);
        course4 = (EditText) findViewById(R.id.courseNum4);
        course5 = (EditText) findViewById(R.id.courseNum5);

        add = (Button) findViewById(R.id.id_course_add_btn);
        submit = (Button) findViewById(R.id.id_submit_course_btn);


        cPrefix1 = (Spinner) findViewById(R.id.sPrefix1);
        cPrefix2 = (Spinner) findViewById(R.id.sPrefix2);
        cPrefix3 = (Spinner) findViewById(R.id.sPrefix3);
        cPrefix4 = (Spinner) findViewById(R.id.sPrefix4);
        cPrefix5 = (Spinner) findViewById(R.id.sPrefix5);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddCourse.this, android.R.layout.simple_spinner_item, prefix);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cPrefix1.setAdapter(adapter);
        cPrefix2.setAdapter(adapter);
        cPrefix3.setAdapter(adapter);
        cPrefix4.setAdapter(adapter);
        cPrefix5.setAdapter(adapter);

        cPrefix1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_prefix1 = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        cPrefix2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_prefix2 = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cPrefix3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_prefix3 = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cPrefix4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_prefix4 = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cPrefix5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str_prefix5 = (String) parent.getItemAtPosition(position).toString();
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
        if(course2.getText().toString().equals("")) cNum2=0;
        else
            cNum2=Integer.parseInt(course2.getText().toString());
        if(course3.getText().toString().equals("")) cNum3=0;
        else
            cNum3=Integer.parseInt(course3.getText().toString());
        if(course4.getText().toString().equals("")) cNum4=0;
        else
            cNum4=Integer.parseInt(course4.getText().toString());
        if(course5.getText().toString().equals("")) cNum5=0;
        else
            cNum5=Integer.parseInt(course5.getText().toString());


        if((cNum1>0) && !(str_prefix1.equals("PREFIX")) )
        {
            course_info.put("prefix", str_prefix1);
            course_info.put("course_number", cNum1);
            course_info.put("user_objectId",currentUser.getObjectId());
            course_info.saveInBackground();
        }

        if((cNum2>0) && !(str_prefix2.equals("PREFIX")) )
        {
            course_info.put("prefix", str_prefix2);
            course_info.put("course_number", cNum2);
            course_info.put("user_objectId",currentUser.getObjectId());
            course_info.saveInBackground();
        }

        if((cNum3>0) && !(str_prefix3.equals("PREFIX")) )
        {
            course_info.put("prefix", str_prefix3);
            course_info.put("course_number", cNum3);
            course_info.put("user_objectId",currentUser.getObjectId());
            course_info.saveInBackground();
        }

        if((cNum4>0) && !(str_prefix4.equals("PREFIX")) )
        {
            course_info.put("prefix", str_prefix4);
            course_info.put("course_number", cNum4);
            course_info.put("user_objectId",currentUser.getObjectId());
            course_info.saveInBackground();
        }

        if((cNum5>0) && !(str_prefix5.equals("PREFIX")) )
        {
            course_info.put("prefix", str_prefix5);
            course_info.put("course_number", cNum5);
            course_info.put("user_objectId",currentUser.getObjectId());
            course_info.saveInBackground();
        }

        Toast.makeText(getApplicationContext(), "Added course(s) successfully.", Toast.LENGTH_SHORT).show();



    }

}
