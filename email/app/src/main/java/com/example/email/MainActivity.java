package com.example.email;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

        Button buttonSend;
        EditText textTo;
        EditText textSubject;
        EditText textMessage;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            buttonSend = (Button) findViewById(R.id.button1);
            textTo = (EditText) findViewById(R.id.editTextTo);
            textSubject = (EditText) findViewById(R.id.editTextSubject);
            textMessage = (EditText) findViewById(R.id.editText1);

            buttonSend.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    String to = textTo.getText().toString();
                    String subject = textSubject.getText().toString();
                    String message = textMessage.getText().toString();

                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                    email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    email.putExtra(Intent.EXTRA_TEXT, message);
                    email.setType("message/rfc822");

                    startActivity(Intent.createChooser(email, "Choose an Email client :"));

                }
            });
        }
    }

