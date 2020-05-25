package com.vk.camerapreviewexample.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.vk.camerapreviewexample.R;
import com.vk.camerapreviewexample.databinding.ActivityFullScreenPreviewBinding;
import com.vk.camerapreviewexample.viewmodel.CameraPreviewViewModel;

public class CameraPreviewActivity extends AppCompatActivity {

    private CameraPreviewViewModel cameraPreviewViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFullScreenPreviewBinding activityFullScreenPreviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_full_screen_preview);
        cameraPreviewViewModel = new CameraPreviewViewModel(this, activityFullScreenPreviewBinding);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            cameraPreviewViewModel.startCameraPreview();
            cameraPreviewViewModel.captureImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                cameraPreviewViewModel.startCameraPreview();
            } else {
                Toast.makeText(this, R.string.camera_permission_error, Toast.LENGTH_SHORT).show();
            }
            if (grantResults.length > 1
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                cameraPreviewViewModel.captureImage();
            } else {
                Toast.makeText(this, R.string.storage_permission_error, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
