package com.example.rxjava.Network;

import com.example.rxjava.pojo.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static PostClient postClientInstance;
    private PostInterface postInterface;

    public PostClient() {
        postInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PostInterface.class);
    }

    public static PostClient getPostClientInstance() {
        if (postClientInstance == null)
            postClientInstance = new PostClient();
        return postClientInstance;
    }

    public Call<List<PostModel>> getPosts(){
        return postInterface.getPosts();
    }
}
