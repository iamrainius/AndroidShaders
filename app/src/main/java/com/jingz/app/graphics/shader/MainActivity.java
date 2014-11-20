package com.jingz.app.graphics.shader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapShader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.jingz.app.graphics.shader.BitmapShaderActivity;
import com.jingz.app.graphics.shader.GradientShaderActivity;
import com.jingz.app.graphics.shader.R;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button mBitmapShader;
    private Button mGradientShader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBitmapShader = (Button) findViewById(R.id.btn_bitmap_shader);
        mBitmapShader.setOnClickListener(this);
        mGradientShader = (Button) findViewById(R.id.btn_gradient_shader);
        mGradientShader.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bitmap_shader:
                Intent intent = new Intent(this, BitmapShaderActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_gradient_shader:
                intent = new Intent(this, GradientShaderActivity.class);
                startActivity(intent);
                break;
        }
    }
}
