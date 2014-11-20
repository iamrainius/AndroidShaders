package com.jingz.app.graphics.shader;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Shader;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2014/11/18.
 */
public class GradientShaderView extends View {

    private static final String TAG = GradientShaderView.class.getSimpleName();

    private static int DEFAULT_FG_COLOR = 0xffffffff;
    private static int DEFAULT_BG_COLOR = 0x550000ff;
    private static int CURSOR_FG_COLOR = 0xff000000;
    private static int CURSOR_BG_COLOR = 0xffffffff;
    private static int CROSS_HALF_WIDTH = 10;

    private static int TYPE_LINEAR = 1;
    private static int TYPE_RADIAL = 2;
    private static int TYPE_SWEEP = 3;

    private PointF beginPoint;
    private PointF endPoint;

    private int fgColor = DEFAULT_FG_COLOR;
    private int bgColor = DEFAULT_BG_COLOR;

    private Paint bgPaint;
    private Paint cursorLinePaint;
    private Paint gradientPaint;

    private int type = 0;

    private boolean showCursor = false;
    private boolean drawGradient = false;

    private Handler handler = new Handler();

    public GradientShaderView(Context context) {
        super(context);
        init();
    }

    public GradientShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GradientShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GradientShaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        beginPoint = new PointF(0, 0);
        endPoint = new PointF(0, 0f);
        bgPaint = new Paint();
        bgPaint.setColor(0xffd0d0d0);
        initCursorPaint();

        gradientPaint = new Paint();

    }

    private void initCursorPaint() {
        cursorLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        cursorLinePaint.setStyle(Paint.Style.STROKE);
        cursorLinePaint.setColor(CURSOR_FG_COLOR);
        cursorLinePaint.setStrokeWidth(2);

        PathEffect effect = new DashPathEffect(new float[] {8f, 8f}, 1.0f);
        cursorLinePaint.setPathEffect(effect);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "Action: " + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                showCursor = true;
                beginPoint.set(event.getX(), event.getY());
                endPoint.set(event.getX(), event.getY());
                invalidate();
                return true;
            case MotionEvent.ACTION_MOVE:
                endPoint.set(event.getX(), event.getY());
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
                endPoint.set(event.getX(), event.getY());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showCursor = false;
                        type = TYPE_LINEAR;
                        invalidate();
                    }
                }, 200);
                invalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {

//        fillBackground(canvas);

        if (showCursor) {
            drawCursor(canvas);
        }

        if (type == TYPE_LINEAR) {
            LinearGradient linearGradient = new LinearGradient(200, 200, 1000, 1000, DEFAULT_FG_COLOR, DEFAULT_BG_COLOR, Shader.TileMode.REPEAT);
            gradientPaint.setShader(linearGradient);
            canvas.drawPaint(gradientPaint);
//            canvas.drawCircle(500, 500, 300, gradientPaint);
        }
    }

    private void fillBackground(Canvas canvas) {
        canvas.drawPaint(bgPaint);
    }

    private void drawCursor(Canvas canvas) {
        // Draw line
        Path path = new Path();
        path.moveTo(beginPoint.x, beginPoint.y);
        path.lineTo(endPoint.x, endPoint.y);
        canvas.drawPath(path, cursorLinePaint);

        // Draw cross
        canvas.drawLine(beginPoint.x - CROSS_HALF_WIDTH, beginPoint.y, beginPoint.x + CROSS_HALF_WIDTH, beginPoint.y, cursorLinePaint);
        canvas.drawLine(beginPoint.x, beginPoint.y - CROSS_HALF_WIDTH, beginPoint.x, beginPoint.y + CROSS_HALF_WIDTH, cursorLinePaint);
        canvas.drawLine(endPoint.x - CROSS_HALF_WIDTH, endPoint.y, endPoint.x + CROSS_HALF_WIDTH, endPoint.y, cursorLinePaint);
        canvas.drawLine(endPoint.x, endPoint.y - CROSS_HALF_WIDTH, endPoint.x, endPoint.y + CROSS_HALF_WIDTH, cursorLinePaint);
    }
}
