package com.example.sector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import java.nio.file.Path;



public class SectorView {
    //класс абстракция
    float x;
    float y;
    float startAngle;
    float sweepAngle;
    float radius;
    Path sector;
    //java.nio.file.Path sect

//где будет рисоваться и углы
    public SectorView(float x, float y, float startAngle, float sweepAngle, float radius) {
        //центр
        this.x=x;
        this.y=y;
        //углы
        this.startAngle=startAngle;
        this.startAngle=sweepAngle;
        this.radius = radius;
        this.sector = sector;

    }

    public void draw(Canvas canvas , Paint p) {
            canvas.drawArc(x-radius, y-radius , x+radius, y+radius, startAngle, sweepAngle,true,p);

    }

}
