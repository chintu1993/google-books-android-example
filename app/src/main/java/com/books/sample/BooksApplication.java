package com.books.sample;

import android.app.Activity;
import android.app.Application;

import com.books.sample.shared.dependency.injection.DaggerIApplicationComponent;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;

import javax.inject.Inject;

public class BooksApplication extends Application implements HasDispatchingActivityInjector {
    @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerIApplicationComponent.create().inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
