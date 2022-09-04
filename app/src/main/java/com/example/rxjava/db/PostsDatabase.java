package com.example.rxjava.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.rxjava.model.PostModel;

@Database(entities = PostModel.class, version = 2)
public abstract class PostsDatabase extends RoomDatabase {

    private static PostsDatabase DBInstance;
    public abstract PostsDAO postsDAO();

    public static synchronized PostsDatabase getDBInstance(Context context){
        if (DBInstance == null){
            DBInstance = Room.databaseBuilder(context.getApplicationContext(), PostsDatabase.class, "PostsDB")
                    .build();
        }
        return DBInstance;
    }
}
