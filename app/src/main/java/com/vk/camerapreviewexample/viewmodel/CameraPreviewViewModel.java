package com.vk.camerapreviewexample.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.vk.camerapreviewexample.camera.CameraPreview;
import com.vk.camerapreviewexample.exception.ExceptionUtility;
import com.vk.camerapreviewexample.databinding.ActivityFullScreenPreviewBinding;
import com.vk.camerapreviewexample.utility.Utility;

import java.io.File;
import java.io.FileOutputStream;

import static com.vk.camerapreviewexample.constant.Constant.FULL_SCREEN;
import static com.vk.camerapreviewexample.constant.Constant.INPUT_HEIGHT;
import static com.vk.camerapreviewexample.constant.Constant.INPUT_WIDTH;

public class CameraPreviewViewModel {

    private Camera mCamera;
    private String TAG = "FullScreenPreviewViewModel";
    private CameraPreview maPreview;
    private Context context;
    private ActivityFullScreenPreviewBinding activityFullScreenPreviewBinding;

    public CameraPreviewViewModel(Context context, ActivityFullScreenPreviewBinding activityFullScreenPreviewBinding) {
        this.context = context;
        this.activityFullScreenPreviewBinding = activityFullScreenPreviewBinding;
    }

    public void startCameraPreview() {
        try {
            Bundle extras = ((Activity) context).getIntent().getExtras();
            long inputWidth = 0;
            long inputHeight = 0;
            if (extras != null) {
                inputWidth=extras.getInt(INPUT_WIDTH,0);
                inputHeight=extras.getInt(INPUT_HEIGHT,0);
            }
            mCamera = getCameraInstance();
            maPreview = new CameraPreview(context, mCamera, null);
            activityFullScreenPreviewBinding.flCameraPreview.addView(maPreview);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) maPreview.getLayoutParams();
            params.gravity = Gravity.CENTER;
            if(inputWidth>0&&inputHeight>0){
                setLayoutWH(inputWidth,inputHeight);
            }
        } catch (Exception exc) {
            ExceptionUtility.logError(TAG, "startCameraPreview", exc);
        }
    }

    private void setLayoutWH( long inputWidth,long inputHeight) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int mScreenWidth = displayMetrics.widthPixels;
        int mScreenHeight = displayMetrics.heightPixels;
        int width = (int) (displayMetrics.widthPixels *((float)inputWidth/100));
        int height = (int) (displayMetrics.heightPixels * ((float)inputHeight/100));
        int optimalSize[] = Utility.getOptimalDimensions(mScreenWidth
                , mScreenHeight
                , width
                , height);
        activityFullScreenPreviewBinding.flCameraPreview.getLayoutParams().width = optimalSize[0];
        activityFullScreenPreviewBinding.flCameraPreview.getLayoutParams().height = optimalSize[1];
        activityFullScreenPreviewBinding.flCameraPreview.getLayoutParams().width = optimalSize[0];
        activityFullScreenPreviewBinding.flCameraPreview.getLayoutParams().height = optimalSize[1];
    }

    private Camera getCameraInstance() {
        if (mCamera == null)
            // mCamera = Camera.open(useBackCamera ? 0 : 1);
            mCamera = Camera.open(1);
        return mCamera;
    }

    public void captureImage() {
        activityFullScreenPreviewBinding.ivCapture.setOnClickListener((View view) -> {
            try {
                mCamera.takePicture(() -> {
                }, null, (final byte[] bytes, Camera camera) -> {
                    try {
                        mCamera.stopPreview();
                        saveImage(bytes);
                    } catch (Exception e) {
                        ExceptionUtility.logError(TAG, "takePicture", e);
                    }
                });
            } catch (Exception e) {
                ExceptionUtility.logError(TAG, "capturePhoto", e);
            }
        });
    }

    private void saveImage(final byte[] data) {
        //make a new picture file
        try {
            byte[] imageData = Utility.rotateImageData((Activity) context, data, 1);
            File userDIR = Utility.createExternalDirectory(FULL_SCREEN);
            File file = new File(userDIR, System.currentTimeMillis() + ".jpeg");
            FileOutputStream outPut = new FileOutputStream(file);
            outPut.write(imageData, 0, imageData.length);
            outPut.close();
            Toast.makeText(context, file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
            ((Activity) context).finish();
        } catch (Exception e) {
            ExceptionUtility.logError(TAG, "saveImage", e);
        }
    }
}
