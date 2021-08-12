package com.example.taskmaster;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertOne(Task task);

    @Query("SELECT * FROM task WHERE task_title LIKE :title")
    Task findByName(String title);

    @Query("SELECT * FROM task")
    List<Task> findAll();

    @Delete
    void deleteItem(Task task);


}
