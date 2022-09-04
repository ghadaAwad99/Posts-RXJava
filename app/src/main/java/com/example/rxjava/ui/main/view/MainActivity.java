package com.example.rxjava.ui.main.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava.R;
import com.example.rxjava.db.PostsDatabase;
import com.example.rxjava.model.PostModel;
import com.example.rxjava.ui.main.viewModel.PostViewModel;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    PostViewModel postViewModel;
    PostAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postViewModel =  new ViewModelProvider(this).get(PostViewModel.class);
        postViewModel.getPosts();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new PostAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        insertPostsToRoom();
        getPosts();
    }

    // get posts from API and insert them in Room
    private void insertPostsToRoom(){
        PostsDatabase postsDatabase = PostsDatabase.getDBInstance(this);
        postViewModel.postsLiveData.observe(this,
                postModelList -> postsDatabase.postsDAO().insertPost(postModelList)
                        .subscribeOn(Schedulers.io())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        }));
    }

    //get posts from Room
    private void getPosts(){
        PostsDatabase postsDatabase = PostsDatabase.getDBInstance(this);
        postsDatabase.postsDAO().getAllPosts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<PostModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<PostModel> postModels) {
                        adapter.setData(postModels);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}