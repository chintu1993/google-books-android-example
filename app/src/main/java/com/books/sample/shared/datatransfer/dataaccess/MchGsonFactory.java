package com.books.sample.shared.datatransfer.dataaccess;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MchGsonFactory {

    private static Gson sInstance;

    public static Gson get() {
        if (sInstance == null) {
            sInstance = create();
        }
        return sInstance;
    }

    private static Gson create() {
        return new GsonBuilder().create();
    }
}
