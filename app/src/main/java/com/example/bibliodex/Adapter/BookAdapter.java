package com.example.bibliodex.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.bibliodex.R;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bibliodex.Model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for displaying a list of books in a RecyclerView.
 * Uses Glide to load cover images.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    /** List of books to display */
    private ArrayList<Book> bookList;
    /** Application context */
    private Context context;

    /**
     * Constructor for BookAdapter.
     * @param context the application context
     * @param books the list of books to display
     */
    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.bookList = books;
    }

    /**
     * ViewHolder class for book items.
     */
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCover;
        TextView textTitle, textAuthorYear, textStatus, textPage, textRating;

        /**
         * Initializes the views for a book item.
         * @param itemView the item view
         */
        public BookViewHolder(View itemView) {
            super(itemView);
            imageCover = itemView.findViewById(R.id.imageCover);
            textTitle = itemView.findViewById(R.id.textTitle);
            textAuthorYear = itemView.findViewById(R.id.textAuthorYear);
            textStatus = itemView.findViewById(R.id.textStatus);
            textPage = itemView.findViewById(R.id.textPage);
            textRating = itemView.findViewById(R.id.textRating);
        }
    }

    /**
     * Creates a new view for a RecyclerView item.
     * @param parent the parent ViewGroup
     * @param viewType the type of the view
     * @return a new BookViewHolder
     */
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    /**
     * Binds the data of a book to the corresponding view.
     * @param holder the ViewHolder to bind data to
     * @param position the position of the item in the list
     */
    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.textTitle.setText(book.getTitle());
        holder.textAuthorYear.setText(book.getAuthor() + " - " + book.getPublicationYear());
        holder.textRating.setText(book.getRating() + "/5");

        if (book.isRead()) {
            holder.textStatus.setText(context.getString(R.string.read));
            holder.textPage.setText(""); // no need to display page if read
        } else {
            holder.textStatus.setText(context.getString(R.string.inProgress));
            holder.textPage.setText(context.getString(R.string.page) + " " + book.getPageActual());
        }

        // Load the cover image from a URI with Glide
        if (book.getCoverUri() != null && !book.getCoverUri().isEmpty()) {
            Glide.with(context)
                    .load(Uri.parse(book.getCoverUri()))
                    .centerCrop()
                    .into(holder.imageCover);
        } else {
            holder.imageCover.setImageResource(R.drawable.default_book); // default image
        }
    }

    /**
     * Updates the book list and notifies the adapter of the change.
     * @param newBookList the new list of books
     */
    public void updateBookList(ArrayList<Book> newBookList) {
        this.bookList = newBookList;
        notifyDataSetChanged();
    }

    /**
     * Returns the number of items in the list.
     * @return the size of the book list
     */
    @Override
    public int getItemCount() {
        return bookList.size();
    }
}