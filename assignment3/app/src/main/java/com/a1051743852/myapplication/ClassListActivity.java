package com.a1051743852.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        ListView listView = (ListView)findViewById(R.id.class_list);
        String[] className = {"basketball basis", "soccer basis", "golf basis", "volleyball basis", "badminton advance", "bicycle skills", "breaststroke learning","Parkour primary"};
        String[] classPrice = {
                "$36","$67","$199","$49","$9","$599","$232","$88"
        };
        int[] imageId = {R.drawable.class1, R.drawable.class2, R.drawable.class3, R.drawable.class4, R.drawable.class5, R.drawable.class6, R.drawable.class2, R.drawable.class8};
        List<Map<String,Object>> listitems = new ArrayList<Map<String,Object>>();
        for(int i = 0;i<imageId.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",imageId[i]);
            map.put("className",className[i]);
            map.put("classPrice",classPrice[i]);
            listitems.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this,listitems,R.layout.activity_class_list_item,new String[]{"image","className","classPrice"},new int[]{
                R.id.classView,R.id.classNameTxt,R.id.classPriceTxt
        });
        listView.setAdapter(adapter);
    }
    public void video_play_onclick(View view){
        Intent intent = new Intent(ClassListActivity.this,InternetVideo.class);
        startActivity(intent);

    }
}



