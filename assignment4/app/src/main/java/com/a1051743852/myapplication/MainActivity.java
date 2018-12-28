package com.a1051743852.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    static String jsonString;
//   private  TextView coach_view;
//   private static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

//        coach_view=findViewById(R.id.coach);
//        coach_view.setOnClickListener(this);
    }

    public void oneOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, Changepage.class);
        startActivity(intent);

    }

    public void twoOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, ClassListActivity.class);
        startActivity(intent);

    }

    public void threeOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, Clubchange.class);
        startActivity(intent);

    }

    public void fourOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, Posterchange.class);
        startActivity(intent);

    }


    public void fiveOnClick(View view) {
        new Thread(new MyThread()).start();
        Intent intent = new Intent(MainActivity.this, TrainerListActivity.class);
        startActivity(intent);

    }

    public void sixOnClick(View view) {
        Intent intent = new Intent(MainActivity.this, Mychange.class);
        startActivity(intent);

    }


    // 子线程接收数据，主线程修改数据
    public class MyThread implements Runnable {
        @Override
        public void run() {
            jsonString = WebService_trainers.executeHttpGet();
        }
    }

    public static String getJsonString() {
        return jsonString;
    }
}
