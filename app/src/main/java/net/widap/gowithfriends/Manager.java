package net.widap.gowithfriends;

import android.content.Context;
import android.media.projection.MediaProjection;
import android.util.Log;
import android.view.WindowManager;

/**
 *  Created by william on 7/19/16.
 */

public class Manager {

    public static Manager inst; //the single instance of Manager

    //this is not in inst because it needs to be accessed before inst is created
    public static MediaProjection mediaProjection;

    private Context context;
    private WindowManager windowManager;
    public static final String TAG="GO_With_Friends";

    Overlay overlay;
    PixmapHandler pixmapHandler;
    ScreenProcessor screenProcessor;
    ScreenGrabber screenGrabber;

    public Manager(Context contextIn)
    {
        inst=this;
        context=contextIn;
        pixmapHandler=new PixmapHandler();
        screenGrabber=new ScreenGrabber(pixmapHandler);
        screenProcessor=new ScreenProcessor(pixmapHandler);
    }

    public static Context getContext() {return inst.context;}
    public static WindowManager getWm() {
        if (inst.windowManager==null)
            inst.windowManager=(WindowManager)inst.context.getSystemService(Context.WINDOW_SERVICE);

        return inst.windowManager;
    }

    public void mediaProjectionSet()
    {
        startInteraction();
    }

    public void startInteraction()
    {
        screenGrabber.go();
        if (overlay==null)
            overlay = new Overlay();
        overlay.create();
        overlay.setLoc(200, 300);
    }

    public void endInteraction()
    {
        overlay.remove();
    }

    private int i=0;

    public void screenFramedGrabed()
    {
        //double ang=Math.toDegrees(screenProcessor.getCompassAng());
        //System.out.println("pixel: " + pixmapHandler.get(600, 800).r);
        //Log.d(TAG, "screenFramedGrabed: compass is: "+ang);

        //if (++i%40==39)
        //    overlay.setLoc((int)(ang*2), 0);

        if (++i%4==0)
            overlay.setImage(pixmapHandler.getBitmap());
    }
}
