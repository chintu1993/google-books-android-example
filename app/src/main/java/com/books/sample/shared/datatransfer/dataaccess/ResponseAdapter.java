package com.books.sample.shared.datatransfer.dataaccess;

/**
 * @param <R> Response DTO
 * @param <D> Response domain object
 */
public interface ResponseAdapter<D, R> extends ExternalDataAdapter<D, R> {}
