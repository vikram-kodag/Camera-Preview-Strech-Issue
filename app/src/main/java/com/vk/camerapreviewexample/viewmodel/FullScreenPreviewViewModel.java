package com.vk.camerapreviewexample.viewmodel;

import android.content.Context;
import android.hardware.Camera;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.vk.camerapreviewexample.camera.CameraPreview;
import com.vk.camerapreviewexample.exception.ExceptionUtility;
import com.vk.camerapreviewexample.databinding.ActivityFullScreenPreviewBinding;

public class FullScreenPreviewViewModel {

    private Camera mCamera;
    private String TAG = "FullScreenPreviewViewModel";
    private CameraPreview maPreview;
    private Context context;
    private ActivityFullScreenPreviewBinding activityFullScreenPreviewBinding;

    public FullScreenPreviewViewModel(Context context, ActivityFullScreenPreviewBinding activityFullScreenPreviewBinding) {
        this.context = context;
        this.activityFullScreenPreviewBinding = activityFullScreenPreviewBinding;
    }

    public void startCameraPreview() {
        try {
            mCamera = getCameraInstance();
            maPreview = new CameraPreview(context, mCamera, null);
            activityFullScreenPreviewBinding.flCameraPreview.addView(maPreview);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) maPreview.getLayoutParams();
            params.gravity = Gravity.CENTER;
        } catch (Exception exc) {
            ExceptionUtility.logError(TAG, "startCameraPreview", exc);
        }
    }

    private Camera getCameraInstance() {
        if (mCamera == null)
            // mCamera = Camera.open(useBackCamera ? 0 : 1);
            mCamera = Camera.open(1);
        return mCamera;
    }
}
