package com.example.rxjava.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rxjava.Network.PostClient;
import com.example.rxjava.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class PostViewModel extends ViewModel {
    private static final String TAG = "PostViewModel";
    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();
    LiveData<List<PostModel>> postsLiveData = postsMutableLiveData;

    public void getPosts() {
        Observable<List<PostModel>> postsObservable = PostClient.getPostClientInstance().getPosts()
                //up stream to IO thread [Background]
                .subscribeOn(Schedulers.io())
                //down stream to Main Thread [UI]
                .observeOn(AndroidSchedulers.mainThread());
        postsObservable.subscribe(value -> postsMutableLiveData.setValue(value), error -> Log.d(TAG, "getPosts: " + error));
    }
}
