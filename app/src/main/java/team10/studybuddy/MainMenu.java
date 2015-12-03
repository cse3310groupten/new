package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity implements View.OnClickListener{

    Button btn_profile;
    Button btn_course;
    Button btn_rate;
    Button btn_event;
    Button btn_notification;
    Button btn_setting;
    Button btn_application;
    Button btn_logout;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btn_profile = (Button) findViewById(R.id.id_btn_menu_profile);
        btn_course = (Button) findViewById(R.id.id_btn_menu_course);
        btn_rate = (Button) findViewById(R.id.id_btn_menu_rate);
        btn_event = (Button) findViewById(R.id.id_btn_menu_event);
        btn_notification = (Button) findViewById(R.id.id_btn_menu_notification);
        btn_setting =(Button) findViewById(R.id.id_btn_menu_setting);
        btn_application =(Button) findViewById(R.id.id_btn_menu_application);
        btn_logout = (Button) findViewById(R.id.id_btn_logout);

        btn_logout.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        if (authenticate() == true){
        }
        else{

        }
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.id_btn_logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this, Login.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void openProfile(View view)    {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void openCourse(View view)    {
        Intent intent = new Intent(this, Course.class);
        startActivity(intent);
    }

    public void openRate(View view)    {
        Intent intent = new Intent(this, Rate.class);
        startActivity(intent);
    }

    public void openEvent(View view)    {
        Intent intent = new Intent(this, Event.class);
        startActivity(intent);
    }

    public void openNotification(View view)    {
        Intent intent = new Intent(this, Notification.class);
        startActivity(intent);
    }

    public void openSetting(View view)    {
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    public void openApplication(View view)    {
        Intent intent = new Intent(this, Application.class);
        startActivity(intent);
    }



}
