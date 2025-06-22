package com.example.bibliodex.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bibliodex.R;
import com.example.bibliodex.ViewModel.BookVM;

public class AddBookWindow extends AppCompatActivity {

    private CheckBox checkRead;
    private EditText inputPage;
    private Button btnCancel;
    private EditText inputTitle;
    private EditText inputAuthor;
    private EditText inputYear;
    private RatingBar ratingBar;
    private CheckBox checkFav;
    private Button btnValidate;
    private Button btnChangeCover;
    private ImageView coverImage;
    private ActivityResultLauncher<Intent> pickImageLauncher;
    private BookVM bookVM;
    private String coverUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_book_window);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        this.checkRead = findViewById(R.id.checkRead);
        this.inputPage = findViewById(R.id.inputPage);
        this.btnCancel = findViewById(R.id.btnCancel);
        this.inputTitle = findViewById(R.id.inputTitle);
        this.inputAuthor = findViewById(R.id.inputAuthor);
        this.inputYear = findViewById(R.id.inputYear);
        this.ratingBar = findViewById(R.id.rateBook);
        this.checkFav = findViewById(R.id.checkFav);
        this.btnValidate = findViewById(R.id.btnValidate);
        this.btnChangeCover = findViewById(R.id.btnChangeCover);
        this.coverImage = findViewById(R.id.coverPreview);
        this.coverImage.setImageResource(R.drawable.default_book);

        checkRead.setOnCheckedChangeListener((buttonView, isChecked) -> {
            inputPage.setVisibility(isChecked ? View.GONE : View.VISIBLE);
        });
        inputPage.setVisibility(checkRead.isChecked() ? View.GONE : View.VISIBLE);
        this.btnCancel.setOnClickListener(this::displayMainPage);

        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImageUri = result.getData().getData();
                        coverImage.setImageURI(selectedImageUri);
                        coverUri = selectedImageUri.toString();
                    }
                }
        );
        btnChangeCover.setOnClickListener(this::changeCover);
        btnValidate.setOnClickListener(this::validateAddBook);
    }

    /**
     * Validates the input fields and adds a new book.
     * @param view the view that triggered this method
     */
    private void validateAddBook(View view) {
        BookVM bookVM = new BookVM(this);
        bookVM.setTitle(inputTitle.getText().toString());
        bookVM.setAuthor(inputAuthor.getText().toString());
        bookVM.setPublicationYear(Integer.parseInt(inputYear.getText().toString()));
        bookVM.setRating((int) ratingBar.getRating());
        bookVM.setFavorite(checkFav.isChecked());
        bookVM.setRead(checkRead.isChecked());
        bookVM.setPageActual(checkRead.isChecked() ? 0 : Integer.parseInt(inputPage.getText().toString()));
        bookVM.setCoverUri(coverUri != null ? coverUri : "android.resource://com.example.bibliodex/drawable/default_book");
        bookVM.addBook();
        displayMainPage(view);
    }

    /**
     * Changes the cover image of the book.
     * @param view the view that triggered this method
     */
    private void changeCover(View view){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        pickImageLauncher.launch(intent);
    }

    /**
     * Displays the main page of the application.
     * @param view the view that triggered this method
     */
    private void displayMainPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}