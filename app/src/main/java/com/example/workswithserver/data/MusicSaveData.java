package com.example.workswithserver.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "MusicSaveData")
public class MusicSaveData {

    private @PrimaryKey(autoGenerate = true)
    int id;
    @NonNull
    private String musician;
    @NonNull
    private String nameTrack;
    private String currentTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusician() {
        return musician;
    }

    public void setMusician(String musician) {
        this.musician = musician;
    }

    public String getNameTrack() {
        return nameTrack;
    }

    public void setNameTrack(String nameTrack) {
        this.nameTrack = nameTrack;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    @Override
    public String toString() {
        return  "id = " + id +
                ", musician = " + musician +
                ", nameTrack = " + nameTrack +
                ", currentTime = " + currentTime;
    }
}



