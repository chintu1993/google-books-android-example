package com.books.sample.booklist.dataaccess;

import com.google.gson.annotations.SerializedName;

class ImageLinksDto {
    @SerializedName("thumbnail") private String thumbnailUri;

    String getThumbnailUri() {
        return thumbnailUri;
    }
}
