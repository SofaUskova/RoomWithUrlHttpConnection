package com.example.workswithserver.activity;

import android.annotation.TargetApi;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.workswithserver.R;
import com.example.workswithserver.asyncTask.MyAsyncTask;
import com.example.workswithserver.data.MyAppDatabase;
import com.example.workswithserver.data.MusicSaveData;

import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private static MyAppDatabase myAppDatabase;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);
        myAppDatabase = Room.databaseBuilder(this,
                MyAppDatabase.class, "music_database")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        //myAppDatabase.daoMusicSaveData().deleteAll();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new MyAsyncTask(myAppDatabase, textView).execute();
            }
        }, 0, 1000);
    }

    public void showAll(View view) {
        try {
            List<MusicSaveData> allConvert = myAppDatabase.daoMusicSaveData().getAll();

            Intent intent = new Intent(this, ShowAll.class);
            intent.putExtra("array",  convertLisToStringArray(allConvert)); //передаем стринговый массив объектов
            startActivity(intent);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private String[] convertLisToStringArray(List<MusicSaveData> allConvert) {
        List<String> strings = allConvert.stream()
                .map(object -> Objects.toString(object, null))
                .collect(Collectors.toList());

        String[] all = new String[strings.size()];
        all = strings.toArray(all);
        return all;
    }
}