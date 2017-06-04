package com.books.sample.booklist.dataaccess;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

interface BookListService {

    @GET("volumes")
    Call<BookListResponseDto> fetchBookList(@Query("q") String query, @Query("key") String key);
}
