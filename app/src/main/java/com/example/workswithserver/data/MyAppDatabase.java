package com.example.workswithserver.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.workswithserver.dao.DaoMusicSaveData;

@Database(entities = {MusicSaveData.class}, version = 3)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract DaoMusicSaveData daoMusicSaveData();
}


