package com.example.rxjava.ui.main.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava.R;
import com.example.rxjava.ui.main.viewModel.PostViewModel;

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
        fetchPosts();
    }

    private void fetchPosts(){
        postViewModel.postsLiveData.observe(this, postModelList -> adapter.setData(postModelList));
    }
}