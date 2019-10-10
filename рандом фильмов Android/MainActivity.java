//package com.example.myapplication;
package com.example.pc.myfilms;

import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import java.util.Arrays;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Vector;

public class MainActivity extends AppCompatActivity
{
    String[] movies;
    TextView filmTitle;
    int index = 0;
    boolean[] itused;
    int  count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies=getResources().getStringArray(R.array.MyMovies);
        filmTitle=findViewById(R.id.film_title);
        itused = new boolean[movies.length];
        Log.d("mytag", "function onCreate called");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("mytag", "function onResume called");
    }

    public void onClick(View v)
    {
        if(count == movies.length && v.getId() != R.id.drop)
        {
            filmTitle.setText("You have already watched all the films.!");
            return;
        }
        if(v.getId() == R.id.rand)
        {
            index =(int)(Math.random() * movies.length);
            while(itused[index])    //пока не все отмечены
            {
                index = (int)(Math.random()* movies.length);
            }
            itused[index] = true;   //помечаем фильм
            count++;    //считаем просмотренные фильмы
            filmTitle.setText(movies[index]);
        }
        if(v.getId() == R.id.drop)
        {
            filmTitle.setText("Choose a movie");
            for(int i=0;i<movies.length;i++)
            {
                itused[i] =false;
            }
            count = 0;
        }
    }
}