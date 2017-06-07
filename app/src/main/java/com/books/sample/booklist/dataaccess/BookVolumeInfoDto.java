package com.books.sample.booklist.dataaccess;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class BookVolumeInfoDto {

    @SerializedName("title") private String title;
    @SerializedName("subtitle") private String subtitle;
    @SerializedName("description") private String description;
    @SerializedName("publisher") private String publisher;
    @SerializedName("language") private String language;
    @SerializedName("authors") private List<String> authorsList;
    @SerializedName("publishedDate") private String publishedDate;
    @SerializedName("pageCount") private int pageCount;
    @SerializedName("averageRating") private Double averageRating;
    @SerializedName("ratingsCount") private Integer ratingsCount;
    @SerializedName("imageLinks") private ImageLinksDto imageLinksDto;


    String getTitle() {
        return title;
    }

    Optional<String> getSubtitle() {
        return Optional.fromNullable(subtitle);
    }

    Optional<String> getDescription() {
        return Optional.fromNullable(description);
    }

    Optional<String> getPublisher() {
        return Optional.fromNullable(publisher);
    }

    String getLanguage() {
        return language;
    }

    List<String> getAuthorsList() {
        return authorsList != null ? authorsList : ImmutableList.<String>of();
    }

    String getPublishedDate() {
        return publishedDate;
    }

    int getPageCount() {
        return pageCount;
    }

    Optional<Double> getAverageRating() {
        return Optional.fromNullable(averageRating);
    }

    Optional<Integer> getRatingsCount() {
        return Optional.fromNullable(ratingsCount);
    }

    ImageLinksDto getImageLinksDto() {
        return imageLinksDto;
    }
}
