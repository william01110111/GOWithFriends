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

    public CustomSurfaceView(Context context)
    {
        super(context);
        setZOrderOnTop(true);
        holder=getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSPARENT);
        paint=new Paint();
        paint.setColor(200);
        paint.setStrokeWidth(30);
        paint.setStrokeWidth(60);
        paint.setAlpha(128);
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
        Canvas canvas=holder.lockCanvas();

        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
        //canvas.drawLine(0, 0, 20, 20, paint);
        //canvas.drawBitmap(Manager.inst.pixmapHandler.getBitmap(), 0, 0, null);
        canvas.drawOval(0, 0, 200, 430, paint);

        holder.unlockCanvasAndPost(canvas);
    }
}