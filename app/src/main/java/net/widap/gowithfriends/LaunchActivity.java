package net.widap.gowithfriends;

import android.content.Context;
import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class LaunchActivity extends AppCompatActivity {

    MediaProjectionManager mediaProjectionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Intent service = new Intent(this, MainService.class);
        startService(service);

        //media projection shit
        mediaProjectionManager=(MediaProjectionManager)getSystemService(Context.MEDIA_PROJECTION_SERVICE);
        Intent intent=mediaProjectionManager.createScreenCaptureIntent();
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Manager.mediaProjection = mediaProjectionManager.getMediaProjection(resultCode, data);

        if (Manager.mediaProjection==null)
        {
            Log.e(Manager.TAG, "setupMediaProjection: media projection is null");
            return;
        }

        Manager.inst.mediaProjectionSet();
    }
}
