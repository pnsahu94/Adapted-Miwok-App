package com.example.miwok;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceID;

    public WordAdapter(Context context , ArrayList<Word> wordArrayList , int colorResourceID){
        super(context, 0, wordArrayList);
        mColorResourceID = colorResourceID;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);


        TextView miwokWord = (TextView) listItemView.findViewById(R.id.miwok);
        miwokWord.setText(currentWord.getmMiwokTranslation());

        TextView englishWord = (TextView) listItemView.findViewById(R.id.english);
        englishWord.setText(currentWord.getmEnglishTranslation());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        // Check if an image is provided for this word or not
        if (currentWord.hasImage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentWord.getmImageResourceID());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }



        LinearLayout textLocation = (LinearLayout) listItemView.findViewById(R.id.text_location);
        int color = ContextCompat.getColor(getContext(), mColorResourceID);
        textLocation.setBackgroundColor(color);

        return listItemView;
    }
}
