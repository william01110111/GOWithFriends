package net.widap.gowithfriends;

import android.content.Intent;
import android.graphics.ImageFormat;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.media.Image;
import android.media.ImageReader;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Surface;

/**
 *  Created by william on 7/20/16.
 */
public class ScreenGrabber {

    private int width;
    private int height;
    private int dpi;
    private ImageReader imgReader;
    public Image lastImage;
    private Surface surface;
    private VirtualDisplay virtualDisplay;
    private ImageAvailableListener listener;
    private PixmapHandler pixmapHandler;

    private class ImageAvailableListener implements ImageReader.OnImageAvailableListener
    {
        @Override
        public void onImageAvailable(ImageReader imageReader) {
            if (lastImage!=null)
                lastImage.close();

            lastImage=imgReader.acquireLatestImage();

            if (lastImage!=null)
            {
                pixmapHandler.setWithImage(lastImage);
                Manager.inst.screenFramedGrabed();
            }
        }
    }

    public ScreenGrabber(PixmapHandler handlerIn) {
        pixmapHandler=handlerIn;
    }

    public void go() {

        Log.d(Manager.TAG, "go: screen grabber started!");

        Manager.getWm().getDefaultDisplay();
        DisplayMetrics metrics = Manager.getContext().getResources().getDisplayMetrics();
        dpi = metrics.densityDpi;
        //width=metrics.widthPixels;
        //height=metrics.heightPixels;
        width=1440;
        height=2048; //anything bigger then 2048 crashes it
        listener=new ImageAvailableListener();

        if (Manager.mediaProjection==null)
        {
            Log.w(Manager.TAG, "mediaProjection null when ScreenGrabber.go() called");
            return;
        }
        
        imgReader = ImageReader.newInstance(width, height, ImageFormat.RGB_565, 3);
        surface = imgReader.getSurface();

        virtualDisplay = Manager.mediaProjection.createVirtualDisplay
                (
                        "GO_With_Friends - display",
                        width, height, dpi,
                        DisplayManager.VIRTUAL_DISPLAY_FLAG_PUBLIC,
                        surface, null, null
                );

        Log.d(Manager.TAG, "created virtual display: " + virtualDisplay);

        imgReader.setOnImageAvailableListener(listener, new Handler());
    }

    public void close() {

        if (virtualDisplay != null) {
            virtualDisplay.release();
        }
    }
}
