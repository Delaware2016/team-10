package com.example.eli.unitedwaydelaware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eli on 10/7/2016.
 */
public class WelcomeScreenActivity extends AppCompatActivity {

    private List<EventClass> events;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycle_view);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();
    }

    private void initializeData() {
        events = new ArrayList<>();
        events.add(new EventClass("Volunteer this Friday at the River!", "Come out for a ton of fun" +
                " in the sun", R.drawable.lion, "https://www.google.com/maps/place/Delaware+River/data=" +
                "!4m2!3m1!1s0x89c47c43049f45eb:0x85717b8ab2474015?sa=X&ved=0ahUKEwiKmcv0nsrPAhUDyT4KHQozC3UQ8gEIGzAA",
                "11/12/2016"));
        events.add(new EventClass("Volunteer this Monday at Della Ware's House!", "Come out for a ton of fun" +
                " in the dress", R.drawable.lion, "40.741895,-73.989308",
                "11/12/2016"));
    }
    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(events,this);
        rv.setAdapter(adapter);
    }
}
