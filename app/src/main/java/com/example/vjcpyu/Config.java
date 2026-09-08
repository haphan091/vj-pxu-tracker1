package com.example.vjcpyu;

import android.content.Context;
import android.content.SharedPreferences;

public final class Config {
    static final String PREFS = "config";
    static final String TOKEN = "fr24_token";
    static final String ENABLED = "enabled";
    static final String LAST_IDS = "last_ids";
    static final String LAST_STATUS = "last_status";
    static final String LAST_SUCCESS = "last_success";
    static final String BASE = "https://fr24api.flightradar24.com/api";
    static final String CHANNEL = "vjc_pxu_alerts";
    static final long POLL_MS = 15L * 60L * 1000L;

    private Config() {}

    static SharedPreferences prefs(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }
}
