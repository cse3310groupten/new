package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.ParseClassName;

@ParseClassName("Course")

public class Course extends AppCompatActivity {

    Button add, request, mycourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);


        add = (Button) findViewById(R.id.id_course_add_btn);
        request = (Button) findViewById(R.id.request_btn);
        mycourse= (Button) findViewById(R.id.my_course_btn);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
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

    public void openMyCourses(View view)
    {
        Intent intent = new Intent (this, MyCourses.class);
        startActivity(intent);

    }

    public void openRequest(View view)
    {
        Intent intent = new Intent (this, RequestStudyGroup.class);
        startActivity(intent);
    }
}
