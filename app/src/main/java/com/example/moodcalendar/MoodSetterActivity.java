package com.example.moodcalendar;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MoodSetterActivity extends AppCompatActivity {

    private RadioGroup moodGroup;
    private Button setMoodButton;
    private String currentMood;
    private TextView redArrow;
    private TextView orangeArrow;
    private TextView yellowArrow;
    private TextView ygArrow;
    private TextView greenArrow;
    private ArrayList<TextView> arrowList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodsetter);
        moodGroup = findViewById(R.id.moodGroup);
        redArrow = findViewById(R.id.redArrow);
        orangeArrow = findViewById(R.id.orangeArrow);
        yellowArrow = findViewById(R.id.yellowArrow);
        ygArrow = findViewById(R.id.ygArrow);
        greenArrow = findViewById(R.id.greenArrow);
        setMoodButton = findViewById(R.id.setMoodButton);

        arrowList = new ArrayList<>(Arrays.asList(redArrow,yellowArrow,orangeArrow,ygArrow,greenArrow));

        moodGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int mood) {
                switch(mood){
                    case R.id.redMood:
                        setAlphasToOneAndOthersToZero(redArrow);
                        currentMood = "1";
                        break;
                    case R.id.orangeMood:
                        setAlphasToOneAndOthersToZero(orangeArrow);
                        currentMood = "2";
                        break;
                    case R.id.yellowMood:
                        setAlphasToOneAndOthersToZero(yellowArrow);
                        currentMood = "3";
                        break;
                    case R.id.ygMood:
                        setAlphasToOneAndOthersToZero(ygArrow);
                        currentMood = "4";
                        break;
                    case R.id.greenMood:
                        setAlphasToOneAndOthersToZero(greenArrow);
                        currentMood = "5";
                        break;


                }
            }
        });

        setMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMood();
            }
        });

    }


    private void setAlphasToOneAndOthersToZero(TextView thisArrowToOne)
    {

        for (TextView currentView : arrowList)
        {
            if(currentView == thisArrowToOne)
            {
                currentView.setAlpha(1);
            }
            else
            {
                currentView.setAlpha(0);
            }

        }

    }

    private void setMood()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long startTime = calendar.getTimeInMillis();
        long endTime = startTime + (60 * 60 * 1000); // Set the event duration to 1 hour


        String title = "Mood";
        String description = currentMood;

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.DESCRIPTION, description);

        startActivity(intent);


    }
}
