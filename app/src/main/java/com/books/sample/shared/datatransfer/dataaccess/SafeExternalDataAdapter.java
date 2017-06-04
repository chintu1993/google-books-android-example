package com.books.sample.shared.datatransfer.dataaccess;

import static com.google.common.base.Preconditions.checkNotNull;

import timber.log.Timber;

import javax.annotation.Nonnull;

public abstract class SafeExternalDataAdapter<D, E> implements ExternalDataAdapter<D, E> {

    @Override
    @Nonnull
    public final D toDomainData(E data) throws ExternalDataException {
        try {
            return checkNotNull(toDomainDataIgnoringUncheckedExceptions(data));
        } catch (RuntimeException exception) {
            Timber.e("Invalid data: %s : %s", data, exception.getMessage());
            throw new ExternalDataException(exception);
        }
    }

    protected abstract D toDomainDataIgnoringUncheckedExceptions(E data) throws ExternalDataException;
}
