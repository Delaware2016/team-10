package com.example.eli.unitedwaydelaware;

import java.util.ArrayList;

/**
 * Created by Eli on 10/7/2016.
 */
public class EventClass {
    
    protected String title, description, address, date;
    protected int photo;
    protected ArrayList<RegistrationList> rl;
    
    public EventClass(String title,String description,int photo, String address,String date){
        this.title=title;
        this.description=description;
        this.photo=photo;
        this.address=address;
        this.date=date;
        rl=new ArrayList<>();
        }

}
