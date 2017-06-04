package com.books.sample.shared.datatransfer.dataaccess;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Optional;

public final class OptionalDomainDataAdapter<D, E> implements DomainDataAdapter<Optional<D>, Optional<E>> {

    private final DomainDataAdapter<D, E> wrappedAdapter;

    public OptionalDomainDataAdapter(DomainDataAdapter<D, E> wrappedAdapter) {
        checkNotNull(wrappedAdapter);

        this.wrappedAdapter = wrappedAdapter;
    }

    @Override
    public Optional<E> toExternalData(Optional<D> domainData) {
        checkNotNull(domainData);
        if (domainData.isPresent()) {
            return Optional.fromNullable(wrappedAdapter.toExternalData(domainData.get()));
        } else {
            return Optional.absent();
        }
    }
}
