package com.example.livedatarecview;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class myRepository {
    private ArrayList<AnimeModel> model = new ArrayList<>();
    private static myRepository instance;

    public static myRepository getInstance() {
        if (instance == null) {
            instance = new myRepository();
            Log.i("mylog_Repository", "hello from getInstnace is null");
        }
        Log.i("mylog_Repository", "hello from getInstnace not null");
        return instance;
    }

    public MutableLiveData<List<AnimeModel>> getData() {

        MutableLiveData<List<AnimeModel>> data = new MutableLiveData<>();
//        model.add(new AnimeModel("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png", "Naruto", "Hukage"));
//        model.add(new AnimeModel("https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png", "Kakashi", "Sense"));


        


        data.setValue(model);
        return data;
    }
}
