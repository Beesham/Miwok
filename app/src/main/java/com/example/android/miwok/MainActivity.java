/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numbersTextview = (TextView) findViewById(R.id.numbers);
        TextView phrasesTextview = (TextView) findViewById(R.id.phrases);
        TextView familyTextview = (TextView) findViewById(R.id.family);
        TextView colorsTextview = (TextView) findViewById(R.id.colors);

        numbersTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNumbersActivity();
            }
        });

        phrasesTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPhrasesActivity();
            }
        });

        familyTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFamilyActivity();
            }
        });

        colorsTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startColorsActivity();
            }
        });
    }


    public void startNumbersActivity(){
        Intent i = new Intent(this, NumbersActivity.class);
        startActivity(i);
    }

    public void startPhrasesActivity(){
        Intent i = new Intent(this, PhrasesActivity.class);
        startActivity(i);
    }

    public void startFamilyActivity(){
        Intent i = new Intent(this, FamilyActivity.class);
        startActivity(i);
    }

    public void startColorsActivity(){
        Intent i = new Intent(this, ColorsActivity.class);
        startActivity(i);
    }
}
