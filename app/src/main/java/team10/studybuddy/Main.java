package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseObject;

public class Main extends AppCompatActivity implements View.OnClickListener{

    Button btn_login;
    Button btn_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "eiVK4YPZpFbKBxwKD3PlUpSdDflusDQEZhhlPaWd", "hRw4QGOAYJKnWGS0BJuhRH6xUtRVpLrvGHz393PL");

        /*Create buttons*/
        btn_login = (Button) findViewById(R.id.id_btn_login);
        btn_sign_up = (Button) findViewById(R.id.id_btn_sign_up);

        btn_login.setOnClickListener(this);
        btn_sign_up.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.id_btn_login:
                startActivity(new Intent(this,Login.class));
                break;

            case R.id.id_btn_sign_up:
                startActivity(new Intent(this,Registration.class));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
/*
    public void openLoginPage(View view)    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void openRegistrationPage(View view)    {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
*/
}
