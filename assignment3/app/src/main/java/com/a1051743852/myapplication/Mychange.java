package com.a1051743852.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Mychange extends AppCompatActivity {

    protected void onCreate(Bundle sa){
        super.onCreate(sa);
        setContentView(R.layout.my);
    }

    public void one1OnClick(View view){
        Intent intent = new Intent(Mychange.this,Clubchange.class);
        startActivity(intent);

    }
    public void my_coach(View view){
        Intent intent = new Intent(Mychange.this,MyCoach.class);
        startActivity(intent);

    }
}
