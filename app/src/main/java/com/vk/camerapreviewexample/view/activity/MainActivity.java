package com.vk.camerapreviewexample.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vk.camerapreviewexample.R;

import static com.vk.camerapreviewexample.constant.Constant.INPUT_HEIGHT;
import static com.vk.camerapreviewexample.constant.Constant.INPUT_WIDTH;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClickFullScreen(View view){
        startActivity(new Intent(this,CameraPreviewActivity.class));
    }
    public void onClickDynamicSize(View view){
        Intent intent=new Intent(this,CameraPreviewActivity.class);
        intent.putExtra(INPUT_WIDTH,50);
        intent.putExtra(INPUT_HEIGHT,50);
        startActivity(intent);
    }
}
