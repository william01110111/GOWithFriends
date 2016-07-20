package net.widap.gowithfriends;

/**
 *  Created by william on 7/19/16.
 */

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Overlay {

    WindowManager.LayoutParams params;
    View view;

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
        TextView textView;
        textView=new TextView(Manager.getContext());
        String txt="[Overlay test text]";
        textView.setText(txt);
        textView.setTextSize(24);
        view=textView;
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

        params.gravity = Gravity.START | Gravity.TOP;
        params.x = 0;
        params.y = 0;
        //params.width = 600;
        //params.height = 100;
    }

    void setLoc(int x, int y)
    {
        params.x=x;
        params.y=y;
        Manager.getWm().updateViewLayout(view, params);
    }
}
