package com.books.sample.shared.datatransfer.dataaccess;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Optional;

import javax.annotation.Nonnull;

public final class OptionalExternalDataAdapter<D, E> implements ExternalDataAdapter<Optional<D>, Optional<E>> {

    private final ExternalDataAdapter<D, E> wrappedAdapter;

    public OptionalExternalDataAdapter(@Nonnull ExternalDataAdapter<D, E> wrappedAdapter) {
        checkNotNull(wrappedAdapter);

        this.wrappedAdapter = wrappedAdapter;
    }

    @Override
    @Nonnull
    public Optional<D> toDomainData(Optional<E> data) throws ExternalDataException {
        checkNotNull(data);
        if (data.isPresent()) {
            try {
                return Optional.fromNullable(wrappedAdapter.toDomainData(data.get()));
            } catch (ExternalDataException ignore) {
            }
        }
        return Optional.absent();
    }
}

