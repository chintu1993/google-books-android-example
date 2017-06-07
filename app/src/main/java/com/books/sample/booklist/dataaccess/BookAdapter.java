package com.books.sample.booklist.dataaccess;

import com.books.sample.booklist.domain.Book;
import com.books.sample.shared.datatransfer.dataaccess.ExternalDataException;
import com.books.sample.shared.datatransfer.dataaccess.OptionalExternalDataAdapter;
import com.books.sample.shared.datatransfer.dataaccess.SafeExternalDataAdapter;
import com.books.sample.shared.datatransfer.dataaccess.SafeExternalDataListAdapter;
import com.books.sample.shared.domain.Amount;
import com.books.sample.shared.domain.Author;
import com.books.sample.shared.domain.Uri;
import com.google.common.base.Optional;

class BookAdapter extends SafeExternalDataAdapter<Book, BookItemDto> {
    private final RatingAdapter ratingAdapter = new RatingAdapter();
    private final PublishInfoAdapter publishInfoAdapter = new PublishInfoAdapter();
    private final OptionalExternalDataAdapter<Amount, PriceDto> retailPriceAdapter = new OptionalExternalDataAdapter<>(new RetailPriceAdapter());
    private final OptionalExternalDataAdapter<Uri, String> uriAdapter = new OptionalExternalDataAdapter<>(new UriAdapter());
    private final SafeExternalDataListAdapter<Author, String> authorAdapter = new SafeExternalDataListAdapter<>(new AuthorAdapter());

    @Override
    protected Book toDomainDataIgnoringUncheckedExceptions(BookItemDto data) throws ExternalDataException {
        BookVolumeInfoDto volumeInfoDto = data.getVolumeInfoDto();
        return new Book(data.getId(),
                data.geteTag(),
                volumeInfoDto.getTitle(),
                volumeInfoDto.getSubtitle(),
                volumeInfoDto.getDescription(),
                volumeInfoDto.getPageCount(),
                volumeInfoDto.getLanguage(),
                ratingAdapter.toDomainData(volumeInfoDto),
                authorAdapter.toDomainData(volumeInfoDto.getAuthorsList()),
                publishInfoAdapter.toDomainData(volumeInfoDto),
                getSalesUri(data),
                uriAdapter.toDomainData(volumeInfoDto.getImageLinksDto().getThumbnailUri()),
                retailPriceAdapter.toDomainData(data.getSaleInfoDto().getRetailPriceDto()));
    }

    private Optional<Uri> getSalesUri(BookItemDto data) {
        if (data.getSaleInfoDto().getBuyLink().isPresent()) {
            return Optional.of(new Uri(android.net.Uri.parse(data.getSaleInfoDto().getBuyLink().get())));
        } else if (data.getAccessInfoDto().getWebReaderLink().isPresent()) {
            return Optional.of(new Uri(android.net.Uri.parse(data.getAccessInfoDto().getWebReaderLink().get())));
        } else {
            return Optional.absent();
        }
    }
}
