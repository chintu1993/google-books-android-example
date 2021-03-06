package com.books.sample.booklist.ui;

import static com.books.sample.shared.utils.infrastructure.UiUtils.dpToPx;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.view.View;
import android.widget.ProgressBar;

import com.books.sample.R;
import com.books.sample.booklist.domain.Book;
import com.books.sample.booklist.platform.BookListController;
import com.books.sample.booklist.platform.BookListListener;
import com.books.sample.booklist.ui.BookListAdapter.BookAdapterListener;
import com.books.sample.shared.ui.BaseFragment;
import com.books.sample.shared.ui.GridSpacingItemDecoration;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import timber.log.Timber;

import java.util.List;

import javax.inject.Inject;

public class BookListFragment extends BaseFragment implements BookAdapterListener, BookListListener {

    @Inject BookListController bookListController;

    @BindView(R.id.book_list_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.search) SearchView searchView;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    private BookListAdapter bookListAdapter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.list_layout;
    }

    @Override
    protected Fragment getThis() {
        return this;
    }

    @Override
    protected void onAfterViewCreated(View view, @Nullable Bundle savedInstanceState) {
        bookListAdapter = new BookListAdapter(getContext(), this);
        bookListController.setBookListListener(this);

        searchView.setQueryHint("Find books");
        searchView.onActionViewExpanded();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bookListController.requestBooks(query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setQuery(bookListController.getQuery(), true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10, getResources()), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(bookListAdapter);

    }

    @Override
    public void onBookClicked(Book book) {
        Timber.d("on book clicked");
        bookListController.showBookDetail(book);
    }

    @Override
    public void onMoreItemsRequested() {
        bookListController.requestMoreBooks();
    }

    @Override
    public void onBooksRequested() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBooksRetrieved(@NotNull List<Book> bookList) {
        bookListAdapter.addItems(bookList);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onClearBooks() {
        bookListAdapter.clearItems();
    }
}
