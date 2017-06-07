package com.books.sample.shared.ui;

import com.books.sample.shared.domain.Author;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterators;

import java.util.List;

public class AuthorListFormatter {
    public static Formatter<List<Author>> AUTHOR_FORMATTER = new Formatter<List<Author>>() {
        @Override
        public String format(List<Author> source) {
            return Joiner.on(", ").join(Iterators.transform(source.iterator(), new Function<Author, String>() {
                @Override
                public String apply(Author input) {
                    return input.getValue();
                }
            }));
        }
    };
}
