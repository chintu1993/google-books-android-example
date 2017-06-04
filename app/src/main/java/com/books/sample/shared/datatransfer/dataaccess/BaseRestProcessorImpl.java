package com.books.sample.shared.datatransfer.dataaccess;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;

import com.books.sample.shared.datatransfer.platform.ProcessorListener;
import com.books.sample.shared.datatransfer.platform.RestProcessor;
import com.books.sample.shared.platform.BackgroundTaskController;
import com.books.sample.shared.platform.BackgroundTaskControllerListener;
import com.google.common.base.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.inject.Inject;

abstract class BaseRestProcessorImpl<Service, ResponseDto, ResponseDomainObject, RequestDomainObject, ResponseListener extends ProcessorListener> implements
        RestProcessor<RequestDomainObject, ResponseListener>,
        Callback<ResponseDto> {

    protected final Service service;

    @Inject BackgroundTaskController backgroundTaskController;
    @Inject MbApiRetrofit mbApiRetrofit;

    private Optional<ResponseListener> listener = Optional.absent();


    protected BaseRestProcessorImpl() {
        service = mbApiRetrofit.createResource(getService());
    }

    @Override
    public void request(RequestDomainObject requestData, ResponseListener listener) {
        this.listener = Optional.of(listener);
        final Call<ResponseDto> apiCall = createCall(requestData);
        apiCall.enqueue(this);
    }

    @NonNull
    @Nonnull
    protected abstract Call<ResponseDto> createCall(@Nonnull RequestDomainObject domainArgument);


    @Override
    public final void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
        if (response.isSuccessful()) {
            final ResponseDto apiResponse = response.body();
            processAndNotifySuccessfulResponse(apiResponse);
        } else {
            notifyOnFailure();
        }
    }

    protected void notifyOnFailure() {
        if (listener.isPresent()) {
            listener.get().onNoConnection();
        }
    }


    @Override
    public final void onFailure(Call<ResponseDto> call, Throwable t) {
        Timber.e(t, "Failure of retrofit call");
        notifyOnFailure();
    }


    private void processAndNotifySuccessfulResponse(final ResponseDto apiResponse) {
        backgroundTaskController.doBackground(new Callable<ResponseDomainObject>() {
            @Override
            public ResponseDomainObject call() throws Exception {
                return checkNotNull(convertToDomainData(apiResponse));
            }
        }, new BackgroundTaskControllerListener<ResponseDomainObject>() {
            @Override
            public void onSuccessful(ResponseDomainObject result) {
                notifyAllOnDomainDataSuccess(result);
            }

            @Override
            public void onFailed() {
                notifyOnFailure();
            }
        });
    }

    protected abstract ResponseDomainObject convertToDomainData(ResponseDto response);

    private void notifyAllOnDomainDataSuccess(ResponseDomainObject domainData) {
        if (listener.isPresent()) {
            notifyOnDomainDataSuccess(listener.get(), domainData);
        }
    }

    protected abstract void notifyOnDomainDataSuccess(@Nonnull ResponseListener listener, @Nonnull ResponseDomainObject domainData);

    protected abstract Class<Service> getService();
}
