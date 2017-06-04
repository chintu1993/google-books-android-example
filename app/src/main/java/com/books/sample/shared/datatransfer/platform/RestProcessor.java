package com.books.sample.shared.datatransfer.platform;

import javax.annotation.Nonnull;

public interface RestProcessor<RequestDomainObject, ResponseListener extends ProcessorListener> {
    void request(@Nonnull RequestDomainObject requestData, @Nonnull ResponseListener listener);
}
