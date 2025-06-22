package com.example.bibliodex.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bibliodex.Model.Book;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class BookDAO extends BaseDAO {

    private ArrayList<Book> listBooks;

    /**
     * Constructor for BookDAO.
     * @param context Android context
     */
    public BookDAO(Context context) {
        super(context);
        this.listBooks = this.getAllBooks();
    }

    public ArrayList<Book> getAllBooks() {
        SharedPreferences prefs = getContext().getSharedPreferences("books_prefs", Context.MODE_PRIVATE);
        String json = prefs.getString("books_data", null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<Book>>(){}.getType();
            listBooks = getGson().fromJson(json, type);
        } else {
            listBooks = new ArrayList<>();
        }
        return listBooks;
    }

    public void addBook(Book book) {
        listBooks.add(book);
        saveBooks();
    }

    public void removeBook(Book book) {
        listBooks.remove(book);
        saveBooks();
    }

    public void updateBook(int index, Book book) {
        if (index >= 0 && index < listBooks.size()) {
            listBooks.set(index, book);
            saveBooks();
        }
    }

    public Book getBook(int index) {
        Book book = null;
        if (index >= 0 && index < listBooks.size()) {
            book = listBooks.get(index);
        }
        return book;
    }

    public ArrayList<Book> getAllFavorite() {
        ArrayList<Book> favoriteBooks = new ArrayList<>();
        for (Book book : listBooks) {
            if (book.isFavorite()) {
                favoriteBooks.add(book);
            }
        }
        return favoriteBooks;
    }

    public ArrayList<Book> getAllInRead() {
        ArrayList<Book> readBooks = new ArrayList<>();
        for (Book book : listBooks) {
            if (!book.isRead()) {
                readBooks.add(book);
            }
        }
        return readBooks;
    }

    public void saveBooks() {
        SharedPreferences prefs = getContext().getSharedPreferences("books_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = getGson().toJson(listBooks);
        editor.putString("books_data", json);
        editor.apply();
    }
}
