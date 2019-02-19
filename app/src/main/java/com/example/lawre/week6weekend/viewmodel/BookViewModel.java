package com.example.lawre.week6weekend.viewmodel;

import android.util.Log;

import com.example.lawre.week6weekend.model.Book;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BookViewModel
{
    public static String jsonResponse;
    public static void makeApiCall(String url)
    {
        String TAG = "TAG_";
        Log.d(TAG, "makeApiCall: ");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback()
        {

            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e("Ok_HTTP", "onFailure: ",e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                jsonResponse = response.body().string();
                Book[] BookArray = new Gson().fromJson(jsonResponse, Book[].class);
                List<Book> bookList = Arrays.asList(BookArray);
                ArrayList<Book> books = new ArrayList<>(bookList);
                EventBus.getDefault().post(books);
            }
        });
    }
}
