package com.example.vjcpyu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {
        if (Config.prefs(context).getBoolean(Config.ENABLED, false)) PollReceiver.schedule(context);
    }
}
