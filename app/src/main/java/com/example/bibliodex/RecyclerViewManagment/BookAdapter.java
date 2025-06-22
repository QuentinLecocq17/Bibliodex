package com.example.bibliodex.RecyclerViewManagment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.bibliodex.Model.Book;
import com.example.bibliodex.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for displaying a list of Book objects in a RecyclerView.
 * Implements filtering functionality to allow searching by title or author.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> implements Filterable {
    private ArrayList<Book> bookList;
    private ArrayList<Book> bookListFull; // Full list for filtering
    private Context context;

    /**
     * Constructs a new BookAdapter.
     *
     * @param context The context in which the adapter is created.
     * @param books   The initial list of books to display.
     */
    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.bookList = books;
        this.bookListFull = new ArrayList<>(books); // Initialize the full list
    }

    @Override
    public Filter getFilter() {
        return bookFilter;
    }

    /**
     * Filter implementation for searching books by title or author.
     * Updates the displayed list based on the search constraint.
     */
    private Filter bookFilter = new Filter() {
        /**
         * Performs filtering on the full book list based on the given constraint.
         *
         * @param constraint The search text entered by the user.
         * @return FilterResults containing the filtered list of books.
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Book> filteredList = new ArrayList<>();

            // If no search text, return the full list
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(bookListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                // Add books whose title or author contains the search text
                for (Book book : bookListFull) {
                    if (book.getTitle().toLowerCase().contains(filterPattern) ||
                            book.getAuthor().toLowerCase().contains(filterPattern)) {
                        filteredList.add(book);
                    }
                }
            }

            // Prepare the filter results
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        /**
         * Publishes the filtering results to update the displayed book list.
         *
         * @param constraint The search text entered by the user.
         * @param results    The results of the filtering operation.
         */
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            bookList.clear();
            bookList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    /**
     * ViewHolder for displaying book information in the RecyclerView.
     * Holds references to the views for each data item.
     */
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCover;
        TextView textTitle, textAuthorYear, textStatus, textPage;
        RatingBar ratingBar;

        public BookViewHolder(View itemView) {
            super(itemView);
            imageCover = itemView.findViewById(R.id.imageCover);
            textTitle = itemView.findViewById(R.id.textTitle);
            textAuthorYear = itemView.findViewById(R.id.textAuthorYear);
            textStatus = itemView.findViewById(R.id.textStatus);
            textPage = itemView.findViewById(R.id.textPage);
            ratingBar = itemView.findViewById(R.id.ratingBar); // Remplace textRating
        }
    }

    /**
     * Inflates the layout for a book item and creates a new BookViewHolder.
     *
     * @param parent   The parent view group.
     * @param viewType The view type of the new view.
     * @return A new BookViewHolder instance.
     */
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    /**
     * Binds the data of a Book object to the views in the BookViewHolder.
     *
     * @param holder   The BookViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.textTitle.setText(book.getTitle());
        holder.textAuthorYear.setText(book.getAuthor() + " - " + book.getPublicationYear());
        holder.ratingBar.setRating(book.getRating()); // Utilise le RatingBar

        if (book.isRead()) {
            holder.textStatus.setText(context.getString(R.string.read));
            holder.textPage.setText("");
        } else {
            holder.textStatus.setText(context.getString(R.string.inProgress));
            holder.textPage.setText(context.getString(R.string.page) + " " + book.getPageActual());
        }

        if (book.getCoverUri() != null && !book.getCoverUri().isEmpty()) {
            Glide.with(context)
                    .load(Uri.parse(book.getCoverUri()))
                    .centerCrop()
                    .into(holder.imageCover);
        } else {
            holder.imageCover.setImageResource(R.drawable.default_book);
        }
    }

    /**
     * Updates the book list and notifies the adapter of data changes.
     *
     * @param newBookList The new list of books to display.
     */
    public void updateBookList(ArrayList<Book> newBookList) {
        this.bookList = newBookList;
        this.bookListFull = new ArrayList<>(newBookList);
        notifyDataSetChanged();
    }

    /**
     * Returns the total number of items in the book list.
     *
     * @return The size of the book list.
     */
    @Override
    public int getItemCount() {
        return bookList.size();
    }
}