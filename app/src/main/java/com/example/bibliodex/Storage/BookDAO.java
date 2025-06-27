package com.example.bibliodex.Storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bibliodex.Model.Book;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Data Access Object (DAO) for managing Book objects in persistent storage using SharedPreferences.
 * Provides methods to add, remove, update, and retrieve books, as well as filter favorites and books in progress.
 */
public class BookDAO extends BaseDAO {

    /** In-memory list of books */
    private ArrayList<Book> listBooks;

    /**
     * Constructor for BookDAO.
     * Initializes the list of books from persistent storage.
     * @param context Android context
     */
    public BookDAO(Context context) {
        super(context);
        this.listBooks = this.getAllBooks();
    }

    /**
     * Retrieves all books from SharedPreferences.
     * @return ArrayList of all Book objects
     */
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

    /**
     * Adds a new book to the list and saves the updated list to SharedPreferences.
     * @param book Book to add
     */
    public void addBook(Book book) {
        listBooks.add(book);
        saveBooks();
    }

    /**
     * Removes a book from the list and saves the updated list to SharedPreferences.
     * @param book Book to remove
     */
    public void removeBook(Book book) {
        listBooks.remove(book);
        saveBooks();
    }

    /**
     * Updates a book at the specified index and saves the updated list to SharedPreferences.
     */
    public void updateBook(Book updatedBook) {
        int i = 0;
        boolean found = false;
        while (!found){
            if (Objects.equals(listBooks.get(i).getId(), updatedBook.getId())) {
                listBooks.set(i, updatedBook);
                found = true;
            }
            i++;
        }
        saveBooks();
    }

    /**
     * Retrieves all books marked as favorites.
     * @return ArrayList of favorite Book objects
     */
    public ArrayList<Book> getAllFavorite() {
        ArrayList<Book> favoriteBooks = new ArrayList<>();
        for (Book book : listBooks) {
            if (book.isFavorite()) {
                favoriteBooks.add(book);
            }
        }
        return favoriteBooks;
    }

    /**
     * Retrieves all books that are not marked as read (in progress).
     * @return ArrayList of books in progress
     */
    public ArrayList<Book> getAllInProgress() {
        ArrayList<Book> readBooks = new ArrayList<>();
        for (Book book : listBooks) {
            if (!book.isRead()) {
                readBooks.add(book);
            }
        }
        return readBooks;
    }

    /**
     * Retrieves all books that are marked as read.
     * @return ArrayList of read Book objects
     */
    public ArrayList<Book> getAllRead() {
        ArrayList<Book> readBooks = new ArrayList<>();
        for (Book book : listBooks) {
            if (book.isRead()) {
                readBooks.add(book);
            }
        }
        return readBooks;
    }

    /**
     * Saves the current list of books to SharedPreferences as a JSON string.
     */
    public void saveBooks() {
        SharedPreferences prefs = getContext().getSharedPreferences("books_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = getGson().toJson(listBooks);
        editor.putString("books_data", json);
        editor.apply();
    }
}