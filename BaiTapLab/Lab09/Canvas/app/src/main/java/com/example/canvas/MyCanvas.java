package com.example.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class MyCanvas extends View {
    public static int color = Color.YELLOW;
    public MyCanvas(Context context) {
        super(context, null);
    }
    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        // Vẽ nền vàng
        canvas.drawColor(color);
        // Vẽ chữ
        drawText(canvas);
        // Vẽ hình chữ nhật
        drawRect(canvas);
    }
    private void drawText(Canvas canvas) {
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        canvas.translate((float) viewWidth /2, (float) viewHeight /2);

        Paint mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(60f);

        for (int i = 0; i < 10; i++) {
            canvas.drawText("Minh Bảo", 0, 0, mPaint);
            canvas.rotate(36);
        }
    }

    private void drawRect(Canvas canvas) {
        int pos = 10;
        int viewWidth = getWidth();
        int viewHeight = getHeight();
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(pos, (float) viewHeight /2, pos + (float) viewWidth /5, viewHeight, mPaint);
        mPaint.setColor(Color.RED);
        canvas.drawRect(pos * 2 + (float) viewWidth /5, (float) viewHeight /2 - 100, (pos + (float) viewWidth /5) * 2, viewHeight, mPaint);
    }
}
