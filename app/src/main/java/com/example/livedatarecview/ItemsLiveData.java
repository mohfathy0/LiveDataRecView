package com.example.livedatarecview;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import javax.annotation.Nullable;

public class ItemsLiveData extends LiveData<List<AnimeModel>> implements EventListener<DocumentSnapshot> {

    private  Query query;
    DocumentReference documentReference;
    private ListenerRegistration listenerRegistration = null;
    private MutableLiveData<List<AnimeModel>> mListOfDocument;

    public ItemsLiveData(String collectionName) {
        this.mListOfDocument = new MutableLiveData<>();
      //  query = FirebaseFirestore.getInstance().collection(collectionName).whereEqualTo("id", 1);
        documentReference = FirebaseFirestore.getInstance().collection("anime").document("Naruto");
        onActive();
        SendLog("ItemsLiveData");
    }

    @Override
    protected void onActive() {
        if (listenerRegistration == null) {
         //   listenerRegistration = query.addSnapshotListener(this::onEvent);
         listenerRegistration=documentReference.addSnapshotListener(this::onEvent);
        }
       // SendLog("onActive");
    }

    @Override
    protected void onInactive() {
        listenerRegistration.remove();
        SendLog("onInactive");
    }

//    @Override
//    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
//        SendLog("onEvent");
//        if (queryDocumentSnapshots != null && e == null) {
//            mListOfDocument.postValue(queryDocumentSnapshots.toObjects(AnimeModel.class));
//        } else {
//            // handle errors
//
//        }
//    }

    public LiveData<List<AnimeModel>> getListOfDocument() {
        SendLog("LiveData");
        return mListOfDocument;
    }
    public  void SendLog(String Message){
        Log.i("mylog_ItemsLiveData",Message);
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                SendLog("onEvent");
        if (documentSnapshot != null && e == null) {
            SendLog(documentSnapshot.getString("charName").toString());

        } else {
            // handle errors

        }
    }
}