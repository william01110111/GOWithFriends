package net.widap.gowithfriends;

/**
 *  Created by william on 7/19/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

public class Overlay {

    WindowManager.LayoutParams params;
    CustomSurfaceView view;

    Overlay()
    {

    }

    void create()
    {
        setupView();
        setupLayoutParams();
        Manager.getWm().addView(view, params);
    }

    void remove()
    {
        Manager.getWm().removeView(view);
    }

    void setupView()
    {

        view=new CustomSurfaceView(Manager.getContext());

        //surfaceView.
    }

    void setupLayoutParams()
    {
        params = new WindowManager.LayoutParams
                (
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                        PixelFormat.TRANSLUCENT
                );

        DisplayMetrics metrics = Manager.getContext().getResources().getDisplayMetrics();
        params.gravity = Gravity.START | Gravity.TOP;
        params.x = 0;
        params.y = 0;
        params.width = metrics.widthPixels;
        params.height = metrics.heightPixels;
    }

    void update()
    {
        view.draw();
    }
}
