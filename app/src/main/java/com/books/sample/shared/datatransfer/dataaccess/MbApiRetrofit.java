package com.books.sample.shared.datatransfer.dataaccess;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;


public class MbApiRetrofit {
    private final Retrofit retrofit;

    public MbApiRetrofit(RestClient mchRestClient) {
        retrofit = createRetrofit(mchRestClient);
    }

    private Retrofit createRetrofit(RestClient mchRestClient) {

        return new Builder().baseUrl(Environment.BASE_URL)
                .client(mchRestClient.getClient())
                .addConverterFactory(GsonConverterFactory.create(MchGsonFactory.get()))
                .build();
    }

    /** Creates an API resource implementation defined by the specified interface. */
    public <T> T createResource(Class<T> service) {
        return retrofit.create(service);
    }
}
