package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.parse.ParseUser;

public class Settings extends AppCompatActivity {

    CheckBox fname,lname,email,major;
    Button submit;
    boolean show_fname, show_lname, show_email, show_major;
    ParseUser currentUser = ParseUser.getCurrentUser();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        fname = (CheckBox) findViewById(R.id.setting_firstname);
        lname = (CheckBox) findViewById(R.id.setting_lastname);
        email = (CheckBox) findViewById(R.id.setting_email);
        major = (CheckBox) findViewById(R.id.setting_major);

        submit = (Button)findViewById(R.id.id_submit_setting);

        show_fname = currentUser.getBoolean("show_firstname");
        show_lname = currentUser.getBoolean("show_lastname");
        show_email = currentUser.getBoolean("show_email");
        show_major = currentUser.getBoolean("show_major");

        if(!show_fname)
            fname.setChecked(false);
        if(!show_lname)
            lname.setChecked(false);
        if(!show_email)
            email.setChecked(false);
        if(!show_major)
            major.setChecked(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_setting, menu);
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

    public void submitChanges(View view){

        if(fname.isChecked())
            currentUser.put("show_firstname",true);
        else
            currentUser.put("show_firstname",false);

        if(lname.isChecked())
            currentUser.put("show_lastname",true);
        else
            currentUser.put("show_lastname",false);

        if(major.isChecked())
            currentUser.put("show_major",true);
        else
            currentUser.put("show_major",false);

        if(email.isChecked())
            currentUser.put("show_email",true);
        else
            currentUser.put("show_email",false);

        currentUser.saveInBackground();
        Toast.makeText(getApplicationContext(), "Privacy setting has been updated.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);
    }
}
