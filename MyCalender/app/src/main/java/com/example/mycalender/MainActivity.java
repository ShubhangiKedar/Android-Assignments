package com.example.mycalender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;

import static android.provider.Telephony.Mms.Part.TEXT;
import static java.nio.file.StandardOpenOption.CREATE;

public class MainActivity extends AppCompatActivity {



    private MySQLiteDBHandler dbHandler;
    private EditText editText;
    private CalendarView calendarView;
    private String selectedDate;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        editText = findViewById(R.id.editText2);
        calendarView = findViewById(R.id.calendarView2);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth);
                ReadDatabase(view);
            }
        });

        try{

            dbHandler = new MySQLiteDBHandler(this, “CalendarDatabase”, null,1);
            sqLiteDatabase = dbHandler.getWritableDatabase();
            sqLiteDatabase.execSQL(“CREATE TABLE EventCalendar(Date TEXT, Event TEXT)”);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void InsertDatabase(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put(“Date”,selectedDate);
        contentValues.put(“Event”, editText.getText().toString());
        sqLiteDatabase.insert(“EventCalendar”, null, contentValues);

    }

    public void ReadDatabase(View view){
        String query = “Select Event from EventCalendar where Date = ” + selectedDate;
        try{
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            cursor.moveToFirst();
            editText.setText(cursor.getString(0));
        }
        catch (Exception e){
            e.printStackTrace();
            editText.setText(“ ”);
        }
    }

}

