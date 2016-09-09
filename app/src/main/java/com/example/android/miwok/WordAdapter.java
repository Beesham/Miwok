package com.example.android.miwok;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Beesham on 06-09-16.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private int backgroundColorResourceId;

    public WordAdapter(Context context, List<Word> objects, int colorResourceId) {
        super(context, 0, objects);
        backgroundColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView defaultTranslationTextview = (TextView) listItemView.findViewById(R.id.english_word_textview);
        TextView miwokTranslationTextview = (TextView) listItemView.findViewById(R.id.miwok_word_textview);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.category_linear_layout);

        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), backgroundColorResourceId));
        defaultTranslationTextview.setText(currentWord.getDefault_translation());
        miwokTranslationTextview.setText(currentWord.getMiwok_translation());

        if(currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
        }else{
            imageView.setVisibility(View.GONE);
        }


        return listItemView;
    }
}
