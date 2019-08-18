package com.example.livedatarecview;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class myRepository2 {
    private static ItemsLiveData2 itemsLiveData;
    private static myRepository2 instance;
    private static MutableLiveData<List<AnimeModel>> model;
   private static FirebaseFirestore db;

    public static myRepository2 getInstance() {
        if (instance == null) {
            instance = new myRepository2();
           db = FirebaseFirestore.getInstance();
            itemsLiveData = new ItemsLiveData2(db,"anime");
            MutableLiveData<List<AnimeModel>> tempModel = new MutableLiveData<>();

            model=new MutableLiveData<>();
            model.setValue(tempModel.getValue());
            SendLog("getInstance");
        }

        return instance;
    }

    public LiveData<List<AnimeModel>> getData() {

        SendLog("itemLiveData returned");
        if (model.getValue()!=null){
            SendLog(model.getValue().toString());
        }

        return model;

    }
    public static void SendLog(String message){
        Log.i("mylog_myRepository2",message);
    }

}
