package com.example.eli.unitedwaydelaware;

import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Eli on 10/8/2016.
 */

public class VolunteerList {
    ArrayList<Volunteer> volunteers = new ArrayList<>();
    private int counter = 0;

    public void add(Volunteer volunteer) {
        volunteers.add(counter, volunteer);
    }

}
