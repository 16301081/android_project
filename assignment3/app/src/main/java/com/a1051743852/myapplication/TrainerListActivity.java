package com.a1051743852.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);

        ListView listView = findViewById(R.id.trainer_list);


//        WebService_trainers s = new WebService_trainers();
//        String jsonString = s.executeHttpGet();

            String jsonString = MainActivity.getJsonString();
            List<TrainerBean> trainerList = new ArrayList<>();

            //测试
           jsonString = "[{\"name\":\"Anny\",\"motto\":\"Success belongs to the persevering\",\"intoduce\":\"she is a girl\"},{\"name\":\"Bob\",\"motto\":\"If we chase perfection we can catch excellence\",\"intoduce\":\"he like basketball\"},{\"name\":\"Emma\",\"motto\":\"Life lies in movement.\",\"intoduce\":\"he is a boy \"},{\"name\":\"JinHua\",\"motto\":\"Wealth is nothing without health\",\"intoduce\":\"he is my brother\"},{\"name\":\"Lily\",\"motto\":\"Gym doesn't change live, People do\",\"intoduce\":\"she is beautiful\"},{\"name\":\"QingWen\",\"motto\":\"Gym doesn't change live, People do\",\"intoduce\":\"she is the most beautiful girl\"},{\"name\":\"Zhang Dongqing\",\"motto\":\"I like badminton\",\"intoduce\":\"Zhang began playing badminton at the age of five in 1988.[3] he joined the national team in 2000.[3] in August 2002, he first ranked first in the badminton world.\"}]";

            try {
                JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (jsonObject != null) {
                        String name = jsonObject.optString("name");
                        String motto = jsonObject.optString("motto");
                        String introduce = jsonObject.optString("introduce");

                        // 封装Java对象
                        TrainerBean trainerBean = new TrainerBean(name, motto, introduce);
                        trainerList.add(trainerBean);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String teachers[] = new String[trainerList.size()];
            String motto[] = new String[trainerList.size()];
            // String introduce[]=new String [trainerList.size()];

            for (int i = 0; i < trainerList.size(); i++) {
                teachers[i] = trainerList.get(i).getName();
                motto[i] = trainerList.get(i).getMotto();
                // introduce[i]=trainerList.get(i).getIntroduce();

            }


            int[] imageId = new int[trainerList.size()];
            for (int i = 0; i < trainerList.size(); i++) {
                imageId[i] = R.drawable.trainer1;
            }

            List<Map<String, Object>> listitems = new ArrayList<>();
            for (int i = 0; i < imageId.length; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("image", imageId[i]);
                map.put("motto", motto[i]);
                map.put("teacher", teachers[i]);
                listitems.add(map);
            }

            SimpleAdapter adapter = new SimpleAdapter(this, listitems, R.layout.activity_trainer_list_item, new String[]{"teacher", "motto", "image"}, new int[]{
                    R.id.nameTxt, R.id.infoTxt, R.id.imageCircle
            });
            listView.setAdapter(adapter);


    }
}