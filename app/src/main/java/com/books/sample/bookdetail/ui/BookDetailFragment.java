package com.books.sample.bookdetail.ui;

import static com.books.sample.shared.ui.AuthorListFormatter.AUTHOR_FORMATTER;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.books.sample.R;
import com.books.sample.bookdetail.platform.BookDetailController;
import com.books.sample.booklist.domain.Book;
import com.books.sample.booklist.domain.Rating;
import com.books.sample.shared.domain.Amount;
import com.books.sample.shared.ui.BaseFragment;
import com.books.sample.shared.utils.infrastructure.UiUtils;
import com.bumptech.glide.Glide;
import com.google.common.base.Function;
import com.google.common.base.Optional;

import butterknife.BindView;
import butterknife.OnClick;

import javax.inject.Inject;

public class BookDetailFragment extends BaseFragment {

    @Inject BookDetailController bookDetailController;

    @BindView(R.id.fab) FloatingActionButton floatingActionButton;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.thumbnail_image_view) ImageView topImageView;
    @BindView(R.id.title_text_view) TextView titleTextView;
    @BindView(R.id.subtitle_text_view) TextView subtitleTextView;
    @BindView(R.id.description) TextView descriptionTextView;
    @BindView(R.id.authors) TextView authorsTextView;
    @BindView(R.id.rating) RatingBar ratingBar;
    @BindView(R.id.price_label) TextView priceLabelTextView;
    @BindView(R.id.price_value) TextView priceValueTextView;
    @BindView(R.id.pages_label) TextView pagesLabelTextView;
    @BindView(R.id.pages_value) TextView pagesValueTextView;
    @BindView(R.id.published_value) TextView publishedValueTextView;
    @BindView(R.id.publisher_value) TextView publisherValueTextView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.book_detail_layout;
    }

    @Override
    protected Fragment getThis() {
        return this;
    }

    @OnClick(R.id.fab)
    public void onFabClick() {
        bookDetailController.redirectToBookPurchase();
    }

    @Override
    protected void onAfterViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Book book = bookDetailController.getBook();
        UiUtils.setVisible(book.getBuyUri().isPresent(), floatingActionButton);

        UiUtils.setVisible(book.getThumbnail().isPresent(), collapsingToolbar);

        if (book.getThumbnail().isPresent()) {
            Glide.with(getContext()).load(book.getThumbnail().get().getValue()).into(topImageView);
        }

        titleTextView.setText(book.getTitle());
        subtitleTextView.setText(book.getSubtitle().orNull());
        descriptionTextView.setText(book.getDescription().orNull());
        authorsTextView.setText(AUTHOR_FORMATTER.format(book.getAuthors()));
        initializeRating(book.getRating());
        initializeKeyValue(priceLabelTextView, priceValueTextView, book.getPrice().transform(new Function<Amount, String>() {
            @Override
            public String apply(Amount input) {
                return String.format("%s %s", input.getValue(), input.getCurrency());
            }
        }));
        pagesValueTextView.setText(String.valueOf(book.getPageCount()));
        publishedValueTextView.setText(book.getPublishInfo().getPublishedDate());
        publisherValueTextView.setText(book.getPublishInfo().getPublisher());

    }

    private void initializeRating(Optional<Rating> rating) {
        boolean isRatingPresent = rating.isPresent();
        UiUtils.setVisible(isRatingPresent, ratingBar);
        if (isRatingPresent) {
            ratingBar.setRating((float) rating.get().getAverage());
        }
    }

    private void initializeKeyValue(TextView keyView, TextView valueView, Optional<String> value) {
        UiUtils.setVisible(value.isPresent(), keyView, valueView);
        valueView.setText(value.orNull());
    }
}
