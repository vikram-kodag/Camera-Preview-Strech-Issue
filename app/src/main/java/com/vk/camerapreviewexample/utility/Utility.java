package com.vk.camerapreviewexample.utility;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class Utility {
    private static String TAG = "Utility";

    public static boolean isEmptyOrNull(String value) {
        return (value == null || value.isEmpty());
    }

    public static void showMessage(Context activity, String message) {
        Toast toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT);
        View view = toast.getView();
        toast.show();
    }

    public static int[] getOptimalDimensions(float mediaWidth, float mediaHeight, int width, int height) {
        int layoutWidth = width;
        int layoutHeight = height;
        float ratioWidth = layoutWidth / mediaWidth;
        float ratioHeight = layoutHeight / mediaHeight;
        float aspectRatio = mediaWidth / mediaHeight;
        if (ratioWidth > ratioHeight) {
            layoutWidth = (int) (layoutHeight * aspectRatio);
        } else {
            layoutHeight = (int) (layoutWidth / aspectRatio);
        }
        Log.i(TAG, "layoutWidth: " + layoutWidth);
        Log.i(TAG, "layoutHeight: " + layoutHeight);
        Log.i(TAG, "aspectRatio: " + aspectRatio);
        return new int[]{layoutWidth, layoutHeight};
    }

}
