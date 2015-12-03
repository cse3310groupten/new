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
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class Registration extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;

    EditText etName;
    EditText etUsername;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        bRegister= (Button) findViewById(R.id.bRegister);

        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        bRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bRegister:
                String name = etName.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                //User user = new User(name, username, password);

                ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
                query.whereEqualTo("Username", "user1.uta.edu");

                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            // The query was successful to find existing username
                            showMessage();

                        } else {
                            // create new account holder
                            ParseObject testUser = new ParseObject("User");

                            testUser.put("first_name", "Brian");
                            testUser.put("last_name", "Wong");
                            testUser.put("password", "0000");
                            testUser.put("username","user1@uta.edu");
                            testUser.put("major","CE");
                            testUser.put("no_of_ratings",0);
                            testUser.put("rate",0);
                            //privacy settings are initially updated as default(return true)
                            testUser.put("show_firstname",true);
                            testUser.put("show_lastname",true);
                            testUser.put("show_email",true);
                            testUser.put("show_gender",true);
                            testUser.put("show_major",true);
                            testUser.saveInBackground();



                        }
                    }
                });



                // Create user using ParseObject

                startActivity(new Intent(this, Login.class));
                break;
        }
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
