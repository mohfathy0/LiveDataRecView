package com.example.livedatarecview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    myViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel= ViewModelProviders.of(this).get(myViewModel.class);
        mViewModel.init();
        mViewModel.getHeros().observe(this, animeModels -> {
            mAdapter.notifyDataSetChanged();
        });
        mRecyclerView = findViewById(R.id.mRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecAdapter(this,mViewModel.getHeros().getValue());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
