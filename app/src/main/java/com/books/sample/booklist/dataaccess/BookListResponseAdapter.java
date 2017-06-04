package com.books.sample.booklist.dataaccess;

import com.books.sample.booklist.domain.Book;
import com.books.sample.booklist.domain.BookListResponse;
import com.books.sample.shared.datatransfer.dataaccess.ExternalDataException;
import com.books.sample.shared.datatransfer.dataaccess.SafeExternalDataAdapter;
import com.books.sample.shared.datatransfer.dataaccess.SafeExternalDataListAdapter;

public class BookListResponseAdapter extends SafeExternalDataAdapter<BookListResponse, BookListResponseDto> {
    private final SafeExternalDataListAdapter<Book, BookItemDto> bookListAdapter = new SafeExternalDataListAdapter<>(new BookAdapter());

    @Override
    protected BookListResponse toDomainDataIgnoringUncheckedExceptions(BookListResponseDto data) throws ExternalDataException {
        return new BookListResponse(bookListAdapter.toDomainData(data.getBookList()), data.getTotalItems());
    }
}
