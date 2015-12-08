package team10.studybuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class SearchByName extends AppCompatActivity {

    EditText name;
    TextView nameFound;
    Button yes,no,search;
    String nameInput,tempStr,result="",getName;


    ParseQuery query;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_name);

        name = (EditText) findViewById(R.id.id_search_name_edit);
        nameFound = (TextView) findViewById(R.id.FoundName);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        search= (Button)findViewById(R.id.btn_search_by_name);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    public void pressedSearch(View view)
    {
        getName="";
                nameInput = name.getText().toString();
        if (nameInput.equals(""))
            Toast.makeText(getApplicationContext(), "empty string", Toast.LENGTH_SHORT).show();

        else {
            query = ParseQuery.getQuery("_User");
            query.whereEqualTo("first_name",nameInput);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> List, ParseException e) {
                    if (e == null) {

                        if (!(List.size() == 0)) {

                            if (getName == "") {
                                getName = List.get(0).getString("first_name") + " " + List.get(0).getString("last_name");
                                nameFound.setText(getName);
                            }
                        } else {

                        }
                    } else {
                    }
                }
            });

            if (getName.equals("")) {
                query = ParseQuery.getQuery("_User");
                query.whereEqualTo("last_name", nameInput);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> List, ParseException e) {
                        if (e == null) {

                            if (List.size() == 0) {

                            } else {
                                if(getName=="") {
                                    getName = List.get(0).getString("first_name") + " " + List.get(0).getString("last_name");
                                    nameFound.setText(getName);
                                }

                            }


                        } else {
                        }
                    }
                });


            }


        }




    }

    public void pressedYes(View view)
    {
        startActivity(new Intent(this,AddRate.class));

    }

    public void pressedNo(View view)
    {
        getName="";
        nameFound.setText(getName);
    }

}
