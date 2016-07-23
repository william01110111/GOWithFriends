package net.widap.gowithfriends;

/**
 *  Created by william on 7/21/16.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CustomSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder holder;
    private Paint paint;
    private Canvas canvas;

    public CustomSurfaceView(Context context)
    {
        super(context);
        setZOrderOnTop(true);
        holder=getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);
    }

    public void surfaceCreated(SurfaceHolder var1)
    {

    }

    public void surfaceChanged(SurfaceHolder var1, int var2, int var3, int var4)
    {

    }

    public void surfaceDestroyed(SurfaceHolder var1)
    {

    }

    public void draw()
    {
        canvas=holder.lockCanvas();

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        Manager.inst.drawStuff(canvas);

        holder.unlockCanvasAndPost(canvas);
        canvas=null;
    }
}