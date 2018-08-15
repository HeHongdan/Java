package com.hhd.java._Demo.EarphoneInterface;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.widget.Toast;

import com.hhd.java.R;

public class EarphoneInterfaceActivity extends Activity {

    private AudioManager mAudioManager;
    private MediaSession mediaSession;
    private ComponentName mComponentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earphone_interface);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // AudioManager注册一个MediaButton对象
        mComponentName = new ComponentName(getPackageName(), MediaButtonReceiver.class.getName());

    }


    @Override
    protected void onResume() {
        //MediaSession.setMediaButtonReceiver(PendingIntent mbr)
        mAudioManager.registerMediaButtonEventReceiver(mComponentName);
        registerReceiver(headSetReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
        super.onResume();
    }

    @Override
    protected void onPause() {
        // 取消注册
        mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);
        unregisterReceiver(headSetReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mAudioManager = null;
        mComponentName = null;
        super.onDestroy();
    }

    private final BroadcastReceiver headSetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_HEADSET_PLUG)) {
                // phone headset plugged
                if (intent.getIntExtra("state", 0) == 1) {
                    // do something
//					Log.d(TAG, "耳机检测：插入");
                    Toast.makeText(context, "耳机检测：插入", Toast.LENGTH_SHORT) .show();
                    mAudioManager.registerMediaButtonEventReceiver(mComponentName);
                    // phone head unplugged
                } else {
                    // do something
//					Log.d(TAG, "耳机检测：没有插入");
                    Toast.makeText(context, "耳机检测：没有插入", Toast.LENGTH_SHORT).show();
                    mAudioManager.unregisterMediaButtonEventReceiver(mComponentName);
                }
            }
        }
    };

}
