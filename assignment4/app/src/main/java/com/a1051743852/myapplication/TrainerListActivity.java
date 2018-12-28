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

//
//        WebService_trainers s = new WebService_trainers();
//        String jsonString = s.executeHttpGet();


        if (checkNetwork()) {
            String jsonString = MainActivity.getJsonString();
            List<TrainerBean> trainerList = new ArrayList<>();

            //测试json数据，删除下面一行代码可以实时获取服务器的教练信息
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
            String introduce[] = new String[trainerList.size()];

            for (int i = 0; i < trainerList.size(); i++) {
                teachers[i] = trainerList.get(i).getName();
                motto[i] = trainerList.get(i).getMotto();
                introduce[i] = trainerList.get(i).getIntroduce();

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

            //将数据存入本地数据库
            //打开或创建test.db数据库
            SQLiteDatabase db = openOrCreateDatabase("android.db", Context.MODE_PRIVATE, null);
            db.execSQL("DROP TABLE IF EXISTS coach");
            //创建person表
            db.execSQL("CREATE TABLE coach (name VARCHAR PRIMARY KEY , motto VARCHAR, introduce VARCHAR)");
            for (int i = 0; i < trainerList.size(); i++) {
                db.execSQL("INSERT INTO coach VALUES (?, ?, ?)", new Object[]{trainerList.get(i).getName(),
                        trainerList.get(i).getMotto(), trainerList.get(i).getIntroduce()});
            }
            //关闭当前数据库
            db.close();


        } else {
            //从本地数据库读取数据
            SQLiteDatabase db = openOrCreateDatabase("android.db", Context.MODE_PRIVATE, null);
            Cursor c = db.rawQuery("SELECT * FROM coach ", null);
            int index = 0;
            String teachers[] = new String[c.getCount()];
            String motto[] = new String[c.getCount()];
            String introduce[] = new String[c.getCount()];
            int[] imageId = new int[c.getCount()];
            while (c.moveToNext()) {
                String name1 = c.getString(c.getColumnIndex("name"));
                String motto1 = c.getString(c.getColumnIndex("motto"));
                String introduce1 = c.getString(c.getColumnIndex("introduce"));
                teachers[index] = name1;
                motto[index] = motto1;
                introduce[index] = introduce1;
                imageId[index] = R.drawable.trainer1;
                index++;
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
            c.close();
        }

    }

    // 检测网络
    private boolean checkNetwork() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager.getActiveNetworkInfo() != null) {
            return connManager.getActiveNetworkInfo().isAvailable();
        }
        return false;
    }

}