package com.example.moodcalendar;

import static com.example.moodcalendar.CalendarQuickstart.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;

/*TODO:
    - Check to see if day already has a mood; if so overwrite it.
    - Figure out how to implement stats for a month shown
    - Potentially allow editing of moods by tapping on day on calendar
    - Push notifications to remind to set mood
 */


public class MainActivity extends AppCompatActivity {

    private Button calendarButton;
    private Button moodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarButton = findViewById(R.id.calendarButton);
        moodButton = findViewById(R.id.moodButton);

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalendarActivity.class));
            }
        });

        moodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSetMood();
            }
        });



    }

    private void moveToSetMood()
    {
        startActivity(new Intent(MainActivity.this, MoodSetterActivity.class));

    }


}

