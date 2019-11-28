/*разработаем несложную игру Color Tiles (цветные плитки).
 Каждая клетка может быть светлого или тёмного цвета. При нажатии на клетку, все клетки, находящиеся на той же горизонтали и вертикали меняют цвет на противоположный. 
В начале игры плитки имеют случайный цвет (тёмный или светлый). Цель игры - привести плитки к одному цвету.*/

package com.example.colortilestwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TilesView(this));
    }
}
