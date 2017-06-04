package com.books.sample.booklist.dataaccess;

import com.books.sample.booklist.domain.Rating;
import com.books.sample.shared.datatransfer.dataaccess.ExternalDataException;
import com.books.sample.shared.datatransfer.dataaccess.SafeExternalDataAdapter;
import com.google.common.base.Optional;

class RatingAdapter extends SafeExternalDataAdapter<Optional<Rating>, BookVolumeInfoDto> {

    @Override
    protected Optional<Rating> toDomainDataIgnoringUncheckedExceptions(BookVolumeInfoDto data) throws ExternalDataException {
        if (data.getRatingsCount().isPresent() && data.getAverageRating().isPresent()) {
            return Optional.of(new Rating(data.getAverageRating().get(), data.getRatingsCount().get()));
        } else {
            return Optional.absent();
        }
    }
}
