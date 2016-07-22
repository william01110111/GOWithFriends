package net.widap.gowithfriends;

/**
 *  Created by william on 7/19/16.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class Overlay {

    WindowManager.LayoutParams params;
    View view;

    /*private class CustomView extends View {

        CustomView(Context context)
        {
            super(context);
        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);


            // Draw the shadow
            canvas.drawOval(Rect);

            // Draw the label text
            canvas.drawText(mData.get(mCurrentItem).mLabel, mTextX, mTextY, mTextPaint);
        }
    }*/

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
        /*TextView textView;
        textView=new TextView(Manager.getContext());
        String txt="[Overlay test text]";
        textView.setText(txt);
        textView.setTextSize(24);
        view=textView;*/

        //view = new CustomView(Manager.getContext());

        //view=new ImageView(Manager.getContext());
        //view.setAlpha(1.0f);

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

        params.gravity = Gravity.START | Gravity.TOP;
        params.x = 0;
        params.y = 0;
        params.width = 300;
        params.height = 400;
    }

    void setLoc(int x, int y)
    {
        //Log.d(Manager.TAG, "setLoc: " + x + ", " + y);
        //view.layout(x, y, x+200, y+200);

        params.x=x;
        params.y=y;
        Manager.getWm().updateViewLayout(view, params);
    }

    void update()
    {
        ((CustomSurfaceView)view).draw();
    }

    /*void setImage(Bitmap bmp)
    {
        if (bmp!=null)
            ((ImageView)view).setImageBitmap(bmp);
    }*/
}
