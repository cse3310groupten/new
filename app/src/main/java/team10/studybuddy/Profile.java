package team10.studybuddy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Shader;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.File;

public class Profile extends AppCompatActivity implements View.OnClickListener{

    RatingBar rbRatingBar;

    TextView textViewName;
    TextView textViewEmail;
    TextView textViewMajor;
    ImageView image;
    ImageView initImage;
    Button bEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        if(ParseUser.getCurrentUser().get("picture")!=null) {
            retrieveImage();
        }

        rbRatingBar = (RatingBar) findViewById(R.id.rbRatingBar);
        bEdit = (Button) findViewById(R.id.bEdit);
        image = (ImageView) findViewById(R.id.image);

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewMajor = (TextView) findViewById(R.id.textViewMajor);
        //Retrieve Name,Email,and Major from Parse
        if((Boolean)ParseUser.getCurrentUser().get("show_firstname") == true){
            textViewName.append((CharSequence)ParseUser.getCurrentUser().get("first_name"));
        }
        textViewName.append(" ");
        if((Boolean)ParseUser.getCurrentUser().get("show_lastname") == true){
            textViewName.append((CharSequence)ParseUser.getCurrentUser().get("last_name"));
        }
        if((Boolean)ParseUser.getCurrentUser().get("show_email") == true){
            textViewEmail.setText((String) ParseUser.getCurrentUser().get("username"));
        }
        if((Boolean)ParseUser.getCurrentUser().get("show_major") == true){
            textViewMajor.setText((String) ParseUser.getCurrentUser().get("major"));
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bEdit:

                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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

    public void openEdit(View view)
    {
        Intent intent = new Intent (this, Files.class);
        startActivity(intent);

    }
    //Assign picture to profile
    private void retrieveImage() {
        // TODO Auto-generated method stub

        ParseFile showMe = (ParseFile)ParseUser.getCurrentUser().get("picture");
        showMe.getDataInBackground(new GetDataCallback() {

            @Override
            public void done(byte[] data, com.parse.ParseException e) {
                // TODO Auto-generated method stub
                if (e == null) {

                    //Turn file into image
                    Bitmap bmp = BitmapFactory
                            .decodeByteArray(data, 0, data.length);

                    image = (ImageView) findViewById(R.id.image);
                    image.setImageBitmap(bmp);

                } else {

                }

            }
        });
    }
}