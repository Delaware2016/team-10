package com.example.eli.unitedwaydelaware;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    public static Volunteer volunteer;
    String name = "";
    String email = "";
    String password = "";
    Button submit;
    Button skip;
    EditText et, em, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et = (EditText) findViewById(R.id.vname);
        em = (EditText) findViewById(R.id.email);
        pw = (EditText) findViewById(R.id.password);
        submit = (Button) findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (et.getText().toString().trim().equals("") || em.getText().toString().trim().equals("")
                        || pw.getText().toString().trim().equals("")) {
                    Toast.makeText(Main2Activity.this, "Sorry, please enter all fields or skip", Toast.LENGTH_LONG).show();
                } else {
                    createVolunteer();
                    Intent welcomeScreenIntent = new Intent(Main2Activity.this, WelcomeScreenActivity.class);
                    startActivity(welcomeScreenIntent);
                }

            }
        });
        skip = (Button) findViewById(R.id.button2);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSkipped();
                Intent welcomeScreenIntent = new Intent(Main2Activity.this, WelcomeScreenActivity.class);
                startActivity(welcomeScreenIntent);
            }
        });
    }


    public void createVolunteer() {

        name = et.getText().toString();
        email = em.getText().toString();

        password = pw.getText().toString();
        Intent welcomeScreenIntent = new Intent(Main2Activity.this, WelcomeScreenActivity.class);
        startActivity(welcomeScreenIntent);
        volunteer = new Volunteer(name, email, password);

    }

    public void userSkipped() {
        volunteer = Globals.skipped;
    }

}

