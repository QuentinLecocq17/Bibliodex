package com.example.bibliodex.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.bibliodex.R;

public class AddBookWindow extends AppCompatActivity {

    private CheckBox checkRead;
    private EditText inputPage;
    private Button btnCancel;

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
        checkRead.setOnCheckedChangeListener((buttonView, isChecked) -> {
            inputPage.setVisibility(isChecked ? View.GONE : View.VISIBLE);
        });
        inputPage.setVisibility(checkRead.isChecked() ? View.GONE : View.VISIBLE);
        this.btnCancel.setOnClickListener(this::displayMainPage);
    }

    private void displayMainPage(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}