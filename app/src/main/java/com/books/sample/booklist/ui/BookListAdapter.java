package com.books.sample.booklist.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.books.sample.R;
import com.books.sample.booklist.domain.Book;
import com.books.sample.shared.domain.Author;
import com.bumptech.glide.Glide;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private final Context context;
    private final BookAdapterListener bookAdapterListener;
    private List<Book> bookList = Lists.newArrayList();

    private boolean isLoadingItems = false;

    public BookListAdapter(Context context, BookAdapterListener bookAdapterListener) {
        this.context = context;
        this.bookAdapterListener = bookAdapterListener;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list__item_layout, parent, false);
        return new BookViewHolder(itemView);
    }

    public void addItems(List<Book> addedBookList) {
        int previousItemPosition = bookList.size() > 0 ? bookList.size() - 1 : 0;
        bookList.addAll(addedBookList);
        notifyItemRangeInserted(previousItemPosition, addedBookList.size());
        isLoadingItems = false;
    }

    public void clearItems() {
        int previousCount = bookList.size();
        bookList = Lists.newArrayList();
        notifyItemRangeRemoved(0, previousCount);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        if(position >= getItemCount() - 3 && !isLoadingItems) {
            bookAdapterListener.onMoreItemsRequested();
            isLoadingItems = true;
        }

        final Book book = bookList.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(Joiner.on(", ").join(Iterators.transform(book.getAuthors().iterator(), new Function<Author, String>() {
            @Override
            public String apply(Author input) {
                return input.getValue();
            }
        })));
        if(book.getThumbnail().isPresent()){
            Glide.with(context).load(book.getThumbnail().get().getValue()).into(holder.thubmnail);
        }
        else {
            holder.thubmnail.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bookAdapterListener.onBookClicked(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public interface BookAdapterListener {
        void onBookClicked(Book book);

        void onMoreItemsRequested();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail_image_view) public ImageView thubmnail;
        @BindView(R.id.title_text_view) public TextView title;
        @BindView(R.id.author_text_view) public TextView author;

        BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
