package com.jingz.app.graphics.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.jingz.app.graphics.shader.R;

/**
 * Created by Administrator on 2014/11/17.
 */
public class BitmapShaderView extends View {
    private Paint mPaint = new Paint();
    private Bitmap nBitmap;

    public BitmapShaderView(Context context) {
        super(context);
        init();
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        nBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.texture);
        BitmapShader shader = new BitmapShader(nBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mPaint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mPaint);
    }

    public void setTileMode(Shader.TileMode tileMode) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.texture);
        BitmapShader shader = new BitmapShader(bitmap, tileMode, tileMode);
        mPaint.setShader(shader);
        invalidate();
    }
}
