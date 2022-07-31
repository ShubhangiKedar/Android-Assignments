package com.example.customcalendarviewwithevents;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;


public class MyGridAdapter extends ArrayAdapter {
    List<Date> dates;
    Calendar currentDate;
    List<Events> events;
    LayoutInflater inflater;

    public MyGridAdapter(Context context,List<Date> dates,Calendar currentDate,List<Events> events ){
        super(context,R.layout.single_cell_layout);
        this.dates=dates;
        this.currentDate = currentDate;
        this.events= events;
        inflater= LayoutInflater.from(context);

    }

    public View getView(int position, View convertview, ViewGroup parent){
       Date monthDate  =dates.get(position);
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(monthDate);
        int DayNo = dateCalendar.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCalendar.get(Calendar.MONTH)+1;
        int displayYear = dateCalendar.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH)+1;
        int currentYear = currentDate.get(Calendar.YEAR);

       View view = convertview;
       if(view==null){
           view=inflater.inflate(R.layout.single_cell_layout,parent,false);
       }
       if(displayMonth==currentMonth && displayYear == currentYear){
           view.setBackgroundColor(getContext().getResources().getColor(R.color.green));
       }
       else {
           view.setBackgroundColor(Color.parseColor("#CCCCCC"));
       }

        TextView Day_Number = view.findViewById(R.id.calendar_day);
       TextView EventNumber = view.findViewById(R.id.events_id);

       Day_Number.setText(String.valueOf(DayNo));
       Calendar eventCalendar = Calendar.getInstance();
       ArrayList<String> arrayList = new ArrayList<>();
       for(int i =0; i<events.size() ;i++){
           eventCalendar.setTime(ConvetStringToDate(events.get(i).getDATE()));
           if(DayNo == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH)+1
           && displayYear == eventCalendar.get(Calendar.YEAR)){
               arrayList.add(events.get(i).getEVENT());
               EventNumber.setText(arrayList.size()+"Events");

           }

       }


        return view;

    }

    private  Date ConvetStringToDate(String eventDate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try{
            date = format.parse(eventDate);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }


    public int getcount(){
        return dates.size();
    }
    public  int getposition(Object item){
        return dates.indexOf(item);
    }
    public Object getItem(int position){
        return dates.get(position);
    }
}
