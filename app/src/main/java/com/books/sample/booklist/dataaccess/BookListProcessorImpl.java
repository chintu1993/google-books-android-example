package com.books.sample.booklist.dataaccess;

import android.support.annotation.NonNull;

import com.books.sample.booklist.domain.BookListResponse;
import com.books.sample.booklist.domain.FilterPagingRequest;
import com.books.sample.booklist.platform.BookListProcessor;
import com.books.sample.booklist.platform.BookListProcessorListener;
import com.books.sample.shared.datatransfer.dataaccess.BaseRestProcessorImpl;
import com.books.sample.shared.datatransfer.dataaccess.Environment;
import com.books.sample.shared.dependency.injection.Injector;

import retrofit2.Call;

import javax.annotation.Nonnull;

public class BookListProcessorImpl extends BaseRestProcessorImpl<BookListService, BookListResponseDto, BookListResponse, FilterPagingRequest, BookListProcessorListener> implements
        BookListProcessor {
    private static final String QUERY_PREFIX = "intitle:";

    private final BookListResponseAdapter responseAdapter = new BookListResponseAdapter();

    @NonNull
    @Nonnull
    @Override
    protected Call<BookListResponseDto> createCall(@Nonnull FilterPagingRequest domainArgument) {
        return service.fetchBookList(QUERY_PREFIX + domainArgument.getTitleFilter(), Environment.API_KEY);
    }

    @Override
    protected BookListResponse convertToDomainData(BookListResponseDto response) {
        return responseAdapter.toDomainData(response);
    }

    @Override
    protected void notifyOnDomainDataSuccess(@Nonnull BookListProcessorListener listener, @Nonnull BookListResponse domainData) {
        listener.onBooksRetrieved(domainData);
    }

    @Override
    protected Class<BookListService> getService() {
        return BookListService.class;
    }

    @Override
    protected void inject() {
        Injector.INSTANCE.getApplicationComponent().inject(this);
    }
}
