package com.a1051743852.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);

        ListView listView = (ListView)findViewById(R.id.trainer_list);
        String[] teachers = {"Anna", "Emma", "Edith", "Wilson", "Warren", "Leon", "Bella"};
        String[] motto = {
                "Success belongs to the persevering",
                "Life lies in movement.",
                "If we chase perfection we can catch excellence",
                "Gym doesn't change live, People do.",
                "Reading is to the mind while exercise to the body.",
                "Gym doesn't change live, People do.",
                " Wealth is nothing without health."
        };
        int[] imageId = {R.drawable.trainer1, R.drawable.trainer2, R.drawable.trainer3, R.drawable.trainer4, R.drawable.trainer5, R.drawable.trainer6, R.drawable.trainer7};
         List<Map<String,Object>> listitems = new ArrayList<Map<String,Object>>();
         for(int i = 0;i<imageId.length;i++){
             Map<String,Object> map = new HashMap<String,Object>();
             map.put("image",imageId[i]);
             map.put("motto",motto[i]);
             map.put("teacher",teachers[i]);
             listitems.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this,listitems,R.layout.activity_trainer_list_item,new String[]{"teacher","motto","image"},new int[]{
                R.id.nameTxt,R.id.infoTxt,R.id.imageCircle
        });
         listView.setAdapter(adapter);
        }
    }



