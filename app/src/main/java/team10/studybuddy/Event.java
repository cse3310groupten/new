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

public class Event extends AppCompatActivity{
    CalendarView myCalendarview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        myCalendarview = (CalendarView)findViewById(R.id.calendarView);
        myCalendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Toast.makeText(getApplicationContext(), "I'm not here yet", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),""+dayOfMonth+"/"+month+"/"+year, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), month, Toast.LENGTH_SHORT).show();
                //myCalendarview.getContext();
                //Intent intent = new Intent (myCalendarview.getContext(), Agenda.class);
                Intent intent = new Intent (Event.this, Agenda.class);
                intent.putExtra("year",""+year);
                intent.putExtra("day",""+dayOfMonth);
                intent.putExtra("month",""+(++month));
                Event.this.startActivity(intent);


            }
        });



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
