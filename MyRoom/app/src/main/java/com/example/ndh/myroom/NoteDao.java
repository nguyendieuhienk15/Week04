package com.example.ndh.myroom;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note note);


    @Query("DELETE FROM note_table")
    void deleteAll();

    @Query("DELETE FROM note_table WHERE note like :name")
    void deleteByNote(String name);

    @Query("SELECT * from note_table")
    LiveData<List<Note>> getAllNotes();
}