package com.example.bibleapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApp  extends Application {

    //JsonManager jsonManager = new JsonManager();

  //  NetworkingManager networkingManger = new NetworkingManager();
    static ExecutorService executorService = Executors.newFixedThreadPool(4);

    static Handler mainhandler = new Handler(Looper.getMainLooper());
    //DataBaseManager dataBaseManager = new DataBaseManager();
ArrayList<BibleItem> appBibleList =new ArrayList<>(0);


}
