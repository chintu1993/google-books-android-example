package com.books.sample.booklist.dataaccess;

import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class BookListResponseDto {

    @SerializedName("totalItems") private int totalItems;

    @SerializedName("items") private List<BookItemDto> bookList;

    int getTotalItems() {
        return totalItems;
    }

    List<BookItemDto> getBookList() {
        return bookList != null ? bookList : ImmutableList.<BookItemDto>of();
    }
}
