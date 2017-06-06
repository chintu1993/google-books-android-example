package com.books.sample.booklist.dataaccess;

import com.books.sample.shared.datatransfer.dataaccess.ExternalDataException;
import com.books.sample.shared.datatransfer.dataaccess.SafeExternalDataAdapter;
import com.books.sample.shared.domain.Uri;

public class UriAdapter extends SafeExternalDataAdapter<Uri, String> {

    @Override
    protected Uri toDomainDataIgnoringUncheckedExceptions(String data) throws ExternalDataException {
        return new Uri(android.net.Uri.parse(data));
    }
}
