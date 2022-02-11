package com.example.homework41.ui.databaseRoom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.homework41.ui.form.FormModel;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM formmodel")
    List<FormModel> getAllNote();

    @Insert
    void insertAllNote(FormModel... models);

    @Update
    void upDateNote(FormModel model);

    @Delete
    void delete(FormModel model);
}
