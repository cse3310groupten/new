package team10.studybuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    CheckBox fname,lname,email,gender,major;
    Button submit;
    String username;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        fname = (CheckBox) findViewById(R.id.setting_firstname);
        lname = (CheckBox) findViewById(R.id.setting_lastname);
        email = (CheckBox) findViewById(R.id.setting_email);
        gender = (CheckBox) findViewById(R.id.setting_gender);
        major = (CheckBox) findViewById(R.id.setting_major);

        submit = (Button)findViewById(R.id.id_submit_setting);
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

    public void showMessage(View view){

        Toast.makeText(getApplicationContext(), "Privacy setting has been updated.", Toast.LENGTH_SHORT).show();

    }
}
