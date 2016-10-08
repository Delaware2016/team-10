package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageButton;

public class NewUserActivity extends ActionBarActivity {
    ImageButton btnCheckUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        btnCheckUser = (ImageButton) this.findViewById(R.id.btnCheckUser);
        btnCheckUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent nextIntent = new Intent(NewUserActivity.this, WelcomeActivity.class);
                startActivity(nextIntent);

            }
        });

    }
}
