package com.books.sample.shared.dependency.injection;

import com.books.sample.BooksApplication;
import com.books.sample.main.injection.MainActivityModule;

import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {MainActivityModule.class, AndroidInjectionModule.class})
public interface IApplicationComponent {
    void inject(BooksApplication inject);
}
