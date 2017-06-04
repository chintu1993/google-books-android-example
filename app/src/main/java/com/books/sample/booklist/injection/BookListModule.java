package com.books.sample.booklist.injection;

import com.books.sample.booklist.dataaccess.BookListProcessorImpl;
import com.books.sample.booklist.platform.BookListProcessor;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class BookListModule {
    @Provides
    @Singleton
    BookListProcessor provideBookListProcessor() {
        return new BookListProcessorImpl();
    }
}
