package com.example.bibliodex.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bibliodex.Adapter.BookAdapter;
import com.example.bibliodex.Model.Book;
import com.example.bibliodex.R;
import com.example.bibliodex.ViewModel.BookVM;

import java.util.ArrayList;

public class ListBookWindow extends AppCompatActivity {

    private SearchView searchView;
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private ArrayList<Book> bookList;
    private BookVM bookVM;
    private Button btnDisplayAll;
    private Button btnDisplayRead;
    private Button btnDisplayUnread;
    private Button btnDisplayFav;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_book_window);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.bookVM = new BookVM(this);
        this.searchView = findViewById(R.id.filter);
        this.searchView.clearFocus();
        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.bookList = this.bookVM.getAllBooks();
        this.adapter = new BookAdapter(this, bookList);
        this.recyclerView.setAdapter(adapter);
        this.btnDisplayAll = findViewById(R.id.btnDisplayAll);
        this.btnDisplayFav = findViewById(R.id.btnDisplayFav);
        this.btnDisplayRead = findViewById(R.id.btnDisplayRead);
        this.btnDisplayUnread = findViewById(R.id.btnDisplayProgress);
        this.btnBack = findViewById(R.id.btnBack);
        this.btnDisplayAll.setOnClickListener(this::displayAll);
        this.btnDisplayFav.setOnClickListener(this::displayFav);
        this.btnDisplayRead.setOnClickListener(this::displayRead);
        this.btnDisplayUnread.setOnClickListener(this::displayUnread);
        this.btnBack.setOnClickListener(this::displayMainActivity);
    }

    private void displayAll(View view) {
        this.bookList = this.bookVM.getAllBooks();
        this.adapter.updateBookList(bookList);
    }

    private void displayRead(View view) {
        this.bookList = this.bookVM.getAllReadBooks();
        this.adapter.updateBookList(bookList);
    }

    private void displayUnread(View view) {
        this.bookList = this.bookVM.getAllUnreadBooks();
        this.adapter.updateBookList(bookList);
    }

    private void displayFav(View view) {
        this.bookList = this.bookVM.getAllFavoriteBooks();
        this.adapter.updateBookList(bookList);
    }

    private void displayMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}