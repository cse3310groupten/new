package team10.studybuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class AddRate extends AppCompatActivity {

    String id,input;
    int inputNum;
    Spinner ratingSpinner;
    Button submit;
    boolean inputVerified=false;

    ParseQuery<ParseObject> query;
    ParseObject tempObj;
    private static final String[] rateArr = {"RATE", "1","2","3","4","5"};
    int noRates, rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        ratingSpinner = (Spinner) findViewById(R.id.s_rating_Scale);
        submit = (Button) findViewById(R.id.btn_rate_submit);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddRate.this, android.R.layout.simple_spinner_item, rateArr);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ratingSpinner.setAdapter(adapter);

        ratingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               input= (String) parent.getItemAtPosition(position).toString();
                if(input!="RATE")
                {
                   inputNum= Integer.parseInt(input);
                    inputVerified =true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void pressedSubmit(final View view)
    {

    if(inputVerified) {
        query = ParseQuery.getQuery("_User");
        query.whereEqualTo("objectId",id);
        query.findInBackground
                (new FindCallback<ParseObject>() {
            public void done(List<ParseObject> List, ParseException e) {
                if (e == null) {


                    tempObj = List.get(0);
                    noRates = tempObj.getInt("no_of_ratings");
                    rating = tempObj.getInt("rate");

                    rating=rating+inputNum;
                    noRates=noRates+1;

                    tempObj.fetchInBackground(new GetCallback<ParseObject>() {
                        public void done(ParseObject object, ParseException e) {
                            if (e == null) {
                                object.put("no_of_ratings", noRates);
                                object.put("rate", rating);
                                object.fetchInBackground();
                                object.saveInBackground();
                            } else {
                            }

                        }

                    });

                   tempObj.saveInBackground();
                    tempObj.fetchInBackground();

                    String str = "Updated rating successfully";

                    Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(view.getContext(), Rate.class));
                }

                else {

                }
            }
        });


    }
    else {
        Toast.makeText(getApplicationContext(), "Please choose scale to rate.", Toast.LENGTH_SHORT).show();
    }

    }
}
