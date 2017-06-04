package com.books.sample.shared.platform;

import static android.os.Looper.getMainLooper;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.util.concurrent.Futures.addCallback;
import static com.google.common.util.concurrent.MoreExecutors.listeningDecorator;
import static java.util.concurrent.Executors.newCachedThreadPool;

import android.os.Handler;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import timber.log.Timber;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BackgroundTaskController {

    private final ListeningExecutorService executor;
    private final Handler uiHandler;

    @Inject
    public BackgroundTaskController() {
        final ThreadFactory threadFactory = new ThreadFactoryBuilder().setPriority(Thread.MIN_PRIORITY).build();
        executor = listeningDecorator(newCachedThreadPool(threadFactory));
        uiHandler = new Handler(getMainLooper());
    }

    public <T> void doBackground(final Callable<T> asyncTask, final BackgroundTaskControllerListener<T> listener) {
        Timber.d("doBackground");
        checkNotNull(asyncTask);
        checkNotNull(listener);

        final ListenableFuture<T> listenableFuture = executor.submit(asyncTask);
        addCallback(listenableFuture, new FutureCallback<T>() {

            @Override
            public void onSuccess(@Nullable final T result) {
                Timber.d("%s: onSuccess", Thread.currentThread().getName());
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onSuccessful(result);
                    }
                });
            }

            @Override
            public void onFailure(Throwable t) {
                Timber.d(t, "onFailure");
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFailed();
                    }
                });
            }
        });
    }
}
