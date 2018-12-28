package com.a1051743852.myapplication;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }
    public void oneOnClick(View view){
        Intent intent = new Intent(MainActivity.this,Changepage.class);
        startActivity(intent);

    }
    public void twoOnClick(View view){
        Intent intent = new Intent(MainActivity.this,ClassListActivity.class);
        startActivity(intent);

    }
    public void threeOnClick(View view){
        Intent intent = new Intent(MainActivity.this,Clubchange.class);
        startActivity(intent);

    }
    public void fourOnClick(View view){
        Intent intent = new Intent(MainActivity.this,Posterchange.class);
        startActivity(intent);

    }



    public void fiveOnClick(View view){
        Intent intent = new Intent(MainActivity.this,TrainerListActivity.class);
        startActivity(intent);

    }
    public void sixOnClick(View view){
        Intent intent = new Intent(MainActivity.this,Mychange.class);
        startActivity(intent);

    }


}
