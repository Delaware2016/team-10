/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    LinearLayout llLogin;
    String userName = null;
    String email = null;

    public static final List<String> mPermissions = new ArrayList<String>(){{
        add("public_profile");
        add("email");
    }};

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
        FacebookSdk.sdkInitialize(getApplicationContext());

        llLogin = (LinearLayout) this.findViewById(R.id.llLoginFacebook);
        llLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                Intent welcomeIntent = new Intent(MainActivity.this, NewUserActivity.class);
                startActivity(welcomeIntent);

            }
        });
    }
    private void getUserDetailsFromParse(){
        ParseUser parseUser = ParseUser.getCurrentUser();
        Toast.makeText(MainActivity.this, "Welcome back " + parseUser.getUsername(), Toast.LENGTH_LONG).show();
    }
    private void getUserDetailsFromFB(){
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                try {
                    userName = jsonObject.getString("name");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    email = jsonObject.getString("email");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                saveNewUser();
            }
        });
        Bundle paramBundle = new Bundle();
        paramBundle.putString("fields", "name,email");
        request.setParameters(paramBundle);
        request.executeAsync();
    }

    private void saveNewUser(){
        ParseUser user = ParseUser.getCurrentUser();
        user.setUsername(userName);
        user.setEmail(email);
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                //Toast.makeText(MainActivity.this, "success", Toast.LENGTH_LONG).show();
            }
        });
    }

    //stakeit
    //bidoptions - 1 hour
    //UI - 30 min
    //images higher quality
    //navbar - 30 min
    //flagging - 1 hr
    //comments - add color code - - 30 min
    //Picxel
    //camera - 2 hours - done tonight
    //fitbit integration - 2 hours
    //Gridview - 1 hour - done tonight
    //Geolocation picture retrieval - 1 hour
    //Map of geopoints - 1 hour
    //TextApp
    //Map

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
