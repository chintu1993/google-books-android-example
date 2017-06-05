package com.books.sample.main.injection;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.books.sample.MainActivity;
import com.books.sample.booklist.injection.BookListFragmentSubcomponent;
import com.books.sample.booklist.ui.BookListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {IMainActivitySubcomponent.class, BookListFragmentSubcomponent.class})
public abstract class MainActivityModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindYourActivityInjectorFactory(IMainActivitySubcomponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(BookListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindBookListFragmentInjectorFactory(BookListFragmentSubcomponent.Builder builder);
}
