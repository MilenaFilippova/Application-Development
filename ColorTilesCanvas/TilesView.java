package com.example.colortilestwo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class TilesView extends View
{
    int[][] tiles=new int [4][4];
    int darkColor = Color.GRAY;
    int brightColor = Color.YELLOW;
    //int color = darkColor;
    float width,height;



    public TilesView(Context context) {
        super(context);
        Random r = new Random() ;
        for(int i = 0; i<4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                //Реализовать закраску плиток случайным сочетанием цветов (темный/светлый)
                int color = r.nextBoolean() ? darkColor : brightColor;
                tiles[i][j] = color;
            }
        }
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();

       // canvas.drawColor(color);
        Paint p = new Paint();

        for(int i=0; i<4; i++) {
            for (int j = 0; j < 4; j++) {
                p.setColor(tiles[i][j]);
                float x1 = i * width/4;
                float x2 = x1 + width/4;
                float y1 = j * height/4;
                float y2 = y1 + height/4;
                canvas.drawRect(x1,y1,x2,y2,p);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int width_tiles = (int) width / 4;
        int widthX = x / width_tiles;

        int hight_tiles = (int) height / 4;
        int hightY = y / hight_tiles;


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //палец коснулс
            for (int i = 0; i < 4; i++) {
                tiles[widthX][i] = tiles[widthX][i]  == brightColor ? darkColor : brightColor;
                tiles[i][hightY] = tiles[i][hightY] == brightColor ? darkColor : brightColor;
            }
            tiles[widthX][hightY] = tiles[widthX][hightY] == brightColor ? darkColor : brightColor;

        }

        invalidate();   //заставляет экран перерисовываться
        return true;
    }


    protected void changeColor(View v)
    {
        ColorDrawable d = (ColorDrawable) v.getBackground();
        if (d.getColor() == brightColor) {
            v.setBackgroundColor(darkColor);
        }
        else{
            v.setBackgroundColor(brightColor);
        }
    }


    public void check() {
        int rezult = tiles[0][0];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (tiles[i][j]!=rezult)
                {
                    rezult = 0; //если хоть где-то цвета не совпадают,то игра не окончена
                    break;
                }
            }
            if (rezult==0)
                break;
        }

        if (rezult!=0)
        {
            Toast.makeText(getContext(), "YOU ARE A WINNER", Toast.LENGTH_SHORT).show();
        }
    }

}



