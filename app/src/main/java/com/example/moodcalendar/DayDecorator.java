package com.example.moodcalendar;

import android.graphics.drawable.ColorDrawable;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Date;

public class DayDecorator implements DayViewDecorator {

    private CalendarDay day;
    private int color;

    public DayDecorator() {
        day = CalendarDay.today();
        //Uses ARGB
        color = 0xffd37506; // Change this to the desired color
    }

    public DayDecorator(CalendarDay day, int mood)
    {
        this.day = day;
        setColor(mood);

    }

    private void setColor(int mood)
    {
        switch(mood){
            case 1:
                color = 0xffFF1200;
                break;
            case 2:
                color = 0xffFF7400;
                break;
            case 3:
                color = 0xffFFFF00;
                break;
            case 4:
                color = 0xffC4FF00;
                break;
            case 5:
                color = 0xff00FF08;
                break;

        }
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(this.day);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setBackgroundDrawable(new ColorDrawable(color));
    }

    public void setDay(CalendarDay day)
    {
        this.day = day;
    }
}