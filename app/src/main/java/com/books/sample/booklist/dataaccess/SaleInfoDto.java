package com.books.sample.booklist.dataaccess;

import com.google.common.base.Optional;
import com.google.gson.annotations.SerializedName;

class SaleInfoDto {
    @SerializedName("buyLink") private String buyLink;
    @SerializedName("retailPrice") private PriceDto retailPriceDto;

    Optional<String> getBuyLink() {
        return Optional.fromNullable(buyLink);
    }

    Optional<PriceDto> getRetailPriceDto() {
        return Optional.fromNullable(retailPriceDto);
    }
}
