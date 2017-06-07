package com.books.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}
