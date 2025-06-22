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

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private ArrayList<Book> bookList;
    private Context context;

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.bookList = books;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCover;
        TextView textTitle, textAuthorYear, textStatus, textPage, textRating;

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

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.textTitle.setText(book.getTitle());
        holder.textAuthorYear.setText(book.getAuthor() + " - " + book.getPublicationYear());
        holder.textRating.setText(book.getRating() + "/5");

        if (book.isRead()) {
            holder.textStatus.setText(context.getString(R.string.read));
            holder.textPage.setText(""); // pas besoin d'afficher la page
        } else {
            holder.textStatus.setText(context.getString(R.string.inProgress));;
            holder.textPage.setText(context.getString(R.string.page) + " " + book.getPageActual());
        }

        // Charger la couverture depuis une URI avec Glide
        if (book.getCoverUri() != null && !book.getCoverUri().isEmpty()) {
            Glide.with(context)
                    .load(Uri.parse(book.getCoverUri()))
                    .centerCrop()
                    .into(holder.imageCover);
        } else {
            holder.imageCover.setImageResource(R.drawable.default_book); // image par défaut
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
