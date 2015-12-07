package team10.studybuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseUser;

public class Event extends AppCompatActivity implements View.OnClickListener {
    CalendarView myCalendarview;
    Button bEventAdd;
    Button bEventEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        bEventAdd = (Button) findViewById(R.id.bEventAdd);
        bEventEdit = (Button) findViewById(R.id.bEventEdit);
        myCalendarview = (CalendarView)findViewById(R.id.calendarView);
        myCalendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Toast.makeText(getApplicationContext(), "I'm not here yet", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),""+dayOfMonth, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), month, Toast.LENGTH_SHORT).show();
                //myCalendarview.getContext();
                //Intent intent = new Intent (myCalendarview.getContext(), Agenda.class);
                Intent intent = new Intent (Event.this, Agenda.class);
                intent.putExtra("year",""+year);
                intent.putExtra("day",""+dayOfMonth);
                intent.putExtra("month",""+month);
                Event.this.startActivity(intent);


            }
        });
        bEventAdd.setOnClickListener(this);
        bEventEdit.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bEventAdd:
                startActivity(new Intent(this, AddEvent.class));



                break;

            case R.id.bEventEdit:
                startActivity(new Intent(this, EditEvent.class));

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
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
}
