// Copyright 2016 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.example.android.globalactionbarservice;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.media.AudioManager;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayDeque;
import java.util.Calendar;
import java.util.Deque;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.List;


import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_SCROLLED;

//public class GlobalActionBarService extends AccessibilityService {
//    FrameLayout mLayout;
//    boolean shouldScroll = false;

public class GlobalActionBarService extends AccessibilityService {
    FrameLayout mLayout;
    Context ctx=this;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
    }

    @Override
    public void onInterrupt() {

    }


    @Override
    protected void onServiceConnected() {
        // Create an overlay and display the action bar
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mLayout = new FrameLayout(this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
        lp.format = PixelFormat.TRANSLUCENT;
        lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.action_bar, mLayout);
        wm.addView(mLayout, lp);



        configurePowerButton();

        configureOpenButton();

        configureSwipeButton();

    }


    private void configurePowerButton() {
        Button powerButton = (Button) mLayout.findViewById(R.id.feres);
        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.feres.user");
                    ctx.startActivity(i);
                } catch (Exception e) {

                }
            }
        });

    }


//        Button scrollOff= (Button) mLayout.findViewById(R.id.start);
//        scrollOff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                shouldScroll = false;
//
//            }
//        });

//
//        Timer timer = new Timer();
//        timer.scheduleAtFixedRate(new TimerTask() {
//
//            @Override
//            public void run() {
//                configureSwipeButton();
//
//            }
//
//        }, 0, 30000);


    private void configureOpenButton(){
        Button OpenButton = (Button) mLayout.findViewById(R.id.open);
        OpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.multibrains.taxi.passenger.ridepassengeret");
                    ctx.startActivity(i);
                } catch (Exception e) {

                }
            }
        });
    }


//    private void configureSwipeButton() {
//            Path swipePath = new Path();
//            swipePath.moveTo(1000, 1000);
//            swipePath.lineTo(1000, 100);
//            GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
//            gestureBuilder.addStroke(new GestureDescription.StrokeDescription(swipePath, 0, 500));
//            dispatchGesture(gestureBuilder.build(), null, null);
//
//
//    }



    private void configureSwipeButton() {
        Button swipeButton = (Button) mLayout.findViewById(R.id.swipe);
        swipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AccessibilityNodeInfo nodeInfo;
                nodeInfo = (AccessibilityNodeInfo) view.getAccessibilityNodeProvider().createAccessibilityNodeInfo(view.getId());
                List<AccessibilityNodeInfo> list = nodeInfo
                        .findAccessibilityNodeInfosByViewId("com.multibrains.taxi.passenger.ridepassengeret:id/pickup_location_set_pickup");
//
                for (AccessibilityNodeInfo node : list) {
                    node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }

            }
        });
    }



}




