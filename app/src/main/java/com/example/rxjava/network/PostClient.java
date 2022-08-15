package com.example.rxjava.network;

import com.example.rxjava.model.PostModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static PostClient postClientInstance;
    private PostInterface postInterface;

    public PostClient() {
        postInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(PostInterface.class);
    }

    public static PostClient getPostClientInstance() {
        if (postClientInstance == null)
            postClientInstance = new PostClient();
        return postClientInstance;
    }

    public Observable<List<PostModel>> getPosts(){
        return postInterface.getPosts();
    }
}
