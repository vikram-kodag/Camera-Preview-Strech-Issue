package com.vk.camerapreviewexample.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.vk.camerapreviewexample.R;
import com.vk.camerapreviewexample.databinding.ActivityFullScreenPreviewBinding;
import com.vk.camerapreviewexample.viewmodel.FullScreenPreviewViewModel;

public class FullScreenPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFullScreenPreviewBinding activityFullScreenPreviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_full_screen_preview);
        FullScreenPreviewViewModel fullScreenPreviewViewModel = new FullScreenPreviewViewModel(this, activityFullScreenPreviewBinding);
        fullScreenPreviewViewModel.startCameraPreview();
    }
}
