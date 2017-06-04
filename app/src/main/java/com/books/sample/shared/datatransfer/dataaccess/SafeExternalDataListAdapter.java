package com.books.sample.shared.datatransfer.dataaccess;

import static com.google.common.base.Preconditions.checkNotNull;

import timber.log.Timber;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

/**
 * @param <D> type of domain data
 * @param <E> type of external data
 */
public final class SafeExternalDataListAdapter<D, E> implements ExternalDataAdapter<List<D>, List<? extends E>> {

    private final ExternalDataAdapter<D, E> elementAdapter;

    public SafeExternalDataListAdapter(ExternalDataAdapter<D, E> elementAdapter) {
        checkNotNull(elementAdapter);

        this.elementAdapter = elementAdapter;
    }

    @Nonnull
    @Override
    public List<D> toDomainData(List<? extends E> externalData) {
        List<D> result = new ArrayList<>();

        for (E externalElement : externalData) {
            try {
                result.add(elementAdapter.toDomainData(externalElement));
            } catch (RuntimeException exception) {
                Timber.e("%s: Invalid list item (ignored): %s: %s",
                        elementAdapter.getClass().getSimpleName(),
                        externalElement,
                        exception.getMessage());
            }
        }

        return result;
    }
}
