package com.books.sample.booklist.dataaccess;

import com.google.common.base.Optional;
import com.google.gson.annotations.SerializedName;

class AccessInfoDto {
    @SerializedName("webReaderLink") private String webReaderLink;

    Optional<String> getWebReaderLink() {
        return Optional.fromNullable(webReaderLink);
    }
}
