package com.example.sector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;
import android.content.Context;
import java.util.Random;




public class DiagramsView extends View {
    MainActivity activity;
    float Radius=250;
    float CenterX=getWidth()/2;
    float CenterY=getHeight()/2;

    public DiagramsView(Context context, @Nullable AttributeSet attrs) {
        super(context,attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float width,height;

        float x =  event.getX();
        float y =  event.getY();
        boolean rezult = isPointInto(x,y);

        invalidate();
        return super.onTouchEvent(event);
    }

    
    
    public boolean isPointInto(float eventX, float eventY) {

       // функция должна сообщать, содержится ли точка внутри сектора
        if(eventX<=CenterX+Radius && eventX>=CenterX-Radius &&  eventY>=CenterY-Radius && eventY<=CenterY+Radius)
            return true;
        else
            return false;
    }

    
    
    //Метод onDraw был вызван системой, когда возникла необходимость прорисовать
    // View-компонент на экране. Это также произойдет, например, если выключить-включить экран
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);
        Random r = new Random();
        float a = 0;//угол
        Paint p = new Paint();
        p.setStrokeWidth(5);
    
        //5 случайных сектаров
        for (int i =0 ; i < 5 ; i++)
        {
            p.setColor(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            float sweep = r.nextFloat() * 70;
            SectorView s = new SectorView(getWidth()/2, getHeight()/2, a, sweep, Radius);
            a += sweep;
            s.draw(canvas , p);

        }
    }
}




