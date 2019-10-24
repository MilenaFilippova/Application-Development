package com.example.colorplits;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //int dark,bright;
    int darkColor, brightColor;
    View[][] tiles = new View[4][4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources r = getResources();
        darkColor = r.getColor(R.color.dark);
        brightColor = r.getColor(R.color.bright);
        tiles[0][0] = findViewById(R.id.title00);
        tiles[0][1] = findViewById(R.id.title01);
        tiles[0][2] = findViewById(R.id.title02);
        tiles[0][3] = findViewById(R.id.title03);
        tiles[1][0] = findViewById(R.id.title10);
        tiles[1][1] = findViewById(R.id.title11);
        tiles[1][2] = findViewById(R.id.title12);
        tiles[1][3] = findViewById(R.id.title13);
        tiles[2][0] = findViewById(R.id.title20);
        tiles[2][1] = findViewById(R.id.title21);
        tiles[2][2] = findViewById(R.id.title22);
        tiles[2][3] = findViewById(R.id.title23);
        tiles[3][0] = findViewById(R.id.title30);
        tiles[3][1] = findViewById(R.id.title31);
        tiles[3][2] = findViewById(R.id.title32);
        tiles[3][3] = findViewById(R.id.title33);

        randColor();
    }


    //Реализовать закраску плиток случайным сочетанием цветов (темный/светлый)
    public void randColor() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double myrand = Math.random();
                if (myrand > 0.5) //тк цвета 2
                {
                    changeColor(tiles[i][j]);   //меняем цвет
                }
            }
        }
    }


    protected void changeColor(View v) {
        ColorDrawable d = (ColorDrawable) v.getBackground();
        if (d.getColor() == brightColor) {
            v.setBackgroundColor(darkColor);
        } else {
            v.setBackgroundColor(brightColor);
        }
    }


    public void onClick(View v) {

        String tag = v.getTag().toString();
        String[] coord = tag.split(" ");
        int x = Integer.parseInt(coord[0]);
        int y = Integer.parseInt(coord[1]);

        changeColor(tiles[x][y]);

        // изменить цвет на самом тайле и всех тайлах
        // с таким же x и таким же y
        for (int i = 0; i < 4; i++) {
            changeColor(tiles[x][i]);
            changeColor(tiles[i][y]);
        }
        check();

    }

    public void check() {
        ColorDrawable one, two;
        one = (ColorDrawable) tiles[0][0].getBackground();
        boolean rezult = true;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                two = (ColorDrawable) tiles[i][j].getBackground();
                if (one.getColor() != two.getColor()) {
                    rezult = false; //если хоть где-то цвета не совпадают,то игра не окончена
                    break;
                }
            }
            if (!rezult)
                break;
        }

        if (rezult) {
            Toast ending = Toast.makeText(this, "YOU ARE A WINNER", Toast.LENGTH_LONG);
            ending.show();
        }
    }
}
