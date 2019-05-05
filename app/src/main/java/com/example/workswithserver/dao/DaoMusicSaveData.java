package com.example.workswithserver.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.workswithserver.data.MusicSaveData;

import java.util.List;

@Dao
public interface DaoMusicSaveData {

    @Insert
    public void insert(MusicSaveData musicSaveData);

    @Delete
    public void delete(MusicSaveData musicSaveData);

    @Query("DELETE FROM MusicSaveData")
    public void deleteAll();

    @Update
    public void update(MusicSaveData musicSaveData);

    @Query("SELECT * FROM MusicSaveData")
    public List<MusicSaveData> getAll();

    @Query("SELECT * FROM MusicSaveData WHERE id=:id")
    public MusicSaveData getById(int id);

    @Query("SELECT * FROM MusicSaveData WHERE nameTrack=:nameTrack")
    public MusicSaveData getByName(String nameTrack);

    @Query("SELECT * FROM MusicSaveData where id = (SELECT MAX(id) FROM MusicSaveData)")
    public MusicSaveData getLast();

}

