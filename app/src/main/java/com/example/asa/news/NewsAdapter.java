package com.example.asa.news;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends ArrayAdapter<News> {


    //Constructor
    public NewsAdapter(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ArrayList<Integer> backgrounds = new ArrayList<>();
        backgrounds.add(R.drawable.item_background_education);
        backgrounds.add(R.drawable.item_background_music);
        backgrounds.add(R.drawable.item_background_opinion);
        backgrounds.add(R.drawable.item_background_politics);
        backgrounds.add(R.drawable.item_background_science);
        backgrounds.add(R.drawable.item_background_society);

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        News newsCurrent = getItem(position);

        RelativeLayout relativeLayout = (RelativeLayout) listItemView.findViewById(R.id.list_item);

        TextView textViewTitle = (TextView) listItemView.findViewById(R.id.title);
        textViewTitle.setText(newsCurrent.getmTitle());

        TextView textViewCatagory = (TextView) listItemView.findViewById(R.id.catagory);
        textViewCatagory.setText(newsCurrent.getmCatagory());
        int backgroundIndex = getBackground(newsCurrent.getmCatagory());
        relativeLayout.setBackgroundResource(backgrounds.get(backgroundIndex));

        Date timeFull = stringToDate(newsCurrent.getmTime());

        TextView textViewDate = (TextView) listItemView.findViewById(R.id.date);
        textViewDate.setText(formatDate(timeFull));

        TextView textViewTime = (TextView) listItemView.findViewById(R.id.time);
        textViewTime.setText(formatTime(timeFull));


        return listItemView;
    }

    //Parsing time from JSON response
    private Date stringToDate(String timeString) {
        Date d = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD'T'hh:mm:ss'Z'");
        try {
            d = dateFormat.parse(timeString);

        } catch (ParseException e) {
            Log.e("NewsAdapter", "ParseException", e);
        }
        return d;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getBackground(String s) {
        switch (s) {
            case "Music":
                return 1;
            case "Opinion":
                return 2;
            case "Politics":
                return 3;
            case "Science":
                return 4;
            case "Society":
                return 5;
            default:
                return 0;
        }
    }
}
