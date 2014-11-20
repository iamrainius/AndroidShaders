package com.jingz.app.graphics.shader;

import android.app.Activity;
import android.graphics.Shader;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class BitmapShaderActivity extends Activity implements View.OnClickListener {

    private BitmapShaderView mBitmapShaderView;
    private Button mToClamp;
    private Button mToMirror;
    private Button mToRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_shader);

        mBitmapShaderView = (BitmapShaderView) findViewById(R.id.bitmap_shader_view);
        mToClamp = (Button) findViewById(R.id.btn_clamp);
        mToClamp.setOnClickListener(this);
        mToMirror = (Button) findViewById(R.id.btn_mirror);
        mToMirror.setOnClickListener(this);
        mToRepeat = (Button) findViewById(R.id.btn_repeat);
        mToRepeat.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bitmap_shader, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_clamp:
                mBitmapShaderView.setTileMode(Shader.TileMode.CLAMP);
                break;
            case R.id.btn_mirror:
                mBitmapShaderView.setTileMode(Shader.TileMode.MIRROR);
                break;
            case R.id.btn_repeat:
                mBitmapShaderView.setTileMode(Shader.TileMode.REPEAT);
                break;
        }
    }
}
