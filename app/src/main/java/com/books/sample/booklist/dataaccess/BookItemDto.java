package com.books.sample.booklist.dataaccess;

import com.google.gson.annotations.SerializedName;

class BookItemDto {
    @SerializedName("id") private String id;
    @SerializedName("etag") private String eTag;
    @SerializedName("volumeInfo") private BookVolumeInfoDto volumeInfoDto;
    @SerializedName("accessInfo") private AccessInfoDto accessInfoDto;
    @SerializedName("saleInfo") private SaleInfoDto saleInfoDto;

    public String getId() {
        return id;
    }

    String geteTag() {
        return eTag;
    }

    BookVolumeInfoDto getVolumeInfoDto() {
        return volumeInfoDto;
    }

    AccessInfoDto getAccessInfoDto() {
        return accessInfoDto;
    }

    SaleInfoDto getSaleInfoDto() {
        return saleInfoDto;
    }
}
