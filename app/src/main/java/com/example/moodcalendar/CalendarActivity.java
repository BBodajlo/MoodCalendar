package com.example.moodcalendar;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.List;

public class CalendarActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        MaterialCalendarView MoodCalendar = findViewById(R.id.moodCalendar);

        //Get the mood from the calendar and make it sent the calendar color
        Uri uri = CalendarContract.Events.CONTENT_URI;


        if (ContextCompat.checkSelfPermission(
                this, android.Manifest.permission.READ_CALENDAR

        ) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.

        } else {
            // You can directly ask for the permission.
            // The registered ActivityResultCallback gets the result of this request.
            requestPermissionLauncher.launch(
                    android.Manifest.permission.READ_CALENDAR

            );
        }
        ;


        String[] fields =
                {
                        CalendarContract.Events._ID,
                        CalendarContract.Events.TITLE,
                        CalendarContract.Events.DESCRIPTION,
                        CalendarContract.Events.DTSTART

                };

        String selection = CalendarContract.Events.TITLE + " = ?";
        String[] selectionArgs = {"Mood"};

        Cursor cursor = getContentResolver().query(uri, fields, selection, selectionArgs, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve event details
                @SuppressLint("Range") long eventId = cursor.getLong(cursor.getColumnIndex(CalendarContract.Events._ID));
                @SuppressLint("Range") String eventName = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.TITLE));
                @SuppressLint("Range") String moodValue = cursor.getString(cursor.getColumnIndex(CalendarContract.Events.DESCRIPTION));
                @SuppressLint("Range") long eventDate = cursor.getLong(cursor.getColumnIndex(CalendarContract.Events.DTSTART));


                System.out.println("This is event name " + eventName);
                System.out.println("This is the moodValue " + moodValue);
                System.out.println("This is the date of the event: " + eventDate);

                //Date for the event
                Calendar eventCalendar = Calendar.getInstance();
                eventCalendar.setTimeInMillis(eventDate);

                CalendarDay eventCalendarDay = CalendarDay.from(
                        eventCalendar.get(Calendar.YEAR),
                        eventCalendar.get(Calendar.MONTH),
                        eventCalendar.get(Calendar.DAY_OF_MONTH)
                );

                DayDecorator dayDecorator = new DayDecorator(eventCalendarDay, Integer.parseInt(moodValue));
                MoodCalendar.addDecorator(dayDecorator);


            } while (cursor.moveToNext());

            if (cursor != null) {
                cursor.close();
            }
        }
    }
    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            new ActivityResultCallback<Boolean>() {
                @Override
                public void onActivityResult(Boolean result) {
                    if (result) {
                        // PERMISSION GRANTED
                    } else {
                        // PERMISSION NOT GRANTED
                    }
                }
            }
    );
}
