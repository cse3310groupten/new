package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

public class Registration extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;

    EditText etFirstName, etLastName;
    EditText etUsername;
    EditText etPassword, etRePassword;

    ParseUser newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        bRegister= (Button) findViewById(R.id.bRegister);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etRePassword = (EditText) findViewById(R.id.etRePassword);

        bRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bRegister:
                newUser = new ParseUser();
                final String first_name = etFirstName.getText().toString();
                final String last_name = etLastName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                String rePassword = etRePassword.getText().toString();

                if (new String(password).equals(new String(rePassword)))
                {
                    newUser.put("first_name", first_name);
                    newUser.put("last_name", last_name);
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(username); //username is their email
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e)
                        {
                            if (e == null)
                            {
                                Toast.makeText(getApplicationContext(), "Account created.", Toast.LENGTH_SHORT).show();
                                ParseUser newUser = ParseUser.getCurrentUser();
                                newUser.setACL(new ParseACL(newUser));

                                allowAccess();
                            }
                            else
                            {
                                // Sign up didn't succeed. Look at the ParseException
                                // to figure out what went wrong
                                switch(e.getCode())
                                {
                                    case ParseException.CONNECTION_FAILED:
                                         Toast.makeText(getApplicationContext(), "Connection Failure", Toast.LENGTH_SHORT).show();
                                         break;
                                    case ParseException.ACCOUNT_ALREADY_LINKED:
                                         Toast.makeText(getApplicationContext(), "Account Already Linked.", Toast.LENGTH_SHORT).show();
                                         break;
                                    case ParseException.INVALID_ACL:
                                         Toast.makeText(getApplicationContext(), "Invalid ACL", Toast.LENGTH_SHORT).show();
                                         break;
                                    case ParseException.MUST_CREATE_USER_THROUGH_SIGNUP:
                                         Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                         break;
                                    case ParseException.USERNAME_TAKEN:
                                         Toast.makeText(getApplicationContext(), "Username Taken", Toast.LENGTH_SHORT).show();
                                         break;
                                }
                            }
                        }
                    });
                      // Create user using ParseObject

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Passwords are not matching", Toast.LENGTH_SHORT).show();
                }
                break;
            }

        }


    private void allowAccess() {
        startActivity(new Intent(this, MainMenu.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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

    public void inputVerification(View view){

        openMenu(view);
    }

    public void openMenu(View view) {

        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }

    public void showMessage(){

        Toast.makeText(getApplicationContext(), "Existing username.", Toast.LENGTH_SHORT).show();

    }
}
