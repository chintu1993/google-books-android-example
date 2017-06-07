package com.books.sample.bookdetail.injection;

import com.books.sample.bookdetail.ui.BookDetailFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface BookDetailFragmentSubcomponent extends AndroidInjector<BookDetailFragment> {
    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<BookDetailFragment> {}
}
