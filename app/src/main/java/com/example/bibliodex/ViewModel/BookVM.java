package com.example.bibliodex.ViewModel;

import android.content.Context;

import com.example.bibliodex.Model.Book;
import com.example.bibliodex.Storage.BaseDAO;
import com.example.bibliodex.Storage.BookDAO;

import java.io.Serializable;
import java.util.ArrayList;

public class BookVM implements Serializable {
    private Book book;
    private BookDAO bookDAO;

    /**
     * Constructor for BookVM.
     */
    public BookVM(Context context) {
        this.bookDAO = new BookDAO(context);
        this.book = new Book();
    }

    /**
     * Gets the book associated with this ViewModel.
     * @return the book
     */
    public Book getBook() {
        return book;
    }

    /**
     * Sets the book for this ViewModel.
     * @param book the book to set
     */
    public void setBook(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return book.getTitle();
    }
    public void setTitle(String title) {
        book.setTitle(title);
    }
    public String getAuthor() {
        return book.getAuthor();
    }
    public void setAuthor(String author) {
        book.setAuthor(author);
    }
    public int getPublicationYear(){
        return book.getPublicationYear();
    }
    public void setPublicationYear(int publicationYear) {
        book.setPublicationYear(publicationYear);
    }
    public int getRating() {
        return book.getRating();
    }
    public void setRating(int rating) {
        book.setRating(rating);
    }
    public boolean isRead() {
        return book.isRead();
    }
    public void setRead(boolean read) {
        book.setRead(read);
    }
    public boolean isFavorite() {
        return book.isFavorite();
    }
    public void setFavorite(boolean favorite) {
        book.setFavorite(favorite);
    }
    public int getPageActual() {
        return book.getPageActual();
    }
    public void setPageActual(int pageActual) {
        book.setPageActual(pageActual);
    }


    /**
     * Add the book using the Data Access Object.
     */
    public void addBook() {
        bookDAO.addBook(book);
    }

    /**
     * Updates the book in the Data Access Object.
     */
    public void updateBook(int index) {
        bookDAO.updateBook(index, book);
    }
    /**
     * Removes the book from the Data Access Object.
     */
    public void removeBook() {
        bookDAO.removeBook(book);
    }
    /**
     * Gets the book at a specific index from the Data Access Object.
     * @param index the index of the book
     * @return the book at the specified index
     */
    public Book getBookAtIndex(int index) {
        return bookDAO.getBook(index);
    }
    /**
     * Gets all books from the Data Access Object.
     * @return an array of all books
     */
    public ArrayList<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    /**
     * Gets all favorite books from the Data Access Object.
     * @return an array of all favorite books
     */
    public ArrayList<Book> getAllFavoriteBooks() {
        return bookDAO.getAllFavorite();
    }

    /**
     * Gets all unread books from the Data Access Object.
     * @return an array of all unread books
     */
    public ArrayList<Book> getAllUnreadBooks() {
        return bookDAO.getAllInRead();
    }

}
