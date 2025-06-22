package com.example.bibliodex.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView listCurrentBooks;
    private Button btnAddBook;
    private Button btnSeeShelf;
    private BookVM bookVM;
    private ArrayList<Book> bookList;
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.listCurrentBooks = findViewById(R.id.listCurrent);
        listCurrentBooks.setLayoutManager(new LinearLayoutManager(this));
        this.btnAddBook = findViewById(R.id.btnAddBook);
        this.btnSeeShelf = findViewById(R.id.btnSeeShelf);
        this.bookVM = new BookVM(this);
        this.bookList = bookVM.getAllBooks();
        this.bookAdapter = new BookAdapter(this, bookList);
        this.listCurrentBooks.setAdapter(bookAdapter);
        this.btnAddBook.setOnClickListener(this::displayAddBookWindow);
    }

    private void displayAddBookWindow(View view) {
        Intent intent = new Intent(this, AddBookWindow.class);
        startActivity(intent);
    }

}