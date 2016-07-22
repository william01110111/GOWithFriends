package net.widap.gowithfriends;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.media.Image;
import android.util.Log;

/**
 *  Created by william on 7/20/16.
 */

public class PixmapHandler {

    private byte[] data;
    private int width, height;

    public PixmapHandler()
    {
        data=new byte[0];
        width=0;
        height=0;
    }

    public int w() {return width;}
    public int h() {return height;}

    public void setWithImage(Image image)
    {
        width=image.getWidth();
        height=image.getHeight();

        if (image.getFormat()!= ImageFormat.RGB_565)
        {
            Log.w(Manager.TAG, "Image sent to PixmapHandler.setWithImage() with wrong format");
            return;
        }

        if (image.getPlanes()[0].getBuffer().remaining()!=data.length)
            data=new byte[image.getPlanes()[0].getBuffer().remaining()];

        image.getPlanes()[0].getBuffer().get(data);
    }

    public void set(int x, int y, Clr clr)
    {
        int o=((height-y-1)*width+x)*2;
        data[o+1]=(byte)((clr.r & 0b11111000) | ((clr.g >> 5) | 0b00000111));
        data[o]=(byte)((clr.b >> 3 & 0b00011111) | ((clr.g << 3) | 0b11100000));
    }

    public Clr get(int x, int y) //less efficient but convenient
    {
        Clr c=new Clr();
        get(x, y, c);
        return c;
    }

    public void get(int x, int y, Clr clr)
    {
        int o=((height-y-1)*width+x)*2;
        clr.r=data[o+1] & 0b11111000;
        clr.g=(((data[o+1] << 5) & 0b11100000) | ((data[o] >> 3) & 0b00011111)) & 0b11111100;
        clr.b=(data[o] << 3)  & 0b11111100;
    }

    public Bitmap getBitmap()
    {
        if (width==0 || height==0)
            return null;

        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //bmp.setPixels(data, 0, 0, 0, w, h);
        //bmp.;

        System.out.println("starting array traversal..");

        Clr clr=new Clr();

        //System.out.println("pixel: " + get(600, 800).g);

        int[] ary=new int[width*height];

        for (int y=0; y<height; ++y)
        {
            for (int x=0; x<width; ++x)
            {
                get(x, y, clr);
                ary[(height-y-1)*width+x]=(0xFF<<24) | ((clr.r & 0xFF) << 16) | ((clr.g & 0xFF) << 8) | (clr.b & 0xFF);
                //bmp.setPixel(x, y, (0xFF<<24) | (clr.r << 16) | (clr.g << 8) | (clr.b));
            }
        }

        System.out.println("traversal done, writing to bitmap");

        bmp.setPixels(ary, 0, width, 0, 0, width, height);

        System.out.println("bitmap created");

        return bmp;
    }
}
