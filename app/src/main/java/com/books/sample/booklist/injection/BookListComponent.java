package com.books.sample.booklist.injection;

import com.books.sample.booklist.dataaccess.BookListProcessorImpl;

public interface BookListComponent {
    void inject(BookListProcessorImpl arg);
}
