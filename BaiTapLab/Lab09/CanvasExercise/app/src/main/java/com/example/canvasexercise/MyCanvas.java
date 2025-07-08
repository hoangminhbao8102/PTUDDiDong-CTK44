package com.example.canvasexercise;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class MyCanvas  extends View {
    private final Paint paint = new Paint();
    private int[] barValues = {30, 60, 80, 50}; // Giá trị cho bar chart
    private int[] pieValues = {25, 35, 40};     // Giá trị cho pie chart
    private int[] lineValues = {20, 40, 60, 80, 50}; // Giá trị cho line chart

    public MyCanvas(Context context) {
        super(context);
    }

    public void setBarValues(int[] barValues) {
        this.barValues = barValues;
        invalidate();
    }

    public void setPieValues(int[] pieValues) {
        this.pieValues = pieValues;
        invalidate();
    }

    public void setLineValues(int[] lineValues) {
        this.lineValues = lineValues;
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.WHITE);

        drawBarChart(canvas);
        drawPieChart(canvas);
        drawLineChart(canvas);
    }

    private void drawBarChart(Canvas canvas) {
        int width = getWidth() / 2; // Bar chart bên trái
        int height = getHeight();

        int barWidth = width / (barValues.length * 2);
        paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < barValues.length; i++) {
            paint.setColor(Color.rgb(50 * i, 100 + 30 * i, 150));
            float left = i * 2 * barWidth + 20;
            float top = height - (barValues[i] / 100f * height / 2);
            float right = left + barWidth;

            canvas.drawRect(left, top, right, (float) height, paint);
        }
    }

    private void drawPieChart(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        int radius = Math.min(width, height) / 6;
        int cx = width - radius - 20;
        int cy = radius + 20;

        float total = 0;
        for (int val : pieValues) total += val;

        float startAngle = 0f;
        for (int i = 0; i < pieValues.length; i++) {
            paint.setColor(Color.rgb(100 + 30 * i, 50 * i, 200 - 50 * i));
            float sweepAngle = (pieValues[i] / total) * 360;
            canvas.drawArc(cx - radius, cy - radius, cx + radius, cy + radius,
                    startAngle, sweepAngle, true, paint);
            startAngle += sweepAngle;
        }
    }

    private void drawLineChart(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);

        float gap = (float) width / (lineValues.length + 1);
        float prevX = gap;
        float prevY = height - (lineValues[0] / 100f * height / 2);

        for (int i = 1; i < lineValues.length; i++) {
            float x = gap * (i + 1);
            float y = height - (lineValues[i] / 100f * height / 2);
            canvas.drawLine(prevX, prevY, x, y, paint);
            prevX = x;
            prevY = y;
        }
    }
}