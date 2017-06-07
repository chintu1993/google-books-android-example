package com.books.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.books.sample.booklist.ui.BookListFragment;
import com.books.sample.shared.android.platform.AndroidController;
import com.books.sample.shared.screenflow.platform.ScreenFlowController;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasDispatchingSupportFragmentInjector;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements HasDispatchingSupportFragmentInjector {

    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;
    @Inject AndroidController androidController;
    @Inject ScreenFlowController screenFlowController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        screenFlowController.initialize(R.id.container_layout, getSupportFragmentManager());
        androidController.setContext(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        screenFlowController.showInitialFragment(new BookListFragment());
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
