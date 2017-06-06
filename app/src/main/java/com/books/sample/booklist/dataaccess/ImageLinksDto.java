package com.books.sample.booklist.dataaccess;

import com.google.common.base.Optional;
import com.google.gson.annotations.SerializedName;

class ImageLinksDto {
    @SerializedName("thumbnail") private String thumbnailUri;

    Optional<String> getThumbnailUri() {
        return Optional.fromNullable(thumbnailUri);
    }
}
