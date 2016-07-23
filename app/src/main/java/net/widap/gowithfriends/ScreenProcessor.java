package net.widap.gowithfriends;

/**
 *  Created by william on 7/20/16.
 */
public class ScreenProcessor {

    PixmapHandler img;

    ScreenProcessor(PixmapHandler imgIn)
    {
        img=imgIn;
    }

    public double getCompassAng()
    {
        int x, y;

        int left=1080, right=1280, top=img.h()-180, bottom=img.h()-360;

        double avgRedX=0, avgRedY=0, totalRed=0;
        double avgGreyX=0, avgGreyY=0, totalGrey=0;

        Clr clr=new Clr();

        for (y=bottom; y<top; ++y)
        {
            for (x=left; x<right; ++x)
            {
                img.get(x, y, clr);
                //System.out.println("Pixel found!!!");
                int a=getCompassPart(clr);
                if (a==2) //red
                {
                    avgRedX+=x;
                    avgRedY+=y;
                    totalRed++;
                    //img.set(x, y, new Clr(255, 0, 0));
                }
                else if (a==1) //grey
                {
                    avgGreyX+=x;
                    avgGreyY+=y;
                    totalGrey++;
                    //img.set(x, y, new Clr(128, 128, 128));
                }
                else //0, white
                {
                    //img.set(x, y, new Clr(255, 255,255));
                }
            }
        }

        double ang;

        if (totalGrey>12 && totalRed>12)
            ang=Math.atan2((avgGreyY/totalGrey-avgRedY/totalRed), (avgGreyX/totalGrey-avgRedX/totalRed))-90;
        else
            ang=0;

        //System.out.println("Total red: " + totalRed);

        while (ang<0)
            ang+=Math.PI;

        return ang;
    }


    //0 for white, 1 for grey, 2 for red
    private int getCompassPart(Clr in)
    {
        if (in.r>190 && in.g<100 && in.b<100)
            return 2; //red

        else if (in.r>150 && in.r<200 && in.g>150 && in.g<200 && in.b>150 && in.b<200)
            return 1; //grey

        else
            return 0; //white
    }
}
