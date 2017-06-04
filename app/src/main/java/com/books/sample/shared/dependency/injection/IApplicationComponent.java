package com.books.sample.shared.dependency.injection;

import com.books.sample.BooksApplication;
import com.books.sample.main.injection.MainActivityModule;
import com.books.sample.shared.dialog.injection.DialogModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

import javax.inject.Singleton;

@Component(modules = {MainActivityModule.class, AndroidInjectionModule.class, DialogModule.class})
@Singleton
public interface IApplicationComponent {
    void inject(BooksApplication inject);
}
