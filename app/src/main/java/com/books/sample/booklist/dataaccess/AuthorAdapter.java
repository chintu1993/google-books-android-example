package com.books.sample.booklist.dataaccess;

import com.books.sample.shared.datatransfer.dataaccess.ExternalDataException;
import com.books.sample.shared.datatransfer.dataaccess.SafeExternalDataAdapter;
import com.books.sample.shared.domain.Author;

class AuthorAdapter extends SafeExternalDataAdapter<Author, String> {

    @Override
    protected Author toDomainDataIgnoringUncheckedExceptions(String data) throws ExternalDataException {
        return new Author(data);
    }
}
