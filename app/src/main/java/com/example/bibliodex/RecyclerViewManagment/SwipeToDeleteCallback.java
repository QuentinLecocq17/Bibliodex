package com.example.bibliodex.RecyclerViewManagment;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bibliodex.Model.Book;
import com.example.bibliodex.R;
import com.example.bibliodex.Storage.BookDAO;

public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

    private BookAdapter adapter;
    private BookDAO bookDAO;
    private final android.content.Context context;

    public SwipeToDeleteCallback(Context context, BookAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.context = context;
        this.adapter = adapter;
        this.bookDAO = new BookDAO(context);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();
        Book book = adapter.getBookAtPosition(position);
        showPopup(book,position);
    }

    private void showPopup(Book book, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.popup_delete, null);

        ImageView imgView = dialogView.findViewById(R.id.imageCover);
        if (book.getCoverUri() != null && !book.getCoverUri().isEmpty()) {
            imgView.setImageURI(Uri.parse(book.getCoverUri()));
        } else {
            imgView.setImageResource(R.drawable.default_book);
        }

        TextView textTitle = dialogView.findViewById(R.id.textTitle);
        textTitle.setText(book.getTitle());

        TextView textAuthorYear = dialogView.findViewById(R.id.textAuthorYear);
        textAuthorYear.setText(book.getAuthor() + " - " + book.getPublicationYear());

        AppCompatButton btnYes = dialogView.findViewById(R.id.button);
        AppCompatButton btnNo = dialogView.findViewById(R.id.button2);

        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        btnYes.setOnClickListener(v -> {
            adapter.removeBook(position);
            bookDAO.removeBook(book);
            dialog.dismiss();
        });

        btnNo.setOnClickListener(v -> {
            adapter.notifyItemChanged(position);
            dialog.dismiss();
        });

        dialog.show();
    }
}
