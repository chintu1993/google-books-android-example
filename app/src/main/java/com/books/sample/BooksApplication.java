package com.books.sample;

import android.app.Activity;
import android.app.Application;

import com.books.sample.shared.dependency.injection.Injector;
import com.books.sample.shared.utils.infrastructure.TagPrefixDebugTree;
import com.facebook.stetho.Stetho;

import net.danlew.android.joda.JodaTimeAndroid;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasDispatchingActivityInjector;
import timber.log.Timber;

import javax.inject.Inject;

public class BooksApplication extends Application implements HasDispatchingActivityInjector {
    public static final String LOG_TAG_PREFIX = "BOOKS:";

    @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);
        Timber.plant(new TagPrefixDebugTree(LOG_TAG_PREFIX));
        Stetho.initializeWithDefaults(this);
        Injector.INSTANCE.initialize();
        Injector.INSTANCE.getApplicationComponent().inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
