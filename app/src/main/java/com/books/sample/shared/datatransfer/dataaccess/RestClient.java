package com.books.sample.shared.datatransfer.dataaccess;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RestClient {

    private final OkHttpClient client;

    @Inject
    public RestClient() {
        try {
            client = createClient();
        } catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException e) {
            throw new IllegalStateException(e);
        }
    }

    private OkHttpClient createClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.connectTimeout(Environment.CONNECTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        httpClient.readTimeout(Environment.READ_TIMEOUT_SECONDS, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(Level.BODY);
        httpClient.addInterceptor(logging);
        httpClient.addNetworkInterceptor(new StethoInterceptor());

        return httpClient.build();
    }

    public OkHttpClient getClient() {
        return client;
    }
}
