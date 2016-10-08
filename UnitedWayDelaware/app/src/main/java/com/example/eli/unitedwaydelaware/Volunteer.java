package com.example.eli.unitedwaydelaware;

import static com.example.eli.unitedwaydelaware.Globals.vl;

/**
 * Created by Eli on 10/8/2016.
 */

public class Volunteer {
    String name;
    int score;
    double hours;
    double payments;
    boolean isRegistered;
    String email;
    String password;


    public Volunteer(String name,String email, String password){
        this.name=name;
        this.email=email;
        this.password=password;
        add();
    }
    public void add(){
        Volunteer v=new Volunteer(name,email,password);
        vl.add(v);
    }
}
