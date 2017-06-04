package com.books.sample.booklist.dataaccess;

import com.books.sample.booklist.domain.PublishInfo;
import com.books.sample.shared.datatransfer.dataaccess.ExternalDataException;
import com.books.sample.shared.datatransfer.dataaccess.SafeExternalDataAdapter;

class PublishInfoAdapter extends SafeExternalDataAdapter<PublishInfo, BookVolumeInfoDto> {
    @Override
    protected PublishInfo toDomainDataIgnoringUncheckedExceptions(BookVolumeInfoDto data) throws ExternalDataException {
        if (data.getPublisher().isPresent()) {
            return new PublishInfo(data.getPublishedDate(), data.getPublisher().get());
        } else {
            return new PublishInfo(data.getPublishedDate());
        }
    }
}
