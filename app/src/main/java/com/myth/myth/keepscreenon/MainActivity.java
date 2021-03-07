package com.myth.myth.keepscreenon;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private HandlerThread ht;
    private Handler h;
    private Runnable r = null;
    private int i;
    private long MINUTE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        createT();
        MINUTE = 1000 * 60 * 60;
        delayM();
        Toast.makeText(MainActivity.this, "Keep screen on 60 min. Thread Id: " + i,
                Toast.LENGTH_SHORT).show();
    }

    private void createT() {
        if (ht != null) ht.quit();
        ht = new HandlerThread("ht");
        ht.start();
        h = new Handler(ht.getLooper());

        r = new Runnable() {
            @Override
            public void run() {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        };
    }

    private void delayM() {
        h.postDelayed(r, MINUTE);
        i = ht.getThreadId();
    }

    public void min30(View v) {
        createT();
        MINUTE = 1000 * 60 * 30;
        delayM();
        Toast.makeText(MainActivity.this, "Keep screen on 30 min. Thread Id: " + i,
                Toast.LENGTH_LONG).show();
    }

    public void min15(View v) {
        createT();
        MINUTE = 1000 * 60 * 15;
        delayM();
        Toast.makeText(MainActivity.this, "Keep screen on 15 min. Thread Id: " + i,
                Toast.LENGTH_LONG).show();
    }

    public void infinite(View view) {
        createT();
        MINUTE = 1000 * 60 * 60 * 5;
        delayM();
        Toast.makeText(MainActivity.this, "Keep screen on 5 h. Thread Id: " + i,
                Toast.LENGTH_LONG).show();
    }

    public void min60(View view) {
        createT();
        MINUTE = 1000 * 60 * 60;
        delayM();
        Toast.makeText(MainActivity.this, "Keep screen on 1 h. Thread Id: " + i,
                Toast.LENGTH_LONG).show();
    }

    public void btn2h(View view) {
        createT();
        MINUTE = 1000 * 60 * 60 * 2;
        delayM();
        Toast.makeText(MainActivity.this, "Keep screen on 2 h. Thread Id: " + i,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    public void itemHelp(MenuItem item) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setPositiveButton("OK", null);
        adb.setTitle("Help");
        adb.setMessage("???");
        AlertDialog ad = adb.create();
        ad.show();
    }

    public void itemAbout(MenuItem item) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setPositiveButton("OK", null);
        try {
            adb.setTitle("Keep Screen on v" + getPackageManager().getPackageInfo(getPackageName(),
                    0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        adb.setMessage("Development: Stark C.");
        AlertDialog ad = adb.create();
        ad.show();
    }
}
