package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Second extends AppCompatActivity
{

    TextView q;
    int min, max;   //верхняя и нижняя граница
    int result;     //загаданное число


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent i = getIntent();
        q = findViewById(R.id.question);

        min = i.getIntExtra("min", 0);
        max = i.getIntExtra("max", 0);
        result = (max - min)/ 2 + min;
        q.setText(">" + result);
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.yes:  //если пользователь нажал да,то перемещаемся по ближайшим числам одной из половин диапозона
                min = result + 1;
                break;

            case R.id.no:
                max = result;  //сокращаем диапозон на половину
                break;
        }
        if (max == min)
        {
            q.setText("Ваше число: " + min);    //нашли число
        }
        else
        {
            result = (max - min)/2 + min;    //ищем дальше
            q.setText("Ваше число >" + result+"?");
        }
    }
}