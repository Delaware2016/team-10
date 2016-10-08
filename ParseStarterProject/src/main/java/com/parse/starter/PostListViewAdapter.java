package com.parse.starter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

/**
 * Created by Gokul on 10/8/2016.
 */
public class PostListViewAdapter extends ArrayAdapter<ParseObject> implements View.OnClickListener {

    private WelcomeActivity adapterTab2Activity;
    private List<ParseObject> mStatus;

    public PostListViewAdapter(WelcomeActivity tab2Activity, List<ParseObject> status) {
        super (tab2Activity, R.layout.event_list_row, status);
        this.adapterTab2Activity = tab2Activity;
        this.mStatus = status;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final ParseObject statusObject = mStatus.get(position);

        if (convertView == null){
            convertView = LayoutInflater.from(adapterTab2Activity).inflate(
                    R.layout.event_list_row, null);
            viewHolder = new ViewHolder();
            viewHolder.txtPostTitle = (TextView) convertView.findViewById(R.id.eventTitle);
            convertView.setTag(viewHolder);
            viewHolder.llPostLayout = (RelativeLayout) convertView.findViewById(R.id.rLayout);
            viewHolder.txtPostDescription = (TextView) convertView.findViewById(R.id.eventDescription);
            viewHolder.txtDistance = (TextView) convertView.findViewById(R.id.txtDistance);
            viewHolder.btnAttend = (ImageButton) convertView.findViewById(R.id.attend);
            viewHolder.btnShare = (ImageButton) convertView.findViewById(R.id.share);
            viewHolder.llPostLayout.setOnClickListener(this);
            viewHolder.btnAttend.setBackgroundResource(R.drawable.checked);

        }else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        // statusObject.getClassName();
        final String objectId = statusObject.getObjectId();
        final String eventName = statusObject.getString("EventName");
        final String description = statusObject.getString("Description");
        final String distance = statusObject.getString("Distance");
        viewHolder.txtPostTitle.setText(eventName);
        viewHolder.txtPostDescription.setText(description);
        viewHolder.txtDistance.setText(distance + "mi");

        return convertView;
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder {
        TextView txtPostTitle;
        TextView txtPostDescription;
        TextView txtDistance;
        RelativeLayout llPostLayout;
        ImageButton btnShare;
        ImageButton btnAttend;
        //ImageButton imageLike;
    }

}

