package com.books.sample.booklist.injection;

import com.books.sample.booklist.ui.BookListFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface BookListFragmentSubcomponent extends AndroidInjector<BookListFragment> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookListFragment> {}
}
