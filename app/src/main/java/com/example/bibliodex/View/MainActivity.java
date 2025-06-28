package com.example.bibliodex.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bibliodex.RecyclerViewManagment.BookAdapter;
import com.example.bibliodex.Model.Book;
import com.example.bibliodex.R;
import com.example.bibliodex.RecyclerViewManagment.MarginItemDecoration;
import com.example.bibliodex.RecyclerViewManagment.SwipeToDeleteCallback;
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
        this.bookList = bookVM.getAllUnreadBooks();
        this.bookAdapter = new BookAdapter(this, bookList);
        this.listCurrentBooks.setAdapter(bookAdapter);
        this.btnAddBook.setOnClickListener(this::displayAddBookWindow);
        this.btnSeeShelf.setOnClickListener(this::displayListBookWindow);
        int marginInPx = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        listCurrentBooks.addItemDecoration(new MarginItemDecoration(marginInPx));
        listCurrentBooks.setLayoutManager(new LinearLayoutManager(this));

        SwipeToDeleteCallback swipeHandler = new SwipeToDeleteCallback(this, bookAdapter);
        new ItemTouchHelper(swipeHandler).attachToRecyclerView(listCurrentBooks);
    }

    /**
     * Displays the Add Book window when the "Add Book" button is clicked.
     * @param view the view that was clicked
     */
    private void displayAddBookWindow(View view) {
        Intent intent = new Intent(this, AddBookWindow.class);
        intent.putExtra("BOOK", bookVM.getBook());
        startActivity(intent);
    }

    private void displayListBookWindow(View view) {
        Intent intent = new Intent(this, ListBookWindow.class);
        startActivity(intent);
    }

}