package com.books.sample.booklist.ui;

import android.app.Fragment;

import com.books.sample.R;
import com.books.sample.shared.ui.BaseFragment;

public class BookListFragment extends BaseFragment {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.list_layout;
    }

    @Override
    protected Fragment getThis() {
        return this;
    }
}
