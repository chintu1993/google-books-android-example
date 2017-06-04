package com.books.sample.booklist.dataaccess;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

class PriceDto {
    @SerializedName("amount") private BigDecimal amount;
    @SerializedName("currencyCode") private String currency;

    BigDecimal getAmount() {
        return amount;
    }

    String getCurrency() {
        return currency;
    }
}
