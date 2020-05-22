package com.example.memorycanvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

class Card {
    Paint p = new Paint();

    public Card(float x, float y, float width, float height, int color) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    int color, backColor = Color.DKGRAY;
    boolean isOpen = false; // цвет карты
    float x, y, width, height;
    public void draw(Canvas c) {
        // нарисовать карту в виде цветного прямоугольника
        if (isOpen) {
            p.setColor(color);
        } else p.setColor(backColor);
        c.drawRect(x,y, x+width, y+height, p);
    }
    public boolean flip (float touch_x, float touch_y) {
        if (touch_x >= x && touch_x <= x + width && touch_y >= y && touch_y <= y + height) {
            isOpen = ! isOpen;
            return true;
        } else return false;
    }

}

public class TilesView extends View {
    // пауза для запоминания карт
    final int PAUSE_LENGTH = 2; // в секундах
    boolean isOnPauseNow = false;

    // число открытых карт
    int openedCard = 0;

    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<Integer> colors;
    ArrayList<Card> openedCards;
    boolean game;

    int width, height; // ширина и высота канвы

    public TilesView(Context context) {
        super(context);
    }

    public TilesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 1) заполнить массив tiles случайными цветами
        // сгенерировать поле 2*n карт, при этом
        // должно быть ровно n пар карт разных цветов
        colors = new ArrayList<>();
        openedCards = new ArrayList<>();
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.YELLOW);
        game = false;
    }

    public void createCards(){
        float cardWidth = width / 2;
        float cardHeight = height / 4;
        for(int i = 1; i < 4 + 1; i++) {
            int c = (int)(Math.random() * colors.size());
            cards.add(new Card(0, cardHeight * i - cardHeight, cardWidth - 30, cardHeight - 30, colors.get(c)));
            colors.remove(c);
            c = (int)(Math.random() * colors.size());
            cards.add(new Card(cardWidth + 30, cardHeight * i - cardHeight, cardWidth, cardHeight - 30, colors.get(c)));
            colors.remove(c);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!game) {
            width = canvas.getWidth();
            height = canvas.getHeight();
            createCards();
            game = true;
        }
        else {
            // 2) отрисовка плиток
            // задать цвет можно, используя кисть
            for (Card c: cards) {
                c.draw(canvas);
            }
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 3) получить координаты касания
        int x = (int) event.getX();
        int y = (int) event.getY();
        // 4) определить тип события
        if (event.getAction() == MotionEvent.ACTION_DOWN && !isOnPauseNow)
        {
            // палец коснулся экрана

            for (Card c: cards) {

                if (openedCard == 0) {
                    if (c.flip(x, y)) {
                        Log.d("mytag", "card flipped: " + openedCard);
                        openedCard ++;
                        openedCards.add(c);
                        invalidate();
                        return true;
                    }
                }

                if (openedCard == 1) {


                    // перевернуть карту с задержкой
                    if (c.flip(x, y)) {
                        openedCard ++;
                        // 1) если открылис карты одинакового цвета, удалить их из списка
                        // например написать функцию, checkOpenCardsEqual

                        // 2) проверить, остались ли ещё карты
                        // иначе сообщить об окончании игры

                        // если карты открыты разного цвета - запустить задержку
                        invalidate();
                        PauseTask task = new PauseTask();
                        task.execute(PAUSE_LENGTH);
                        isOnPauseNow = true;
                        openedCards.add(c);

                        if (checkCards()) {
                            if (cards.size() == 0) {
                                Toast.makeText(getContext(), "Game over", Toast.LENGTH_SHORT).show();
                            }
                        }
                        openedCards.clear();
                        return true;
                    }
                }

            }
        }


         // заставляет экран перерисоваться
        return true;
    }

    private boolean checkCards() {
        if(openedCards.get(0).color == openedCards.get(1).color){
            cards.remove(openedCards.get(0));
            cards.remove(openedCards.get(1));
            return true;
        }
        return false;
    }

    public void newGame() {
        // запуск новой игры
    }

    class PauseTask extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... integers) {
            Log.d("mytag", "Pause started");
            try {
                Thread.sleep(integers[0] * 1000); // передаём число секунд ожидания
            } catch (InterruptedException e) {}
            Log.d("mytag", "Pause finished");
            return null;
        }

        // после паузы, перевернуть все карты обратно


        @Override
        protected void onPostExecute(Void aVoid) {
            for (Card c: cards) {
                if (c.isOpen) {
                    c.isOpen = false;
                }
            }
            openedCard = 0;
            isOnPauseNow = false;
            invalidate();
        }
    }
}