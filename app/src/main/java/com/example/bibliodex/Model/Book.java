package com.example.bibliodex.Model;

import java.io.Serializable;

/**
 * Represents a book with a title, author, and publication year.
 * Implements Serializable to allow Book objects to be passed between activities.
 */
public class Book implements Serializable {

    private String title;
    private String author;
    private int publicationYear;
    private int rating;
    private boolean isRead;
    private boolean isFavorite;
    private int pageActual;
    private String coverUri;

    /**
     * Gets the title of the book.
     * @return the book's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     * @return the book's author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     * @param author the new author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the publication year of the book.
     * @return the publication year
     */
    public int getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets the publication year of the book.
     * @param publicationYear the new publication year
     */
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    /**
     * Gets the rating of the book.
     * @return the book's rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating of the book.
     * @param rating the new rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Checks if the book has been read.
     * @return true if the book is read, false otherwise
     */
    public boolean isRead() {
        return isRead;
    }

    /**
     * Sets the read status of the book.
     * @param isRead true if the book has been read, false otherwise
     */
    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
    /**
     * Checks if the book is marked as favorite.
     * @return true if the book is a favorite, false otherwise
     */
    public boolean isFavorite() {
        return isFavorite;
    }
    /**
     * Sets the favorite status of the book.
     * @param isFavorite true if the book is a favorite, false otherwise
     */
    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    /**
     * Gets the current page of the book.
     * @return the current page
     */
    public int getPageActual() {
        return pageActual;
    }
    /**
     * Sets the current page of the book.
     * @param pageActual the new current page
     */
    public void setPageActual(int pageActual) {
        this.pageActual = pageActual;
    }

    /**
     * Gets the URI of the book's cover image.
     * @return the cover image URI
     */
    public String getCoverUri() {
        return coverUri;
    }

    /**
     * Sets the URI of the book's cover image.
     * @param coverUri the new cover image URI
     */
    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }

    /**
     * Constructs a new Book with the specified title, author, and publication year.
     */
    public Book() {
        this.title = "";
        this.author = "";
        this.publicationYear = 0;
        this.rating = 0;
        this.isRead = false;
        this.isFavorite = false;
        this.pageActual = 0;
    }

    @Override
    public String toString(){
        return this.title + " by " + this.author + " (" + this.publicationYear + ")" + " - Rating: " + this.rating;
    }
}