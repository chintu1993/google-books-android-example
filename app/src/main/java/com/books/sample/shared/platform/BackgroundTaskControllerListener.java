package com.books.sample.shared.platform;


public interface BackgroundTaskControllerListener<T> {

    void onSuccessful(T result);

    void onFailed();
}
