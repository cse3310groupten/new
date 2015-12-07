package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.text.ParseException;

public class Login extends AppCompatActivity implements View.OnClickListener{

    Button bLogin;
    EditText etUsername;
    EditText etPassword;
    TextView tvRegisterLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bLogin = (Button) findViewById(R.id.bLogin);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);

        //Parse.initialize(this, "eiVK4YPZpFbKBxwKD3PlUpSdDflusDQEZhhlPaWd", "hRw4QGOAYJKnWGS0BJuhRH6xUtRVpLrvGHz393PL");


        bLogin.setOnClickListener(this);
        tvRegisterLink.setOnClickListener(this);

        ParseUser user = new ParseUser();

        //ParseObject.registerSubclass(Course.class);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bLogin:
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, com.parse.ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                            allowAccess();
                        } else {
                            // Signup failed. Look at the ParseException to see what happened
                            Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



                break;

            case R.id.tvRegisterLink:
                startActivity(new Intent(this, Registration.class));

                break;
        }
    }

    private void allowAccess() {
        startActivity(new Intent(this, MainMenu.class));
    }

    @Override
    public void onBackPressed()
    {
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void userVerification(View view){

        openMenu(view);
    }

    public void openMenu(View view)    {

        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }
}
