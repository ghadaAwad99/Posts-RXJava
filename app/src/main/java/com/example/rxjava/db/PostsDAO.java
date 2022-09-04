package com.example.rxjava.db;

import android.database.Observable;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rxjava.model.PostModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PostsDAO {
    @Insert
    Completable insertPost(List<PostModel> posts);

    @Query("SELECT * FROM posts_table")
    Single<List<PostModel>> getAllPosts();
}
