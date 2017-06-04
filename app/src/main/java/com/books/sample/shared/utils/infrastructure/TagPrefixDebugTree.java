package com.books.sample.shared.utils.infrastructure;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import timber.log.Timber.DebugTree;

public class TagPrefixDebugTree extends DebugTree {
    private final String tagPrefix;

    public TagPrefixDebugTree(String tagPrefix) {
        checkNotNull(tagPrefix);
        checkArgument(!tagPrefix.isEmpty());

        this.tagPrefix = tagPrefix;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        super.log(priority, tagPrefix + tag, message, t);
    }
}
