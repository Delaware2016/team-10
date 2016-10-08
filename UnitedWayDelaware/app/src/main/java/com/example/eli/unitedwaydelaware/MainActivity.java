package com.example.eli.unitedwaydelaware;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.stripe.android.*;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.AuthenticationException;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates button submit and sets the onclicklistener
        submit = (Button) findViewById(R.id.btnSubmit);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                performCheck();
//            }
//        });

        PaymentManager manager = new PaymentManager(
                "4242-4242-4242-4242", 12, 2017, "123", getApplicationContext()
        );

        try {
            manager.chargeCard(500);
        } catch (Exception e) {
            manager.message("Something broke!");
            manager.message(e.getMessage());
        }
    }
}
