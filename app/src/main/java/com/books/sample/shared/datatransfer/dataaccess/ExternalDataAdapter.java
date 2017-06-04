package com.books.sample.shared.datatransfer.dataaccess;

import javax.annotation.Nonnull;

/**
 * @param <D> type of domain data
 * @param <E> type of external data
 */
public interface ExternalDataAdapter<D, E> {

    @Nonnull
    D toDomainData(E data) throws ExternalDataException;
}
