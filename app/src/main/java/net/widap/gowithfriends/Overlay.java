package net.widap.gowithfriends;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  Created by william on 7/19/16.
 */
public class Overlay {

    private WindowManager wm;
    TextView topLeftView;

    Overlay()
    {

    }

    void create(Context context)
    {


        wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params;
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

        TextView textView = new TextView(context);
        topLeftView = new TextView(context);
        WindowManager.LayoutParams topLeftParams = new WindowManager.LayoutParams
                (
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                        PixelFormat.TRANSLUCENT
                );

        topLeftParams.gravity = Gravity.START | Gravity.TOP;
        topLeftParams.x = 300;
        topLeftParams.y = 200;
        topLeftParams.width = 600;
        topLeftParams.height = 100;
        String txt="This is a test";
        textView.setText(txt);
        textView.setTextSize(24);
        //topLeftView.
        wm.addView(textView, topLeftParams);
        System.out.println("overlay creation done");
    }
}
