package com.example.livedatarecview;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference cRef = db.collection("anime");

        cRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){

                    String str = documentSnapshot.getString("charName");
                    Log.i("mylog_FireBaseReo",str);
                    AnimeModel m = new AnimeModel(documentSnapshot.toObject(AnimeModel.class).getImageURL(),documentSnapshot.toObject(AnimeModel.class).getCharName(),documentSnapshot.toObject(AnimeModel.class).getCharTitle());
                    model.add(m);

                }

            }
        });

        data.setValue(model);
        return data;
    }

}
