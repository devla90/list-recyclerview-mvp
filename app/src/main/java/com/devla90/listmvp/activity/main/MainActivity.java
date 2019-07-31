package com.devla90.listmvp.activity.main;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.devla90.listmvp.R;
import com.devla90.listmvp.model.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;
    List<Note> note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        presenter = new MainPresenter(this);
        presenter.getData();

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        presenter.getData();
                    }
                }
        );

        itemClickListener = (new MainAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //...
                String title = note.get(position).getNombre();
                Toast.makeText(MainActivity.this, title, Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Note> notes) {
        adapter = new MainAdapter(this,notes,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        note = notes;
        Log.e("On Get Result: ", note.toString());
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e("Error Api", message);
    }
}
