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
    private ItemsLiveData2 itemsLiveData;
    private static myRepository2 instance;

    public static myRepository2 getInstance() {
        if (instance == null) {
            instance = new myRepository2();
            SendLog("getInstance");
        }

        return instance;
    }

    public LiveData<List<AnimeModel>> getData() {
        if (itemsLiveData==null){
            SendLog("itemLiveData is null");
            itemsLiveData = new ItemsLiveData2("anime");
        }
        SendLog("itemLiveData returned");
        return itemsLiveData.getListOfDocument();

    }
    public static void SendLog(String message){
        Log.i("mylog_myRepository2",message);
    }

}
