package com.example.vjcpyu;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.widget.*;

public class MainActivity extends Activity {
    EditText token; Switch enabled; TextView status;
    @Override public void onCreate(Bundle b) { super.onCreate(b); buildUi(); PollReceiver.createChannel(this); if (Build.VERSION.SDK_INT >= 33 && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 7); }
    void buildUi() {
        LinearLayout root = new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setPadding(dp(24),dp(24),dp(24),dp(24));
        TextView title = new TextView(this); title.setText("VJC → PXU Alert"); title.setTextSize(28); title.setTextColor(Color.rgb(190,0,0)); root.addView(title,new LinearLayout.LayoutParams(-1,-2));
        TextView desc = new TextView(this); desc.setText("Theo dõi máy bay Vietjet (VJC) có origin HAN/SGN và destination PXU từ dữ liệu live của Flightradar24."); desc.setTextSize(16); desc.setPadding(0,dp(10),0,dp(12)); root.addView(desc);
        token = new EditText(this); token.setHint("FR24 API token"); token.setSingleLine(true); token.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD); token.setText(Config.prefs(this).getString(Config.TOKEN,"")); root.addView(token,new LinearLayout.LayoutParams(-1,dp(58)));
        enabled = new Switch(this); enabled.setText("Bật theo dõi nền"); enabled.setTextSize(17); enabled.setChecked(Config.prefs(this).getBoolean(Config.ENABLED,false)); root.addView(enabled);
        Button save = new Button(this); save.setText("Lưu & bắt đầu"); root.addView(save); Button test = new Button(this); test.setText("Kiểm tra ngay"); root.addView(test);
        status = new TextView(this); status.setTextSize(14); status.setPadding(0,dp(16),0,0); status.setText(Config.prefs(this).getString(Config.LAST_STATUS,"Chưa kiểm tra.")); root.addView(status);
        TextView info = new TextView(this); info.setText("• Kiểm tra nền tối thiểu mỗi 15 phút.\n• Android có thể trì hoãn tác vụ để tiết kiệm pin.\n• Token chỉ lưu cục bộ trên máy.\n• FR24 API là dịch vụ có giới hạn/chi phí theo gói."); info.setTextSize(13); info.setPadding(0,dp(18),0,0); root.addView(info);
        setContentView(root);
        save.setOnClickListener(v->{saveConfig(); PollReceiver.schedule(this); status.setText("Đã bật. Lần kiểm tra tiếp theo sẽ được Android thực hiện theo lịch nền.");});
        test.setOnClickListener(v->{saveConfig(); status.setText("Đang kiểm tra…"); new Thread(()->{String s=PollReceiver.poll(this,true); runOnUiThread(()->status.setText(s));}).start();});
        enabled.setOnCheckedChangeListener((button,checked)->{Config.prefs(this).edit().putBoolean(Config.ENABLED,checked).apply(); if(checked) PollReceiver.schedule(this); else PollReceiver.cancel(this);});
    }
    void saveConfig(){Config.prefs(this).edit().putString(Config.TOKEN,token.getText().toString().trim()).putBoolean(Config.ENABLED,enabled.isChecked()).apply();}
    int dp(int x){return (int)(x*getResources().getDisplayMetrics().density+.5f);}
}
