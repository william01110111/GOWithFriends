package net.widap.gowithfriends;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MainService extends Service {

    Overlay overlay;

    public MainService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        //throw new UnsupportedOperationException("Not yet implemented");

        //return null because this service doesn't support binding
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        overlay = new Overlay();
        overlay.create(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Main service for 'GO With Friends' has been destroyed");
    }
}
