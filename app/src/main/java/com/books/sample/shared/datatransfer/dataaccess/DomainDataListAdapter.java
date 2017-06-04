package com.books.sample.shared.datatransfer.dataaccess;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <D> type of domain data
 * @param <E> type of external data
 */
public final class DomainDataListAdapter<D, E> implements DomainDataAdapter<List<D>, List<E>> {

    private final DomainDataAdapter<D, E> elementAdapter;

    public DomainDataListAdapter(DomainDataAdapter<D, E> elementAdapter) {
        checkNotNull(elementAdapter);

        this.elementAdapter = elementAdapter;
    }

    @Override
    public List<E> toExternalData(List<D> domainData) {
        List<E> result = new ArrayList<>();

        for (D domainElement : domainData) {
            result.add(elementAdapter.toExternalData(domainElement));
        }

        return result;
    }
}
