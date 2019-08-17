package com.example.livedatarecview;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class myViewModel extends ViewModel {

    private MutableLiveData<List<AnimeModel>> mMainModel;
    private myRepository mRepository;

    public void init(){
        if (mMainModel!=null){
            Log.i("mylog_ViewModel","hello from init not null");
            return;
        }
        mRepository=myRepository.getInstance();
        mMainModel=mRepository.getData();
        Log.i("mylog_ViewModel","hello from init is null");
    }
    public LiveData<List<AnimeModel>> getHeros(){
        Log.i("mylog_ViewModel","hello from get Profile");
        return mMainModel;
    }


}
