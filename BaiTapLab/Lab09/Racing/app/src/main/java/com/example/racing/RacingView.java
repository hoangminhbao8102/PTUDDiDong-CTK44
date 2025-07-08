package com.example.racing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Random;

public class RacingView extends View implements Runnable {

    private final Paint paint = new Paint();
    private final int[] racerX = {0, 0, 0};
    private boolean isRunning = false;
    private int finishLine;
    private Thread thread;
    private final Random random = new Random();

    public RacingView(Context context) {
        super(context);
    }

    public void startRace() {
        if (thread == null || !isRunning) {
            isRunning = true;
            Arrays.fill(racerX, 0);
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        finishLine = getWidth() - 100;

        canvas.drawColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        canvas.drawLine(finishLine, 0, finishLine, getHeight(), paint);

        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < racerX.length; i++) {
            paint.setColor(Color.rgb(100 + i * 50, 50, 200 - i * 50));
            canvas.drawRect(racerX[i], i * 150 + 50, racerX[i] + 100, i * 150 + 150, paint);
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            for (int i = 0; i < racerX.length; i++) {
                racerX[i] += random.nextInt(10) + 5;
                if (racerX[i] + 100 >= finishLine) {
                    isRunning = false;
                    int finalI = i;
                    post(() -> {
                        invalidate();
                        android.widget.Toast.makeText(getContext(), "Racer " + (finalI + 1) + " wins!", android.widget.Toast.LENGTH_SHORT).show();
                    });
                    return;
                }
            }
            postInvalidate();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
