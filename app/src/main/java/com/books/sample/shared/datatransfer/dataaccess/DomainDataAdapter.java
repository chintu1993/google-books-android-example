package com.books.sample.shared.datatransfer.dataaccess;

/**
 * @param <D> type of domain data
 * @param <E> type of external data
 */
public interface DomainDataAdapter<D, E> {

    E toExternalData(D domainData);
}
