/*
 * Copyright (c) 2016 Cleverlance Enterprise Solutions a.s.<br/>
 * http://www.cleverlance.com<br/>
 * All Rights Reserved.
 */

package com.books.sample.shared.datatransfer.dataaccess;

/**
 * Exception to be thrown when Response DTO parsing to response domain object inside {@link ResponseAdapter#toDomainData(MbApiResponseDto)}
 * fails
 *
 * @author Ivan Martos <ivan.martos@cleverlance.com>
 */
public class ExternalDataException extends RuntimeException {

    public ExternalDataException(String detailMessage) {
        super(detailMessage);
    }

    public ExternalDataException(Throwable throwable) {
        super(throwable);
    }
}
