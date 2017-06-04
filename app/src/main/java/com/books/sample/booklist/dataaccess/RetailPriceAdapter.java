package com.books.sample.booklist.dataaccess;

import com.books.sample.shared.datatransfer.dataaccess.ExternalDataException;
import com.books.sample.shared.datatransfer.dataaccess.SafeExternalDataAdapter;
import com.books.sample.shared.domain.Amount;

class RetailPriceAdapter extends SafeExternalDataAdapter<Amount, PriceDto> {
    @Override
    protected Amount toDomainDataIgnoringUncheckedExceptions(PriceDto data) throws ExternalDataException {
        return new Amount(data.getAmount(), data.getCurrency());
    }
}
