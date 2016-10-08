package com.example.eli.unitedwaydelaware;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Eli on 10/7/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.EventViewHolder>{

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView eventTitle;
        TextView eventDescription;
        ImageView eventPhoto;
        TextView date;
        ImageButton btnShare;
        ImageButton btnMaps;

        EventViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            eventTitle = (TextView)itemView.findViewById(R.id.event_title);
            eventDescription = (TextView)itemView.findViewById(R.id.event_description);
            eventPhoto = (ImageView)itemView.findViewById(R.id.event_photo);
            date=(TextView)itemView.findViewById(R.id.txtDate);
            btnShare=(ImageButton)itemView.findViewById(R.id.btnShare);
            btnMaps=(ImageButton)itemView.findViewById(R.id.btnMaps);
        }
    }
    List<EventClass> events;

    RVAdapter(List<EventClass> events){
        this.events = events;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        EventViewHolder evh = new EventViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(final EventViewHolder eventHolder, final int position) {
        eventHolder.eventTitle.setText(events.get(position).title);
        eventHolder.eventDescription.setText(events.get(position).description);
        eventHolder.eventPhoto.setImageResource(events.get(position).photo);
        eventHolder.date.setText(events.get(position).date);
        eventHolder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, eventHolder.eventTitle.getText()+
                        "\n"+eventHolder.eventDescription.getText());
                sendIntent.setType("text/plain");
                v.getContext().startActivity(sendIntent);
            }
        });
        eventHolder.btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(events.get(position).address));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}