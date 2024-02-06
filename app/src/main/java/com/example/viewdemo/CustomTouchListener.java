package com.example.viewdemo;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

public class CustomTouchListener implements View.OnTouchListener {
    Context context;
    GestureDetectorCompat gestureDetectorCompat;


    //gestureDetectorCompat - needs two things for creation
    //context, gesture listener
    //After creating CustomGestureListener
    //Generate Constructor for CustomTouchListener


    public CustomTouchListener(Context context) {
        this.context = context;
        gestureDetectorCompat = new GestureDetectorCompat
                            (context,new CustomGestureListener());
    }

    public class CustomGestureListener
            extends GestureDetector.SimpleOnGestureListener {


        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            onLongClick();
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            float SWIPE_DIST_THRESHOLD = 10;
            float SWIPE_VEL_THRESHOLD = 20;

            float xOff = e2.getX() - e1.getX();
            float yOff = e2.getY() - e1.getY();

            if (Math.abs(xOff) > Math.abs(yOff)
            && Math.abs(xOff) > SWIPE_DIST_THRESHOLD
            && Math.abs(velocityX) > SWIPE_VEL_THRESHOLD){
                //horizontal swipe
                if (xOff > 0){
                    //right swipe
                    onRightSwipe();
                } else {
                    //left swipe
                    onLeftSwipe();
                }
            } else if (Math.abs(yOff) > Math.abs(xOff)
                    && Math.abs(yOff) > SWIPE_DIST_THRESHOLD
                    && Math.abs(velocityY) > SWIPE_VEL_THRESHOLD) {
                //vertical swipe
                if (yOff > 0){
                    //down swipe
                    onDownSwipe();
                } else {
                    //up swipe
                    onUpSwipe();
                }
            }


            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(@NonNull MotionEvent e) {
            //return super.onDown(e); //base class onDown returns false
            return true;
        }

        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            onDoubleClick();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
            onSingleClick();
            return super.onSingleTapConfirmed(e);
        }
    }

    public void onUpSwipe() {
        //refactored and renamed this method name to be
        //consistent with the rest of the swipe method
        //names :)

        //added these logs after class
        Log.d("GESTUREDEMO","up swipe detected");

    }

    public void onDownSwipe() {
        //added these logs after class
        Log.d("GESTUREDEMO","down swipe detected");

    }

    public void onLeftSwipe() {
        //added these logs after class
        Log.d("GESTUREDEMO","left swipe detected");

    }

    public void onRightSwipe() {
        //added these logs after class
        Log.d("GESTUREDEMO","right swipe detected");

    }

    public void onLongClick() {
        Log.d("GESTUREDEMO","Long click detected");

    }

    public void onDoubleClick() {
        Log.d("GESTUREDEMO","Double click detected");

    }

    public void onSingleClick() {
        Log.d("GESTUREDEMO","Single click detected");
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //return false;
        return gestureDetectorCompat.onTouchEvent(event);
    }
}
