package com.example.homework41.ui.form;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class FormModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "text")
    private String text;

    public FormModel(int id, String text) {
        this.id = id;
        this.text = text;
    }
              @Ignore
    public FormModel(String text) {
        this.text = text;
    }


    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "FormModel{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
