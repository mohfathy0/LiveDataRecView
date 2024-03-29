package com.example.livedatarecview;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class myRepository {
    private ArrayList<AnimeModel> model = new ArrayList<>();
    private static myRepository instance;

    public static myRepository getInstance() {
        if (instance == null) {
            instance = new myRepository();
        }
        return instance;
    }

    public MutableLiveData<List<AnimeModel>> getData() {
        final MutableLiveData<List<AnimeModel>> data = new MutableLiveData<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference cRef = db.collection("anime");
        cRef.addSnapshotListener((queryDocumentSnapshots, e) -> {
            try {
                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            DocumentSnapshot dcAdd = dc.getDocument();
                            model.add(dcAdd.toObject(AnimeModel.class));
                            break;
                        case MODIFIED:
                            DocumentSnapshot dcMod = dc.getDocument();
                            AnimeModel mm = model.stream()
                                    .filter(p -> p.getId() == dcMod.toObject(AnimeModel.class).getId())
                                    .findFirst()
                                    .orElse(null);
                            model.set(model.indexOf(mm), dcMod.toObject(AnimeModel.class));
                            break;
                        case REMOVED:
                            DocumentSnapshot dcRem = dc.getDocument();
                            AnimeModel mr = model.stream().filter(p -> p.getId() == dcRem.toObject(AnimeModel.class).getId())
                                    .findFirst()
                                    .orElse(null);
                            model.remove(mr);
                            break;
                        default:
                            break;
                    }

                }
            }catch (Exception ex){
                Log.i("ex Exception",ex.getMessage());
            }

            data.setValue(model);
        });


        data.setValue(model);
        return data;
    }


}
