package com.example.eli.unitedwaydelaware;

import android.widget.Button;

/**
 * Created by Eli on 10/8/2016.
 */

public class Globals {
    public static VolunteerList vl;
    public static Volunteer skipped;

    public Globals(){
        vl=new VolunteerList();
        skipped=new Volunteer("noname","null","null");
    }
}
