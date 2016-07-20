package net.widap.gowithfriends;

import android.content.Context;

/**
 *  Created by william on 7/19/16.
 */
public class Manager {

    public static Manager inst; //the single instance of Manager

    private Overlay overlay;
    private Context context;

    public Manager(Context contextIn)
    {
        inst=this;
        context=contextIn;
    }

    public Context getContext()
    {
        return context;
    }

    public void startOverlayLayer()
    {
        if (overlay==null)
            overlay = new Overlay();
        overlay.create();
        overlay.setLoc(200, 300);
    }

    public void endOverlayLayer()
    {
        overlay.remove();
    }

    public void screenFramedGrabed()
    {

    }
}
