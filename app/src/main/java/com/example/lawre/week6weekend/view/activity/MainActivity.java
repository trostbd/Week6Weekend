package com.example.lawre.week6weekend.view.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.example.lawre.week6weekend.R;
import com.example.lawre.week6weekend.databinding.ActivityMainBinding;
import com.example.lawre.week6weekend.model.Book;
import com.example.lawre.week6weekend.view.adapter.RecyclerViewAdapter;
import com.example.lawre.week6weekend.viewmodel.BookViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    ActivityMainBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        activityMainBinding.executePendingBindings();
        activityMainBinding.rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EventBus.getDefault().register(this);
        BookViewModel.makeApiCall("http://de-coding-test.s3.amazonaws.com/books.json");
    }

    @Subscribe(threadMode=ThreadMode.MAIN)
    public void fillRecycler(ArrayList<Book> bookList)
    {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(bookList);
        activityMainBinding.rvRecyclerView.setAdapter(recyclerViewAdapter);
    }
}
