package com.example.datetimepicker;


import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

        DatePicker picker;
        Button btn;
        TextView t;
        TextView time;
        TimePicker simpleTimePicker;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            t=(TextView)findViewById(R.id.textView1);
            picker=(DatePicker)findViewById(R.id.datePicker1);
            btn=(Button)findViewById(R.id.button1);
            btn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    t.setText("Selected Date: "+ picker.getDayOfMonth()+"/"+ (picker.getMonth() + 1)+"/"+picker.getYear());
                }
            });


        }
    }




