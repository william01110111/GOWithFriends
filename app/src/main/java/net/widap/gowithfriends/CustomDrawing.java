package net.widap.gowithfriends;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 *  Created by william on 7/22/16.
 */
public class CustomDrawing {

    private Paint paint;
    private Canvas canvas;

    CustomDrawing()
    {
        paint=new Paint();
        paint.setColor(200);
        paint.setStrokeWidth(30);
        paint.setStrokeWidth(60);
        paint.setAlpha(128);
    }

    public void setCanvas(Canvas canvasIn)
    {
        canvas=canvasIn;
    }

    public void drawPlayer(int x, int y)
    {
        canvas.drawOval(x-200, y-100, x+200, y+200, paint);
    }
}
