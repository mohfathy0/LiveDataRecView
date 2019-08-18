package com.example.livedatarecview;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;


public class ItemsLiveData2 extends LiveData<AnimeModel> implements EventListener<QuerySnapshot> {
//https://www.youtube.com/watch?v=WXc4adLMDqk&feature=share&fbclid=IwAR1_nYdrUDP9cm4dD_qT4LUshpl22cb4-HZeb2OrSGbBJ-dYWILcwCcBARQ
    //https://github.com/Bassem-Kamal/TryUsingMVVMWithFireBase/blob/master/ItemRepository.java
    //https://stackoverflow.com/questions/49798814/android-architecture-components-with-firebase-specifically-firestore
    private Query query;
    private ListenerRegistration listenerRegistration = null;
    private MutableLiveData<List<AnimeModel>> mListOfDocument;




    public ItemsLiveData2(FirebaseFirestore db, String Collection) {
        this.mListOfDocument = new MutableLiveData<>();
        query= db.collection(Collection).whereEqualTo("charName","Naruto sama");
        listenerRegistration = query.addSnapshotListener(this);
        SendLog("Constractor");
    }

    public ItemsLiveData2() {
    }

    public MutableLiveData<List<AnimeModel>> getListOfDocument() {
        SendLog("getListOfDocument");
        ArrayList<AnimeModel>model= new ArrayList<>();
        mListOfDocument.setValue(model);
        return mListOfDocument;

    }
    @Override
    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
        if (queryDocumentSnapshots != null && e == null) {
            SendLog("onEvent setValuse");
            mListOfDocument.setValue(queryDocumentSnapshots.toObjects(AnimeModel.class));
            SendLog(queryDocumentSnapshots.toObjects(AnimeModel.class).get(0).getCharTitle());
        } else {
            // handle errors
            SendLog("onEvent");
        }
    }

    @Override
    protected void onActive() {
        super.onActive();
        if (listenerRegistration == null) {
            listenerRegistration = query.addSnapshotListener(this);
        }
        SendLog("onActive");
    }



    @Override
    protected void onInactive() {
        super.onInactive();
        listenerRegistration.remove();
        SendLog("onInactive");
    }

    public void SendLog(String message){
        Log.i("mylog_ItemsLiveData2",message);
    }
}
